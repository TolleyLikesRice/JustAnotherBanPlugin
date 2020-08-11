package tolley.jabp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import tolley.jabp.dataTypes.PlayerObject;
import tolley.jabp.dataTypes.PunishmentReason;
import tolley.jabp.handlers.DataHandler;
import tolley.jabp.handlers.PunishmentHandler;

import java.util.List;

public class PlayerListener implements Listener {
    DataHandler dataHandler;
    PunishmentHandler punishmentHandler;
    FileConfiguration config;

    public void sendDataHandler(DataHandler input, PunishmentHandler input2, FileConfiguration input3) {
        dataHandler = input;
        punishmentHandler = input2;
        config = input3;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerObject playerData = dataHandler.getPlayerData(player.getUniqueId().toString());
        if (playerData == null) {

            Bukkit.getLogger().info("New Player! Adding " + player.getName() + " (" + player.getUniqueId() + ") to the database.");

            playerData = new PlayerObject(player.getName(), player.getAddress().toString().substring(1).split(":")[0]);

            dataHandler.savePlayerData(player.getUniqueId().toString(), playerData);
        } else if (!playerData.username.equals(player.getName())) {
            Bukkit.getLogger().info("Player " + player.getName() + " (" + player.getUniqueId() + ") has a different name than in the database, updating database.");
            playerData.username = player.getName();
            dataHandler.savePlayerData(player.getUniqueId().toString(), playerData);
            if (playerData.ip != player.getAddress().toString().substring(1).split(":")[0]) {
                Bukkit.getLogger().info("Player " + player.getName() + " (" + player.getUniqueId() + ") has a different IP than in the database, updating database.");
                playerData.ip = player.getAddress().toString().substring(1).split(":")[0];
                dataHandler.savePlayerData(player.getUniqueId().toString(), playerData);
            }
        } else if (playerData.ip != player.getAddress().toString().substring(1).split(":")[0]) {
            Bukkit.getLogger().info("Player " + player.getName() + " (" + player.getUniqueId() + ") has a different IP than in the database, updating database.");
            playerData.ip = player.getAddress().toString().substring(1).split(":")[0];
            dataHandler.savePlayerData(player.getUniqueId().toString(), playerData);
        } else {
            Bukkit.getLogger().info("Player " + player.getName() + " (" + player.getUniqueId() + ") is already in the database.");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent event) {
        if(event.getResult() == PlayerLoginEvent.Result.KICK_BANNED) {
            List<PunishmentReason> reasons = dataHandler.getPlayerData(event.getPlayer().getUniqueId().toString()).punishments;
            PunishmentReason kickReason = reasons.get(reasons.size() -1);
            String kickString = punishmentHandler.parsePlaceholders(ChatColor.translateAlternateColorCodes('&', config.getString("BanReason")), kickReason);
            Bukkit.broadcastMessage(event.getPlayer().getName() + " tried to join but they are banned!");
            event.setKickMessage(kickString);
        }
    }
}
