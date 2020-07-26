package tolley.jabp.commands;

import de.vandermeer.asciitable.AsciiTable;
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

        String msg = ChatColor.RED + "Username: " +  ChatColor.AQUA + playerData.username
                + ChatColor.RED + "\n\nUUID: " + ChatColor.AQUA + uuid
                + ChatColor.RED + "\n\nIP: " + ChatColor.AQUA + playerData.ip
                + ChatColor.RED + "\n\nType 1: " + ChatColor.AQUA + playerData.type1
                + ChatColor.RED + "\n\nType 2: " + ChatColor.AQUA + playerData.type2
                + ChatColor.RED + "\n\nType 3: " + ChatColor.AQUA + playerData.type3
                + ChatColor.RED + "\n\nType 4: " + ChatColor.AQUA + playerData.type4
                + ChatColor.RED + "\n\nType 5: " + ChatColor.AQUA + playerData.type5;

        if (sender instanceof Player) {
            sender.sendMessage(msg);
        } else {
            Bukkit.getLogger().info(msg);
        }

        return true;
    }

}
