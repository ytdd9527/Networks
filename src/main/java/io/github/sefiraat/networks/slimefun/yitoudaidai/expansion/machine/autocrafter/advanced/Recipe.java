package io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.machine.autocrafter.advanced;

import org.bukkit.Material;

import java.util.Map;

class Recipe {
    Map<Material, Integer> inputs;  // 输入物品及其所需数量
    Map<Material, Integer> outputs; // 输出物品及其生成数量

    public Recipe(Map<Material, Integer> inputs, Map<Material, Integer> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }
}