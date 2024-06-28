package com.ytdd9527.networks.libs.slimefun.util;

import io.github.thebusybiscuit.slimefun4.core.guide.GuideHistory;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Final_ROOT
 * @since 2.0
 */
public class GuideUtil {
    public static void removeLastEntry(@Nonnull GuideHistory guideHistory) {
        try {
            Method getLastEntry = guideHistory.getClass().getDeclaredMethod("getLastEntry", boolean.class);
            getLastEntry.setAccessible(true);
            getLastEntry.invoke(guideHistory, true);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
