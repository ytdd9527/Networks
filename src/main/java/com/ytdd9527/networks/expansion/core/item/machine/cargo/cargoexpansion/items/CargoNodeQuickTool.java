package com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.items;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import com.ytdd9527.networks.expansion.core.item.AbstractMySlimefunItem;
import io.github.sefiraat.networks.Networks;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CargoNodeQuickTool extends AbstractMySlimefunItem {

    private final NamespacedKey listKey, configKey, cargoKey;
    private final int[] listSlots = { 19, 20, 21, 28, 29, 30, 37, 38, 39 };
    private final Gson gson = new Gson();

    public CargoNodeQuickTool(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        listKey = new NamespacedKey(Networks.getInstance(),"item_list");
        configKey = new NamespacedKey(Networks.getInstance(),"config");
        cargoKey = new NamespacedKey(Networks.getInstance(),"cargo_type");
    }

    @Override
    public void preRegister(){
        addItemHandler((ItemUseHandler) e -> {
            e.cancel();
            Block target = e.getClickedBlock().orElse(null);
            // No target, return
            if (target==null) return;
            ItemStack tool = e.getItem();
            Player p = e.getPlayer();
            if(!isTool(tool)){
                // Not holding the a valid tool, return
                p.sendMessage(ChatColor.RED+"工具无效，请勿堆叠使用");
                return;
            }
            Location bLoc = target.getLocation();
            // If not cargo node block, return.
            SlimefunBlockData blockData = StorageCacheUtils.getBlock(bLoc);
            if (blockData == null || !blockData.getSfId().startsWith("CARGO_NODE_")) {
                p.sendMessage(ChatColor.RED+"请指向一个货运节点");
                return;
            }
            ItemMeta meta = tool.getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();
            if(p.isSneaking()){
                //process to load config from target
                switch (blockData.getSfId()){
                    case "CARGO_NODE_INPUT":
                    case "CARGO_NODE_OUTPUT_ADVANCED":
                        YamlConfiguration itemConfig = new YamlConfiguration();
                        BlockMenu inv = blockData.getBlockMenu();
                        for(int slot : listSlots){
                            ItemStack itemInSlot = inv.getItemInSlot(slot);
                            if(itemInSlot!=null)
                                itemConfig.set(String.valueOf(slot),itemInSlot);
                        }
                        container.set(listKey, PersistentDataType.STRING,itemConfig.saveToString());
                    case "CARGO_NODE_OUTPUT":
                        //save cargo type and config
                        container.set(cargoKey,PersistentDataType.STRING, blockData.getSfId());
                        container.set(configKey,PersistentDataType.STRING, gson.toJson(blockData.getAllData()));
                        //update lore
                        List<String> lore = meta.getLore();
                        lore.set(lore.size()-1, ChatColor.BLUE + "物品: " + SlimefunItem.getById(blockData.getSfId()).getItemName());
                        meta.setLore(lore);
                        tool.setItemMeta(meta);
                        p.sendMessage(ChatColor.GREEN+"加载成功");
                        return;
                    default:
                        p.sendMessage(ChatColor.RED+"请指向一个货运节点");
                }
            }else{
                //process to set config to target
                String storedId = container.get(cargoKey,PersistentDataType.STRING);
                if(storedId==null){
                    p.sendMessage(ChatColor.RED+"请先加载一个配置");
                    return;
                }
                if(!storedId.equalsIgnoreCase(blockData.getSfId())){
                    p.sendMessage(ChatColor.RED+"储存的配置种类与目标不一致");
                    return;
                }
                BlockMenu inv = blockData.getBlockMenu();
                switch (blockData.getSfId()){
                    case "CARGO_NODE_INPUT":
                    case "CARGO_NODE_OUTPUT_ADVANCED":
                        YamlConfiguration itemConfig = new YamlConfiguration();
                        try {
                            itemConfig.loadFromString(container.get(listKey,PersistentDataType.STRING));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            return;
                        }
                        if(itemConfig.getKeys(false).size()>0) {
                            Map<ItemStack, Boolean> itemList = new HashMap<>();
                            Map<ItemStack, Integer> consumeSlots = new HashMap<>();
                            //init item check list
                            for (String each : itemConfig.getKeys(false)) {
                                itemList.put(itemConfig.getItemStack(each), false);
                            }
                            PlayerInventory pInv = p.getInventory();
                            for (int i = 0; i <= 35; i++) {
                                ItemStack includeItem = isInclude(pInv.getItem(i), itemList);
                                if (includeItem != null)
                                    consumeSlots.put(includeItem, i);
                                //if all included, stop checking
                                if (isAllTrue(itemList.values())) break;
                            }
                            if (isAllTrue(itemList.values())) {
                                //replace items in target inv
                                for (int slot : listSlots) {
                                    ItemStack item = itemConfig.getItemStack(String.valueOf(slot));
                                    if (item != null) {
                                        int consumeSlot = consumeSlots.get(item);
                                        ItemStack itemInInv = pInv.getItem(consumeSlot);
                                        ItemStack itemInTarget = inv.getItemInSlot(slot);
                                        if (SlimefunUtils.isItemSimilar(itemInTarget, item, true, false)) {
                                            if (itemInTarget.getAmount() < item.getAmount()) {
                                                itemInInv.setAmount(Math.max(itemInInv.getAmount() - (item.getAmount() - itemInTarget.getAmount()), 0));
                                            }
                                        } else {
                                            itemInInv.setAmount(Math.max(itemInInv.getAmount() - item.getAmount(), 0));
                                        }
                                        pInv.setItem(consumeSlot, itemInInv);
                                        inv.replaceExistingItem(slot, item);
                                    } else
                                        inv.replaceExistingItem(slot, null);


                                }
                            } else {
                                p.sendMessage(ChatColor.RED + "没有足够的材料");
                                for (ItemStack item : itemList.keySet()) {
                                    if (!itemList.get(item)) {
                                        ItemMeta itemMeta = item.getItemMeta();
                                        p.sendMessage("- " + ChatColor.YELLOW + (itemMeta.hasDisplayName() ? itemMeta.getDisplayName() : item.getType().name()) + "x" + item.getAmount());
                                    } else {
                                        for (int slot : listSlots) {
                                            inv.replaceExistingItem(slot, null);
                                        }
                                    }
                                    return;
                                }
                            }
                        }

                    case "CARGO_NODE_OUTPUT":
                        Map<String, String> config = gson.fromJson(
                                container.get(configKey, PersistentDataType.STRING),
                                new TypeToken<Map<String, String>>() { }.getType()
                        );
                        config.forEach(blockData::setData);
                        inv.getPreset().newInstance(inv,bLoc);
                        p.sendMessage(ChatColor.GREEN+"设置成功！");
                        return;
                    default:
                        p.sendMessage(ChatColor.RED+"请指向一个货运节点");
                }
            }
        });
    }

    private ItemStack isInclude(ItemStack item, Map<ItemStack,Boolean> checkList){
        for(ItemStack each : checkList.keySet()){
            if(item!=null && SlimefunUtils.isItemSimilar(each,item,true,false) && item.getAmount() >= each.getAmount()){
                checkList.put(each,true);
                return each;
            }
        }
        return null;
    }

    private boolean isAllTrue(Collection<Boolean> set){
        for(boolean each : set){
            if(!each) return false;
        }
        return true;
    }

    private boolean isTool(ItemStack tool){
        if(tool!=null&&tool.getItemMeta()!=null){
            NamespacedKey idKey = new NamespacedKey(Slimefun.instance(),"slimefun_item");
            PersistentDataContainer container = tool.getItemMeta().getPersistentDataContainer();
            if(container.has(idKey, PersistentDataType.STRING))
                return container.get(idKey,PersistentDataType.STRING).equalsIgnoreCase(getId())&&(tool.getAmount()==1);
        }
        return false;
    }
}
