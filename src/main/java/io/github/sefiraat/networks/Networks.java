package io.github.sefiraat.networks;

import io.github.sefiraat.networks.commands.NetworksMain;
import io.github.sefiraat.networks.managers.ListenerManager;
import io.github.sefiraat.networks.managers.SupportedPluginManager;
import io.github.sefiraat.networks.slimefun.NetheoPlants;
import io.github.sefiraat.networks.slimefun.NetworkSlimefunItems;
import io.github.sefiraat.networks.slimefun.network.NetworkController;
import io.github.sefiraat.networks.slimefun.yitoudaidai.ExpansionSlimefunItems;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.managers.ConfigManager;
import io.github.sefiraat.networks.slimefun.yitoudaidai.expansion.model.ItemsModel;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import net.guizhanss.guizhanlibplugin.updater.GuizhanUpdater;
import net.guizhanss.slimefun4.utils.WikiUtils;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.AdvancedPie;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class Networks extends JavaPlugin implements SlimefunAddon {


    private static Networks instance;

    private final String username;
    private final String repo;
    private final String branch;
    private ConfigManager configManager;
    private ListenerManager listenerManager;
    private SupportedPluginManager supportedPluginManager;

    public Networks() {
        this.username = "ybw0014";
        this.repo = "Networks";
        this.branch = "master";
    }

    @Override
    public void onEnable() {
        instance = this;

        if (!getServer().getPluginManager().isPluginEnabled("GuizhanLibPlugin")) {
            getLogger().log(Level.SEVERE, "本插件需要 鬼斩前置库插件(GuizhanLibPlugin) 才能运行!");
            getLogger().log(Level.SEVERE, "从此处下载: https://50l.cc/gzlib");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getLogger().info("########################################");
        getLogger().info("            Networks - 网络              ");
        getLogger().info("       作者: Sefiraat 汉化: ybw0014      ");
        getLogger().info("       魔改网络: yitoudaidai、tinalness  ");
        getLogger().info("            拓展实用网络功能              ");
        getLogger().info("########################################");

        saveDefaultConfig();

        this.configManager = new ConfigManager();
        tryUpdate();

        this.supportedPluginManager = new SupportedPluginManager();

        setupSlimefun();

        this.listenerManager = new ListenerManager();
        this.getCommand("networks").setExecutor(new NetworksMain());

        setupMetrics();
    }
    @Override
    public void onDisable() {

        this.configManager.saveAll();
    }
    public void tryUpdate() {
        if (configManager.isAutoUpdate() && getDescription().getVersion().startsWith("Build")) {
            GuizhanUpdater.start(this, getFile(), username, repo, branch);
        }
    }

    public static ConfigManager getConfigManager() {
        return Networks.getInstance().configManager;
    }
    public void setupSlimefun() {
        NetworkSlimefunItems.setup();
        ExpansionSlimefunItems.setup();
        ItemsModel.setup();
        WikiUtils.setupJson(this);
        if (supportedPluginManager.isNetheopoiesis()){
            try {
                NetheoPlants.setup();
            } catch (NoClassDefFoundError e) {
                getLogger().severe("你必须更新下界乌托邦才能让网络添加相关功能.");
            }
        }
    }

    public void setupMetrics() {
        final Metrics metrics = new Metrics(this, 13644);

        AdvancedPie networksChart = new AdvancedPie("networks", () -> {
            Map<String, Integer> networksMap = new HashMap<>();
            networksMap.put("Number of networks", NetworkController.getNetworks().size());
            return networksMap;
        });

        metrics.addCustomChart(networksChart);
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return MessageFormat.format("https://github.com/{0}/{1}/issues/", this.username, this.repo);
    }

    @Nonnull
    public String getWikiURL() {
        return "https://slimefun-addons-wiki.guizhanss.cn/networks/{0}";
    }


    public static Networks getInstance() {
        return Networks.instance;
    }
    @Nonnull
    public static PluginManager getPluginManager() {
        return Networks.getInstance().getServer().getPluginManager();
    }


    public static SupportedPluginManager getSupportedPluginManager() {
        return Networks.getInstance().supportedPluginManager;
    }

    public static ListenerManager getListenerManager() {
        return Networks.getInstance().listenerManager;
    }

}
