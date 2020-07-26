package tolley.jabp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import tolley.jabp.commands.BanGUI;
import tolley.jabp.commands.PlayerInfo;
import tolley.jabp.handlers.DataHandler;
import tolley.jabp.listeners.InventoryListener;
import tolley.jabp.listeners.PlayerListener;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        // Handlers
        DataHandler dataHandler = new DataHandler();

        // Commands
        BanGUI banGUI = new BanGUI();
        PlayerInfo playerInfo = new PlayerInfo();

        // Events
        InventoryListener inventoryListener = new InventoryListener();
        PlayerListener playerListener = new PlayerListener();

        // Other
        FileConfiguration config;

        // Get/Create Config
        this.saveDefaultConfig();
        config = this.getConfig();

        // Startup message
        getLogger().info("|+++++++++++++++++++++++++++++++++++++|");
        getLogger().info("| JustAnotherBanPlugin by Tolley      |");
        getLogger().info("| Version 1.0-SNAPSHOT                |");
        getLogger().info("| ENABLED                             |");
        getLogger().info("|+++++++++++++++++++++++++++++++++++++|");

        // Register Commands
        this.getCommand("bangui").setExecutor(banGUI);
        this.getCommand("playerinfo").setExecutor(playerInfo);

        // Register Events
        getServer().getPluginManager().registerEvents(inventoryListener, this);
        getServer().getPluginManager().registerEvents(playerListener, this);

        // Init Handlers
        dataHandler.initDataHandler(getDataFolder().getAbsolutePath());

        // Init Listeners
        playerListener.sendDataHandler(dataHandler);

        // Init Commands
        banGUI.init(inventoryListener, config, dataHandler);
        playerInfo.init(config, dataHandler);

    }

    @Override
    public void onDisable() {
        getLogger().info("|+++++++++++++++++++++++++++++++++++++|");
        getLogger().info("| JustAnotherBanPlugin by Tolley      |");
        getLogger().info("| Version 1.0.0                       |");
        getLogger().info("| DISABLED                            |");
        getLogger().info("|+++++++++++++++++++++++++++++++++++++|");
    }


}

