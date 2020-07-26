package tolley.jabp.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import tolley.jabp.handlers.DataHandler;

public class InventoryListener implements Listener {
    DataHandler dataHandler;
    FileConfiguration config;
    String windowTitle;
    String badPlayer;
    String badPlayerUUID;
    public void sendInfo(FileConfiguration incomingConfig, String incomingWindowTitle, String incomingBadPlayer, String incomingBadPlayerUUID, DataHandler incomingDataHandler) {
        config = incomingConfig;
        windowTitle = incomingWindowTitle;
        badPlayer = incomingBadPlayer;
        badPlayerUUID = incomingBadPlayerUUID;
        dataHandler = incomingDataHandler;
    }

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(windowTitle)) {
            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                     if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getString("type1Name")))) {
                         Player p = (Player) e.getWhoClicked();
                         e.getWhoClicked().closeInventory();
                         p.sendMessage("Punishing " + badPlayer + " for " + ChatColor.translateAlternateColorCodes('&', config.getString("type1Name")));
                         dataHandler.modifyType1(badPlayerUUID, dataHandler.getPlayerData(badPlayerUUID).type1 + 1);
                         e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getString("type2Name")))) {
                         Player p = (Player) e.getWhoClicked();
                         e.getWhoClicked().closeInventory();
                         p.sendMessage("Punishing " + badPlayer + " for " + ChatColor.translateAlternateColorCodes('&', config.getString("type2Name")));
                         dataHandler.modifyType2(badPlayerUUID, dataHandler.getPlayerData(badPlayerUUID).type2 + 1);
                         e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getString("type3Name")))) {
                         Player p = (Player) e.getWhoClicked();
                         e.getWhoClicked().closeInventory();
                         p.sendMessage("Punishing " + badPlayer + " for " + ChatColor.translateAlternateColorCodes('&', config.getString("type3Name")));
                         dataHandler.modifyType3(badPlayerUUID, dataHandler.getPlayerData(badPlayerUUID).type3 + 1);
                         e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getString("type4Name")))) {
                         Player p = (Player) e.getWhoClicked();
                         e.getWhoClicked().closeInventory();
                         p.sendMessage("Punishing " + badPlayer + " for " + ChatColor.translateAlternateColorCodes('&', config.getString("type4Name")));
                         dataHandler.modifyType4(badPlayerUUID, dataHandler.getPlayerData(badPlayerUUID).type4 + 1);
                         e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getString("type5Name")))) {
                         Player p = (Player) e.getWhoClicked();
                         e.getWhoClicked().closeInventory();
                         p.sendMessage("Punishing " + badPlayer + " for " + ChatColor.translateAlternateColorCodes('&', config.getString("type5Name")));
                         dataHandler.modifyType5(badPlayerUUID, dataHandler.getPlayerData(badPlayerUUID).type5 + 1);
                         e.setCancelled(true);
                    } else {
                        e.setCancelled(true);
                    }
                } else {
                    e.setCancelled(true);
                }
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        HandlerList.unregisterAll(this);
    }

}

