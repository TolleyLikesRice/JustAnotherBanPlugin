package tolley.jabp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;

public class PlayerListener implements Listener {
    DataHandler dataHandler;
    public void sendDataHandler(DataHandler input) {
        dataHandler = input;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (dataHandler.getPlayerData(player.getUniqueId().toString()) == null) {

            Bukkit.getLogger().info("New Player! Adding " + player.getName() + " (" + player.getUniqueId() + ") to the database.");

            PlayerObject playerData = new PlayerObject(player.getName(), player.getAddress().toString());

            dataHandler.savePlayerData(player.getUniqueId().toString(), playerData);
        }
    }
}
