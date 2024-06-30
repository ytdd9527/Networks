package com.ytdd9527.networks.libs.plugin.dto;



import com.ytdd9527.networks.common.util.CompareUtil;

import javax.annotation.Nonnull;

/**
 * @author Final_ROOT
 * @since 2.0
 */
public class AdvancedMachineRecipe {
    @Nonnull
    private final ItemAmountWrapper[] inputs;
    @Nonnull
    private final AdvancedRandomOutput[] randomOutputs;
    private final int[] weightBeginValues;
    private int weightSum = 0;

    public AdvancedMachineRecipe(@Nonnull ItemAmountWrapper[] inputs, @Nonnull AdvancedRandomOutput[] randomOutputs) {
        this.inputs = inputs;
        this.randomOutputs = randomOutputs;
        this.weightBeginValues = new int[randomOutputs.length];
        for (int i = 0; i < this.randomOutputs.length; i++) {
            this.weightBeginValues[i] = this.weightSum;
            this.weightSum += this.randomOutputs[i].getWeight();
        }
    }

    @Nonnull
    public ItemAmountWrapper[] getInput() {
        return this.inputs;
    }

    @Nonnull
    public ItemAmountWrapper[] getOutput() {
        int r = (int)(Math.random() * this.weightSum);
        return this.randomOutputs[CompareUtil.getIntSmallFuzzyIndex(this.weightBeginValues, r)].outputItem;
    }

    @Nonnull
    public AdvancedRandomOutput[] getOutputs() {
        return this.randomOutputs;
    }

    public boolean isRandomOutput() {
        return this.randomOutputs.length > 1;
    }

    public int getWeightSum() {
        return this.weightSum;
    }

    public record AdvancedRandomOutput(@Nonnull ItemAmountWrapper[] outputItem, int weight) {
        public AdvancedRandomOutput(@Nonnull ItemAmountWrapper[] outputItem, int weight) {
            this.outputItem = outputItem;
            this.weight = weight;
        }

        @Nonnull
        public ItemAmountWrapper[] getOutputItem() {
            return outputItem;
        }

        public int getWeight() {
            return weight;
        }
    }
}
