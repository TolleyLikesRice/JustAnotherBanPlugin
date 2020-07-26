package tolley.jabp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import tolley.jabp.handlers.DataHandler;

public class PlayerInfo implements CommandExecutor {
    FileConfiguration config;

    DataHandler dataHandler;

    public void init(FileConfiguration configuration, DataHandler dataHandler1) {
        config = configuration;
        dataHandler = dataHandler1;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage("Hello");
        } else {
            Bukkit.getLogger().info("You cannot use BanGUI from console!");
        }
        return true;
    }

}
