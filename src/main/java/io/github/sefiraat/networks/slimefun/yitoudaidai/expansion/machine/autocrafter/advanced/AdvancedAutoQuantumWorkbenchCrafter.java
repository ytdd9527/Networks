package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine.autocrafter.advanced;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import io.github.sefiraat.networks.NetworkStorage;
import io.github.sefiraat.networks.network.NetworkRoot;
import io.github.sefiraat.networks.network.NodeDefinition;
import io.github.sefiraat.networks.network.NodeType;
import io.github.sefiraat.networks.network.stackcaches.BlueprintInstance;
import io.github.sefiraat.networks.network.stackcaches.ItemRequest;
import io.github.sefiraat.networks.slimefun.network.NetworkObject;
import io.github.sefiraat.networks.slimefun.yitoudaidai.ExpansionSlimefunitems;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine.blueprint.QuantumWorkbenchBlueprint;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine.supportedrecipes.SupportedQuantumWorkbenchRecipes;
import io.github.sefiraat.networks.utils.Keys;
import io.github.sefiraat.networks.utils.StackUtils;
import io.github.sefiraat.networks.utils.Theme;
import io.github.sefiraat.networks.utils.datatypes.DataTypeMethods;
import io.github.sefiraat.networks.utils.datatypes.PersistentCraftingBlueprintType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AdvancedAutoQuantumWorkbenchCrafter extends NetworkObject {

    private static final int[] BACKGROUND_SLOTS = new int[]{27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53};

    private static final int[] BLUEPRINT_BACKGROUND = new int[]{0, 1, 2, 9, 11, 18, 19, 20};

    private static final int BLUEPRINT_SLOT = 10;
    private static final int[] OUTPUT_SLOT = new int[]{3,4,5,6,7,8,12,13,14,15,16,17,21,22,23,24,25,26};

    public static final CustomItemStack BLUEPRINT_BACKGROUND_STACK = new CustomItemStack(
            Material.BLUE_STAINED_GLASS_PANE, Theme.PASSIVE + "魔法合成蓝图"
    );

    public static final CustomItemStack BACKGROUND_SLOTS1 = new CustomItemStack(
            Material.WHITE_STAINED_GLASS_PANE, Theme.PASSIVE + " "
    );

    private final int chargePerCraft;
    private final boolean withholding;

    private static final Map<Location, BlueprintInstance> INSTANCE_MAP = new HashMap<>();

    public AdvancedAutoQuantumWorkbenchCrafter(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int chargePerCraft, boolean withholding) {
        super(itemGroup, item, recipeType, recipe, NodeType.CRAFTER);

        this.chargePerCraft = chargePerCraft;
        this.withholding = withholding;

        this.getSlotsToDrop().add(BLUEPRINT_SLOT);

        for (int outputSlot : OUTPUT_SLOT) {
            this.getSlotsToDrop().add(outputSlot);
        }
        addItemHandler(
                new BlockTicker() {
                    @Override
                    public boolean isSynchronized() {
                        return false;
                    }

                    @Override
                    public void tick(Block block, SlimefunItem slimefunItem, SlimefunBlockData data) {
                        BlockMenu blockMenu = data.getBlockMenu();
                        if (blockMenu != null) {
                            addToRegistry(block);
                            craftPreFlight(blockMenu);
                        }
                    }
                }
        );
    }

    protected void craftPreFlight(@Nonnull BlockMenu blockMenu) {

        releaseCache(blockMenu);

        final NodeDefinition definition = NetworkStorage.getAllNetworkObjects().get(blockMenu.getLocation());

        if (definition == null || definition.getNode() == null) {
            return;
        }

        final NetworkRoot root = definition.getNode().getRoot();

        if (!this.withholding) {
            for (int outputSlot : OUTPUT_SLOT) {
                final ItemStack stored = blockMenu.getItemInSlot(outputSlot);
                if (stored != null && stored.getType() != Material.AIR) {
                    root.addItemStack(stored);
                    // 清空槽位，因为物品已经被添加到网络中
                    blockMenu.replaceExistingItem(outputSlot, null);
                }
            }
        }
        final ItemStack blueprint = blockMenu.getItemInSlot(BLUEPRINT_SLOT);

        if (blueprint == null || blueprint.getType() == Material.AIR) {
            return;
        }

        final long networkCharge = root.getRootPower();

        if (networkCharge > this.chargePerCraft) {
            final SlimefunItem item = SlimefunItem.getByItem(blueprint);

            if (!(item instanceof QuantumWorkbenchBlueprint)) {
                return;
            }

            BlueprintInstance instance = INSTANCE_MAP.get(blockMenu.getLocation());

            if (instance == null) {
                final ItemMeta blueprintMeta = blueprint.getItemMeta();
                final Optional<BlueprintInstance> optional = DataTypeMethods.getOptionalCustom(blueprintMeta, Keys.BLUEPRINT_INSTANCE, PersistentCraftingBlueprintType.TYPE);

                if (optional.isEmpty()) {
                    return;
                }

                instance = optional.get();
                setCache(blockMenu, instance);
            }

            // 检查输出槽位是否可以接受合成的物品
            boolean canCraft = true;
            for (int outputSlot : OUTPUT_SLOT) {
                final ItemStack currentOutput = blockMenu.getItemInSlot(outputSlot);
                // 如果槽位中已经有物品，并且物品类型不为空气，或者物品数量将要超过最大堆叠数，则不能进行合成
                if (currentOutput != null && currentOutput.getType() != Material.AIR && (currentOutput.getAmount() + instance.getItemStack().getAmount() >= currentOutput.getMaxStackSize())) {
                    canCraft = false;
                    break;
                }
            }

            if (canCraft) {
                // 所有检查通过，尝试进行合成
                if (tryCraft(blockMenu, instance, root)) {
                    root.removeRootPower(this.chargePerCraft);
                }
            }
        }


    }

    private boolean tryCraft(@Nonnull BlockMenu blockMenu, @Nonnull BlueprintInstance instance, @Nonnull NetworkRoot root) {
        // 获取蓝图槽位上的蓝图数量
        int blueprintCount = getBlueprintCount(blockMenu, instance);

        // 确定网络中每种材料能够支持的合成次数
        int materialsLimit = getMaterialsLimit(instance, root);

        // 使用蓝图数量和材料限制中的较小值作为实际的合成数量
        int craftCount = Math.min(blueprintCount, materialsLimit);

        // 如果实际可以合成的数量为0，则直接返回false
        if (craftCount == 0) {
            return false;
        }

        // 获取合成配方所需的材料，并根据实际合成数量进行调整
        HashMap<ItemStack, Integer> requiredItems = new HashMap<>();
        for (ItemStack stack : instance.getRecipeItems()) {
            if (stack != null) {
                // 计算每种材料在实际合成数量下所需的数量
                int requiredAmount = stack.getAmount() * craftCount;
                requiredItems.put(stack, requiredAmount);
            }
        }

        // 检查网络是否拥有足够的每种所需物品
        for (Map.Entry<ItemStack, Integer> entry : requiredItems.entrySet()) {
            if (!root.contains(new ItemRequest(entry.getKey(), entry.getValue()))) {
                return false; // 如果缺少材料，则无法进行合成
            }
        }

        // 从网络中取出实际需要的物品
        final ItemStack[] inputs = new ItemStack[9];
        for (int i = 0; i < 9; i++) {
            final ItemStack requested = instance.getRecipeItems()[i];
            if (requested != null) {
                final ItemStack fetched = root.getItemStack(new ItemRequest(requested, requiredItems.get(requested)));
                inputs[i] = fetched;
            } else {
                inputs[i] = null;
            }
        }

        // 尝试找到匹配的合成配方，并进行合成
        ItemStack crafted = null;
        for (Map.Entry<ItemStack[], ItemStack> entry : SupportedQuantumWorkbenchRecipes.getRecipes().entrySet()) {
            if (SupportedQuantumWorkbenchRecipes.testRecipe(inputs, entry.getKey())) {
                crafted = entry.getValue().clone();
                // 设置合成物品的数量为配方输出数量乘以实际合成数量
                crafted.setAmount(crafted.getAmount() * craftCount);
                break;
            }
        }

        if (crafted != null && crafted.getType() != Material.AIR) {
            int craftedAmount = crafted.getAmount();
            int itemsPlaced = 0; // 已放置的物品数量

            // 遍历所有输出槽位，尝试放置合成物品
            for (int outputSlot : OUTPUT_SLOT) {
                ItemStack currentStack = blockMenu.getItemInSlot(outputSlot);
                if (currentStack == null || currentStack.getType() == Material.AIR || StackUtils.itemsMatch(crafted, currentStack)) {
                    int spaceLeft = currentStack == null ? crafted.getMaxStackSize() : currentStack.getMaxStackSize() - currentStack.getAmount();
                    int amountToPut = Math.min(craftedAmount - itemsPlaced, spaceLeft);
                    if (amountToPut > 0) {
                        // 创建一个新的ItemStack放入槽位
                        ItemStack toPlace = crafted.clone();
                        toPlace.setAmount(amountToPut);
                        blockMenu.pushItem(toPlace, outputSlot);
                        itemsPlaced += amountToPut;
                    }
                }
                if (itemsPlaced >= craftedAmount) {
                    // 如果已放置所有物品，则退出循环
                    break;
                }
            }

            if (itemsPlaced < craftedAmount) {
                // 如果有物品未能放置，则返回所有材料并终止
                returnItems(root, inputs);
                return false;
            }
        } else {
            // 没有合成物品的代码
            return false;
        }
        final Location location = blockMenu.getLocation().clone().add(0.5, 1.1, 0.5);
        if (root.isDisplayParticles()) {
            location.getWorld().spawnParticle(Particle.WAX_OFF, location, 0, 0, 4, 0);
        }
        // 根据实际合成数量扣除相应的能量
        root.removeRootPower(this.chargePerCraft * craftCount);

        return true;
    }

    private int getBlueprintCount(@Nonnull BlockMenu blockMenu, @Nonnull BlueprintInstance instance) {
        ItemStack blueprintStack = blockMenu.getItemInSlot(BLUEPRINT_SLOT);
        return blueprintStack == null ? 0 : blueprintStack.getAmount();
    }


    private int getMaterialsLimit(@Nonnull BlueprintInstance instance, @Nonnull NetworkRoot root) {
        int craftableAmount = Integer.MAX_VALUE; // 初始化为最大整数值
        for (ItemStack requiredItem : instance.getRecipeItems()) {
            if (requiredItem == null) continue; // 跳过空的原料槽位
            ItemRequest request = new ItemRequest(requiredItem, requiredItem.getAmount()); // 每次合成所需的材料数量
            if (!root.contains(request)) { // 如果缺少材料，则无法进行合成
                return 0; // 直接返回0，表示无法合成
            }
            // 计算基于当前材料库存可以合成的最小数量
            int availableAmount = root.getAllNetworkItems().getOrDefault(StackUtils.getAsQuantity(requiredItem, 1), 0);
            int requiredAmountPerCraft = requiredItem.getAmount(); // 每次合成该材料所需的数量
            craftableAmount = Math.min(craftableAmount, availableAmount / requiredAmountPerCraft); // 取最小值
        }
        return craftableAmount;
    }
    private void returnItems(@Nonnull NetworkRoot root, @Nonnull ItemStack[] inputs) {
        for (ItemStack input : inputs) {
            if (input != null && input.getType() != Material.AIR) {
                root.addItemStack(input); // 退还原料到网络
            }
        }
    }


    public void releaseCache(@Nonnull BlockMenu blockMenu) {
        if (blockMenu.hasViewer()) {
            INSTANCE_MAP.remove(blockMenu.getLocation());
        }
    }

    public void setCache(@Nonnull BlockMenu blockMenu, @Nonnull BlueprintInstance blueprintInstance) {
        if (!blockMenu.hasViewer()) {
            INSTANCE_MAP.putIfAbsent(blockMenu.getLocation().clone(), blueprintInstance);
        }
    }


    @Override
    public void postRegister() {
        new BlockMenuPreset(this.getId(), this.getItemName()) {

            @Override
            public void init() {
                drawBackground(BLUEPRINT_BACKGROUND_STACK, BLUEPRINT_BACKGROUND);
                drawBackground(BACKGROUND_SLOTS1,BACKGROUND_SLOTS);
            }

            @Override
            public boolean canOpen(@Nonnull Block block, @Nonnull Player player) {
                return ExpansionSlimefunitems.NE_AUTO_QUANTUM_WORKBENCH.canUse(player, false)
                        && Slimefun.getProtectionManager().hasPermission(player, block.getLocation(), Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (AdvancedAutoQuantumWorkbenchCrafter.this.withholding && flow == ItemTransportFlow.WITHDRAW) {
                    // 直接返回包含所有输出槽位索引的数组
                    return OUTPUT_SLOT;
                }
                // 如果不是WITHDRAW操作或者不处于withholding状态，返回一个空数组
                return new int[0];
            }
        };
    }
}