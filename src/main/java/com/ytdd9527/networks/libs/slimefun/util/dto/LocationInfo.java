package com.ytdd9527.networks.libs.slimefun.util.dto;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Final_ROOT
 * @since 2.4
 */
public class LocationInfo {
    private Location location;
    private Config config;
    private String id;
    private SlimefunItem slimefunItem;

    private LocationInfo(@Nonnull Location location, @Nonnull Config config, @Nonnull String id, @Nonnull SlimefunItem slimefunItem) {
        this.location = location;
        this.config = config;
        this.id = id;
        this.slimefunItem = slimefunItem;
    }

    public void refresh() {

    }

    public void cloneLocation() {
        this.location = this.location.clone();
    }

    public Location getLocation() {
        return location;
    }

    public Config getConfig() {
        return config;
    }

    public String getId() {
        return id;
    }

    public SlimefunItem getSlimefunItem() {
        return slimefunItem;
    }

    /**
     * @return false if there is no location info
     */
    public boolean newInstance(@Nonnull Location location) {
        Config config = BlockStorage.getLocationInfo(location);
        String id = config.getString("id");
        if(id == null) {
            return false;
        }
        SlimefunItem slimefunItem = SlimefunItem.getById(id);
        if(slimefunItem == null) {
            return false;
        }

        this.location = location;
        this.config = config;
        this.id = id;
        this.slimefunItem = slimefunItem;

        return true;
    }

    public boolean newInstance() {
        return this.newInstance(this.location);
    }

    @Nullable
    public static LocationInfo get(@Nonnull Location location) {
        Config config = BlockStorage.getLocationInfo(location);
        String id = config.getString("id");
        if(id != null) {
            SlimefunItem slimefunItem = SlimefunItem.getById(id);
            if(slimefunItem != null) {
                return new LocationInfo(location, config, id, slimefunItem);
            }
        }

        return null;
    }
}
