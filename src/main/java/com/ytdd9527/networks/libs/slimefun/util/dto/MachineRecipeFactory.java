package com.ytdd9527.networks.libs.slimefun.util.dto;


import com.ytdd9527.networks.libs.plugin.dto.AdvancedMachineRecipe;
import com.ytdd9527.networks.libs.plugin.dto.ItemAmountWrapper;
import com.ytdd9527.networks.libs.plugin.util.ItemStackUtil;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Final_ROOT
 * @since 2.0
 */
public class MachineRecipeFactory {
    private final Map<String, List<MachineRecipe>> recipeMap = new HashMap<>();
    private final Map<String, List<AdvancedMachineRecipe>> advancedRecipeMap = new HashMap<>();
    private static volatile MachineRecipeFactory instance;

    private MachineRecipeFactory() {

    }

    @Nonnull
    public List<MachineRecipe> getRecipe(@Nonnull String id) {
        if (this.recipeMap.containsKey(id)) {
            return this.recipeMap.get(id);
        }
        List<MachineRecipe> machineRecipeList = new ArrayList<>();
        this.recipeMap.put(id, machineRecipeList);
        return machineRecipeList;
    }

    @Nonnull
    public List<AdvancedMachineRecipe> getAdvancedRecipe(@Nonnull String id) {
        if (this.advancedRecipeMap.containsKey(id)) {
            return this.advancedRecipeMap.get(id);
        } else if (this.recipeMap.containsKey(id)) {
            this.initAdvancedRecipeMap(id);
            return this.advancedRecipeMap.get(id);
        }
        return new ArrayList<>();
    }

    public void initAdvancedRecipeMap(@Nonnull String id) {
        List<MachineRecipe> machineRecipeList = this.recipeMap.get(id);
        if (machineRecipeList == null) {
            return;
        }
        List<AdvancedMachineRecipe> advancedMachineRecipeList = new ArrayList<>(machineRecipeList.size());
        for (MachineRecipe machineRecipe : machineRecipeList) {
            ItemAmountWrapper[] inputItems = ItemStackUtil.calItemArrayWithAmount(machineRecipe.getInput());

            AdvancedMachineRecipe.AdvancedRandomOutput[] advancedRandomOutputs;
            if (machineRecipe instanceof RandomMachineRecipe) {
                advancedRandomOutputs = new AdvancedMachineRecipe.AdvancedRandomOutput[((RandomMachineRecipe) machineRecipe).getRandomOutputs().length];
                RandomMachineRecipe.RandomOutput[] randomOutputs = ((RandomMachineRecipe) machineRecipe).getRandomOutputs();
                for (int i = 0; i < randomOutputs.length; i++) {
                    advancedRandomOutputs[i] = new AdvancedMachineRecipe.AdvancedRandomOutput(ItemStackUtil.calItemArrayWithAmount(randomOutputs[i].getOutputItem()), randomOutputs[i].getWeight());
                }
            } else {
                advancedRandomOutputs = new AdvancedMachineRecipe.AdvancedRandomOutput[1];
                advancedRandomOutputs[0] = new AdvancedMachineRecipe.AdvancedRandomOutput(ItemStackUtil.calItemArrayWithAmount(machineRecipe.getOutput()), 1);
            }

            advancedMachineRecipeList.add(new AdvancedMachineRecipe(inputItems, advancedRandomOutputs));
        }
        this.advancedRecipeMap.put(id, advancedMachineRecipeList);
    }

    @Nonnull
    public static MachineRecipeFactory getInstance() {
        if (instance == null) {
            synchronized (MachineRecipeFactory.class) {
                if (instance == null) {
                    instance = new MachineRecipeFactory();
                }
            }
        }
        return instance;
    }
}
