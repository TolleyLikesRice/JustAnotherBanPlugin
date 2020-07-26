package tolley.jabp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import tolley.jabp.commands.PunishGUI;
import tolley.jabp.commands.PlayerInfo;
import tolley.jabp.handlers.DataHandler;
import tolley.jabp.listeners.InventoryListener;
import tolley.jabp.listeners.PlayerListener;

import java.io.IOException;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        // Handlers
        DataHandler dataHandler = new DataHandler();

        // Commands
        PunishGUI punishGUI = new PunishGUI();
        PlayerInfo playerInfo = new PlayerInfo();

        // Events
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
        this.getCommand("punish").setExecutor(punishGUI);
        this.getCommand("playerinfo").setExecutor(playerInfo);

        // Register Events
        getServer().getPluginManager().registerEvents(playerListener, this);

        // Init Handlers
        try {
            dataHandler.initDataHandler(getDataFolder().getAbsolutePath());
        } catch (IOException e) {
            getLogger().warning("An IOException occurred while initializing the dataHandler. Disabling JustAnotherBanPlugin");
            getServer().getPluginManager().disablePlugin(this);
        }

        // Init Listeners
        playerListener.sendDataHandler(dataHandler);

        // Init Commands
        punishGUI.init(this, config, dataHandler);
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

