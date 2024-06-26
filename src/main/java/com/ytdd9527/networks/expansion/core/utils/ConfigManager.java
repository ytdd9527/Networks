package com.ytdd9527.networks.expansion.core.utils;

import com.ytdd9527.networks.expansion.core.utils.DisplayGroupGenerators;
import dev.sefiraat.sefilib.entity.display.DisplayGroup;
import io.github.sefiraat.networks.Networks;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.*;
import java.util.function.Function;

public class ConfigManager {



    public ConfigManager() {
        setupDefaultConfig();
    }

    private void setupDefaultConfig() {
        final Networks plugin = Networks.getInstance();
        final InputStream inputStream = plugin.getResource("config.yml");
        final File existingFile = new File(plugin.getDataFolder(), "config.yml");

        if (inputStream == null) {
            return;
        }

        final Reader reader = new InputStreamReader(inputStream);
        final FileConfiguration resourceConfig = YamlConfiguration.loadConfiguration(reader);
        final FileConfiguration existingConfig = YamlConfiguration.loadConfiguration(existingFile);

        for (String key : resourceConfig.getKeys(false)) {
            checkKey(existingConfig, resourceConfig, key);
        }

        try {
            existingConfig.save(existingFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ParametersAreNonnullByDefault
    private void checkKey(FileConfiguration existingConfig, FileConfiguration resourceConfig, String key) {
        final Object currentValue = existingConfig.get(key);
        final Object newValue = resourceConfig.get(key);
        if (newValue instanceof ConfigurationSection section) {
            for (String sectionKey : section.getKeys(false)) {
                checkKey(existingConfig, resourceConfig, key + "." + sectionKey);
            }
        } else if (currentValue == null) {
            existingConfig.set(key, newValue);
        }
    }


    public boolean isAutoUpdate() {
        return Networks.getInstance().getConfig().getBoolean("auto-update");
    }

    public void saveAll() {
        Networks.getInstance().getLogger().info("正在保存网络拓展数据.");
    }


}
