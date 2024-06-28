package com.ytdd9527.networks.libs.slimefun.util.dto;


import com.ytdd9527.networks.common.util.CompareUtil;
import com.ytdd9527.networks.libs.plugin.util.ItemStackUtil;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A {@link MachineRecipe} that its output item is random.
 * @author Final_ROOT
 * @since 2.0
 */
public class RandomMachineRecipe extends MachineRecipe {
    @Nonnull
    private RandomOutput[] randomOutputs;
    private int[] weightBeginValues;
    private int weightSum = 0;

    public RandomMachineRecipe(@Nonnull MachineRecipe machineRecipe, @Nonnull RandomOutput[] randomOutputs) {
        super(machineRecipe.getTicks(), machineRecipe.getInput(), new ItemStack[0]);
        this.randomOutputs = randomOutputs;
        this.weightBeginValues = new int[randomOutputs.length];
        for (int i = 0; i < this.randomOutputs.length; i++) {
            this.weightBeginValues[i] = this.weightSum;
            this.weightSum += this.randomOutputs[i].getWeight();
        }
    }

    public RandomMachineRecipe(@Nonnull ItemStack[] input, @Nonnull RandomOutput[] randomOutputs) {
        super(0, input, new ItemStack[0]);
        this.randomOutputs = randomOutputs;
        this.weightBeginValues = new int[randomOutputs.length];
        for (int i = 0; i < this.randomOutputs.length; i++) {
            this.weightBeginValues[i] = this.weightSum;
            this.weightSum += this.randomOutputs[i].getWeight();
        }
    }

    public RandomMachineRecipe(@Nonnull ItemStack[] input, @Nonnull List<RandomOutput> randomOutputs) {
        super(0, input, new ItemStack[0]);
        this.randomOutputs = randomOutputs.toArray(new RandomOutput[0]);
        this.weightBeginValues = new int[randomOutputs.size()];
        for (int i = 0; i < this.randomOutputs.length; i++) {
            this.weightBeginValues[i] = this.weightSum;
            this.weightSum += this.randomOutputs[i].getWeight();
        }
    }

    public RandomMachineRecipe(@Nonnull ItemStack input, @Nonnull List<RandomOutput> randomOutputs) {
        super(0, new ItemStack[] {input}, new ItemStack[0]);
        this.randomOutputs = randomOutputs.toArray(new RandomOutput[0]);
        this.weightBeginValues = new int[randomOutputs.size()];
        for (int i = 0; i < this.randomOutputs.length; i++) {
            this.weightBeginValues[i] = this.weightSum;
            this.weightSum += this.randomOutputs[i].getWeight();
        }
    }

    @Nonnull
    @Override
    public ItemStack[] getOutput() {
        int r = (int)(Math.random() * this.weightSum);
        return this.randomOutputs[CompareUtil.getIntSmallFuzzyIndex(this.weightBeginValues, r)].outputItem;
    }

    @Nonnull
    public ItemStack[] getAllOutput() {
        List<ItemStack> itemList = new ArrayList<>();
        for (RandomOutput randomOutput : this.randomOutputs) {
            itemList.addAll(Arrays.asList(randomOutput.outputItem));
        }
        return ItemStackUtil.getItemArray(itemList);
    }

    @Nonnull
    public RandomOutput[] getRandomOutputs() {
        return this.randomOutputs;
    }

    @Nonnull
    public RandomMachineRecipe addRandomOutput(@Nonnull RandomOutput... randomOutputs) {
        RandomOutput[] newRandomOutput = new RandomOutput[this.randomOutputs.length + randomOutputs.length];
        int[] newWeightBeginValues = new int[this.weightBeginValues.length + randomOutputs.length];
        System.arraycopy(this.randomOutputs, 0, newRandomOutput, 0, this.randomOutputs.length);
        System.arraycopy(this.weightBeginValues, 0, newWeightBeginValues, 0, this.weightBeginValues.length);
        int newWeightSum = this.weightSum;
        for (int i = 0; i < randomOutputs.length; i++) {
            RandomOutput randomOutput = randomOutputs[i];
            newRandomOutput[i + this.randomOutputs.length] = randomOutputs[i];
            newWeightBeginValues[i + this.weightBeginValues.length] = newWeightSum;
            newWeightSum += randomOutput.weight;
        }

        this.randomOutputs = newRandomOutput;
        this.weightBeginValues = newWeightBeginValues;
        this.weightSum = newWeightSum;
        return this;
    }

    /**
     * @author Final_ROOT
     * @since 2.0
     */
    public static class RandomOutput {
        @Nonnull
        private ItemStack[] outputItem;
        private int weight;

        public RandomOutput(@Nonnull List<ItemStack> outputItem, int weight) {
            this.outputItem = outputItem.toArray(new ItemStack[0]);
            this.weight = weight;
        }

        public RandomOutput(@Nonnull ItemStack[] outputItem, int weight) {
            this.outputItem = outputItem;
            this.weight = weight;
        }

        public RandomOutput(@Nonnull ItemStack outputItem, int weight) {
            this.outputItem = new ItemStack[] {outputItem};
            this.weight = weight;
        }

        public RandomOutput(@Nonnull Material outputItem, int weight) {
            this.outputItem = new ItemStack[] {new ItemStack(outputItem)};
            this.weight = weight;
        }

        @Nonnull
        public ItemStack[] getOutputItem() {
            return outputItem;
        }

        public int getWeight() {
            return weight;
        }
    }
}
