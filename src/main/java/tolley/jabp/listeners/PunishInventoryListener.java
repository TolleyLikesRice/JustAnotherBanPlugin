package tolley.jabp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import tolley.jabp.dataTypes.PlayerObject;
import tolley.jabp.dataTypes.PunishmentReason;
import tolley.jabp.handlers.DataHandler;
import tolley.jabp.handlers.PunishmentHandler;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PunishInventoryListener implements Listener {
    DataHandler dataHandler;
    PunishmentHandler punishmentHandler;

    FileConfiguration config;
    String windowTitle;
    String badPlayer;
    String badPlayerUUID;

    public void sendInfo(FileConfiguration incomingConfig, String incomingWindowTitle, String incomingBadPlayer, String incomingBadPlayerUUID, DataHandler incomingDataHandler, PunishmentHandler incomingPunishmentHandler) {
        config = incomingConfig;
        windowTitle = incomingWindowTitle;
        badPlayer = incomingBadPlayer;
        badPlayerUUID = incomingBadPlayerUUID;
        dataHandler = incomingDataHandler;
        punishmentHandler = incomingPunishmentHandler;
    }

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(windowTitle)) {
            if (e.getCurrentItem() != null) {
                //if (e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                        String itemDisplayName = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
                        if (itemDisplayName.equals(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', config.getString("type1Name"))))) {
                            PlayerObject playerObject = dataHandler.getPlayerData(badPlayerUUID);
                            Player p = (Player) e.getWhoClicked();
                            e.getWhoClicked().closeInventory();
                            p.sendMessage("Punishing " + badPlayer + " for " + ChatColor.translateAlternateColorCodes('&', config.getString("type1Name")));
                            dataHandler.modifyType1(badPlayerUUID, playerObject.type1 + 1);
                            e.setCancelled(true);
                            punishmentHandler.banPlayer(playerObject, new PunishmentReason(playerObject.username, "Type1", p.getName(), punishmentHandler.parseLengthString("2d"), new Date(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis())));
                        } else if (itemDisplayName.equals(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', config.getString("type2Name"))))) {
                            PlayerObject playerObject = dataHandler.getPlayerData(badPlayerUUID);
                            Player p = (Player) e.getWhoClicked();
                            e.getWhoClicked().closeInventory();
                            p.sendMessage("Punishing " + badPlayer + " for " + ChatColor.translateAlternateColorCodes('&', config.getString("type2Name")));
                            dataHandler.modifyType2(badPlayerUUID, playerObject.type2 + 1);
                            e.setCancelled(true);
                        } else if (itemDisplayName.equals(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', config.getString("type3Name"))))) {
                            PlayerObject playerObject = dataHandler.getPlayerData(badPlayerUUID);
                            Player p = (Player) e.getWhoClicked();
                            e.getWhoClicked().closeInventory();
                            p.sendMessage("Punishing " + badPlayer + " for " + ChatColor.translateAlternateColorCodes('&', config.getString("type3Name")));
                            dataHandler.modifyType3(badPlayerUUID, playerObject.type3 + 1);
                            e.setCancelled(true);
                        } else if (itemDisplayName.equals(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', config.getString("type4Name"))))) {
                            PlayerObject playerObject = dataHandler.getPlayerData(badPlayerUUID);
                            Player p = (Player) e.getWhoClicked();
                            e.getWhoClicked().closeInventory();
                            p.sendMessage("Punishing " + badPlayer + " for " + ChatColor.translateAlternateColorCodes('&', config.getString("type4Name")));
                            dataHandler.modifyType4(badPlayerUUID, playerObject.type4 + 1);
                            e.setCancelled(true);

                        } else if (itemDisplayName.equals(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', config.getString("type5Name"))))) {
                            PlayerObject playerObject = dataHandler.getPlayerData(badPlayerUUID);
                            Player p = (Player) e.getWhoClicked();
                            e.getWhoClicked().closeInventory();
                            p.sendMessage("Punishing " + badPlayer + " for " + ChatColor.translateAlternateColorCodes('&', config.getString("type5Name")));
                            dataHandler.modifyType5(badPlayerUUID, playerObject.type5 + 1);
                            e.setCancelled(true);
                        } else {
                            e.setCancelled(true);
                        }
                    } else {
                        e.setCancelled(true);
                    }
                //} else {
                //    e.setCancelled(true);
                //}
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

