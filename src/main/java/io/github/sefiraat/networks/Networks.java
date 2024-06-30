package io.github.sefiraat.networks;

import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.data.DataSource;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.data.DataStorage;
import com.ytdd9527.networks.expansion.core.item.machine.cargo.cargoexpansion.data.QueryQueue;

import com.ytdd9527.networks.expansion.setup.SetupUtil;

import com.ytdd9527.networks.expansion.setup.depreacte.DepreacteExpansionItems;
import com.ytdd9527.networks.expansion.util.ConfigManager;
import io.github.sefiraat.networks.commands.NetworksMain;
import io.github.sefiraat.networks.managers.ListenerManager;
import io.github.sefiraat.networks.managers.SupportedPluginManager;
import io.github.sefiraat.networks.slimefun.NetheoPlants;
import io.github.sefiraat.networks.slimefun.NetworkSlimefunItems;
import io.github.sefiraat.networks.slimefun.network.NetworkController;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import net.guizhanss.guizhanlibplugin.updater.GuizhanUpdater;
import net.guizhanss.slimefun4.utils.WikiUtils;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.AdvancedPie;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.sql.SQLException;
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
    private static DataSource dataSource;
    private static QueryQueue queryQueue;
    private static BukkitRunnable autoSaveThread;
    private int slimefunTickCount;


    public Networks() {
        this.username = "ytdd9527";
        this.repo = "NetworkExpansion";
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
        getLogger().info("##############################################################################");
        getLogger().info("███╗   ██╗███████╗████████╗██╗    ██╗ ██████╗ ██████╗ ██╗  ██╗                ");
        getLogger().info("████╗  ██║██╔════╝╚══██╔══╝██║    ██║██╔═══██╗██╔══██╗██║ ██╔╝                ");
        getLogger().info("██╔██╗ ██║█████╗     ██║   ██║ █╗ ██║██║   ██║██████╔╝█████╔╝                 ");
        getLogger().info("██║╚██╗██║██╔══╝     ██║   ██║███╗██║██║   ██║██╔══██╗██╔═██╗                 ");
        getLogger().info("██║ ╚████║███████╗   ██║   ╚███╔███╔╝╚██████╔╝██║  ██║██║  ██╗                ");
        getLogger().info("╚═╝  ╚═══╝╚══════╝   ╚═╝    ╚══╝╚══╝  ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝                ");
        getLogger().info("███████╗██╗  ██╗██████╗  █████╗ ███╗   ██╗███████╗██╗ ██████╗ ███╗   ██╗      ");
        getLogger().info("██╔════╝╚██╗██╔╝██╔══██╗██╔══██╗████╗  ██║██╔════╝██║██╔═══██╗████╗  ██║      ");
        getLogger().info("█████╗   ╚███╔╝ ██████╔╝███████║██╔██╗ ██║███████╗██║██║   ██║██╔██╗ ██║      ");
        getLogger().info("██╔══╝   ██╔██╗ ██╔═══╝ ██╔══██║██║╚██╗██║╚════██║██║██║   ██║██║╚██╗██║      ");
        getLogger().info("███████╗██╔╝ ██╗██║     ██║  ██║██║ ╚████║███████║██║╚██████╔╝██║ ╚████║      ");
        getLogger().info("╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝  ╚═╝╚═╝  ╚═══╝╚══════╝╚═╝ ╚═════╝ ╚═╝  ╚═══╝      ");
        getLogger().info("##############################################################################");
        getLogger().info("####################################################");
        getLogger().info("                  Networks - 网络                   ");
        getLogger().info("             作者: Sefiraat 汉化: ybw0014           ");
        getLogger().info("             NetworksExpansion - 网络拓展           ");
        getLogger().info("             作者: yitoudaidai, tinalness           ");
        getLogger().info("              如遇bug请优先反馈至改版仓库:           ");
        getLogger().info("https://github.com/ytdd9527/NetworksExpansion/issues");
        getLogger().info("####################################################");

        getLogger().info("正在获取配置信息...");
        saveDefaultConfig();

        getLogger().info("尝试自动更新...");
        this.configManager = new ConfigManager();
        tryUpdate();

        this.supportedPluginManager = new SupportedPluginManager();

        // Try connect database
        getLogger().info("正在连接数据库文件...");
        try {
            dataSource = new DataSource();
        } catch (ClassNotFoundException | SQLException e) {
            getLogger().warning("数据库文件连接失败！");
            e.printStackTrace();
            onDisable();
        }

        getLogger().info("正在创建队列...");
        queryQueue = new QueryQueue();
        queryQueue.startThread();

        getLogger().info("正在创建自动保存线程...");
        autoSaveThread = new BukkitRunnable() {
            @Override
            public void run() {
                DataStorage.saveAmountChange();
            }
        };
        // 5m * 60s * 20 ticks
        long period = 5 * 60 * 20;
        autoSaveThread.runTaskTimerAsynchronously(this, 2*period, period);

        getLogger().info("正在注册物品...");
        setupSlimefun();

        getLogger().info("正在注册指令...");
        this.listenerManager = new ListenerManager();
        this.getCommand("networks").setExecutor(new NetworksMain());

        setupMetrics();

        Bukkit.getScheduler()
                .runTaskTimer(
                        this,
                        () -> slimefunTickCount++,
                        1,
                        Slimefun.getTickerTask().getTickRate());

        getLogger().info("已启用附属！");
    }
    @Override
    public void onDisable() {
        getLogger().info("正在保存配置信息...");
        this.configManager.saveAll();
        getLogger().info("已禁用附属！");
    }

    public void tryUpdate() {
        if (configManager.isAutoUpdate() && getDescription().getVersion().startsWith("Build")) {
            GuizhanUpdater.start(this, getFile(), username, repo, branch);
        }
    }

    public static ConfigManager getConfigManager() {
        return Networks.getInstance().configManager;
    }

    public static QueryQueue getQueryQueue() {
        return queryQueue;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
    public void setupSlimefun() {
        NetworkSlimefunItems.setup();
        DepreacteExpansionItems.setup();
        SetupUtil.init();
        WikiUtils.setupJson(this);
        if (supportedPluginManager.isNetheopoiesis()){
            try {
                NetheoPlants.setup();
                getLogger().info("检测到安装了下界乌托邦，注册相关物品！");
            } catch (NoClassDefFoundError e) {
                getLogger().severe("未安装下界乌托邦！相关物品将不会注册.");
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

    public static int getSlimefunTickCount() {
        return getInstance().slimefunTickCount;
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
        return MessageFormat.format("https://slimefun-addons-wiki.guizhanss.cn/networks/{0}/{1}", this.username, this.repo);
    }
}