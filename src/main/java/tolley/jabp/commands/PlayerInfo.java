package tolley.jabp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import tolley.jabp.PlayerObject;
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
        if (sender.hasPermission("jabp.playerinfo")) {
            if (args.length == 1) {
                String uuid;
                OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
                if (op.hasPlayedBefore()) {
                    uuid = op.getUniqueId().toString();
                } else {
                    if (sender instanceof Player) {
                        sender.sendMessage(ChatColor.RED + "[JABP] " + ChatColor.WHITE + "I could not find that player");
                    } else {
                        Bukkit.getLogger().info(ChatColor.RED + "[JABP] " + ChatColor.WHITE + "I could not find that player");
                    }
                    return true;
                }

                PlayerObject playerData = dataHandler.getPlayerData(uuid);

                if (playerData == null) {
                    sender.sendMessage("An error occurred while retrieving the data for that user");
                    return true;
                }

                String msg = ChatColor.RED + "Username: " + ChatColor.AQUA + playerData.username
                        + ChatColor.RED + "\n\nUUID: " + ChatColor.AQUA + uuid
                        + ChatColor.RED + "\n\nIP: " + ChatColor.AQUA + playerData.ip
                        + ChatColor.RED + "\n\n" + ChatColor.translateAlternateColorCodes('&', config.getString("type1Name")) + ": " + ChatColor.AQUA + playerData.type1
                        + ChatColor.RED + "\n\n" + ChatColor.translateAlternateColorCodes('&', config.getString("type2Name")) + ": " + ChatColor.AQUA + playerData.type2
                        + ChatColor.RED + "\n\n" + ChatColor.translateAlternateColorCodes('&', config.getString("type3Name")) + ": " + ChatColor.AQUA + playerData.type3
                        + ChatColor.RED + "\n\n" + ChatColor.translateAlternateColorCodes('&', config.getString("type4Name")) + ": " + ChatColor.AQUA + playerData.type4
                        + ChatColor.RED + "\n\n" + ChatColor.translateAlternateColorCodes('&', config.getString("type5Name")) + ": " + ChatColor.AQUA + playerData.type5;

                if (sender instanceof Player) {
                    sender.sendMessage(msg);
                } else {
                    Bukkit.getLogger().info(msg);
                }
            } else {
                sender.sendMessage("Incorrect Syntax. Use: /playerinfo <player>");
            }
        } else {
            sender.sendMessage("You do not have permission for this!");
        }
        return true;
    }
}
