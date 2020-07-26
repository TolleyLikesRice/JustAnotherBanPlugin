package tolley.jabp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tolley.jabp.handlers.DataHandler;
import tolley.jabp.PlayerObject;

public class PlayerListener implements Listener {
    DataHandler dataHandler;
    public void sendDataHandler(DataHandler input) {
        dataHandler = input;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerObject playerData = dataHandler.getPlayerData(player.getUniqueId().toString());
        if (playerData == null) {

            Bukkit.getLogger().info("New Player! Adding " + player.getName() + " (" + player.getUniqueId() + ") to the database.");

            playerData = new PlayerObject(player.getName(), player.getAddress().toString());

            dataHandler.savePlayerData(player.getUniqueId().toString(), playerData);
        } else if (playerData.username != player.getName()) {
            Bukkit.getLogger().info("Player " + player.getName() + " (" + player.getUniqueId() + ") has a different name than in the database, updating database.");
            playerData.username = player.getName();
            dataHandler.savePlayerData(player.getUniqueId().toString(), playerData);
        } else {
            Bukkit.getLogger().info("Player " + player.getName() + " (" + player.getUniqueId() + ") is already in the database.");
        }
    }
}
