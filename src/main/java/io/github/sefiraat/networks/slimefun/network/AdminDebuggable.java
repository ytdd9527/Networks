package io.github.sefiraat.networks.slimefun.network;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;
import io.github.sefiraat.networks.Networks;
import io.github.sefiraat.networks.utils.Theme;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public interface AdminDebuggable {

    String DEBUG_KEY = "network_debugging";

    default boolean isDebug(@Nonnull Location location) {
        String debug = StorageCacheUtils.getData(location, DEBUG_KEY);
        return Boolean.parseBoolean(debug);
    }

    default void setDebug(@Nonnull Location location, boolean value) {
        StorageCacheUtils.setData(location, DEBUG_KEY, String.valueOf(value));
    }

    default void toggle(@Nonnull Location location, @Nonnull Player player) {
        final boolean isDebug = isDebug(location);
        final boolean nextState = !isDebug;
        setDebug(location, nextState);
        player.sendMessage(Theme.SUCCESS + "该方块的调试模式已设置为：" + nextState + "。");
        if (nextState) {
            player.sendMessage(Theme.SUCCESS + "该模式将持续至服务器关闭，或者手动关闭。");
        }
    }

    default void sendDebugMessage(@Nonnull Location location, @Nonnull String string) {
        if (isDebug(location)) {
            final String locationString = "W[" + location.getWorld().getName() + "] " +
                "X[" + location.getBlockX() + "] " +
                "Y[" + location.getBlockY() + "] " +
                "Z[" + location.getBlockZ() + "] ";
            Networks.getInstance().getJavaPlugin().getLogger().log(Level.INFO, locationString + " - " + string);
        }
    }
}
