package tolley.jabp.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class UserInterfaceInventoryListener implements Listener {
    FileConfiguration config;
    String windowTitle;

    public void sendInfo(String incomingWindowTitle) {
        windowTitle = incomingWindowTitle;
    }

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(windowTitle)) {
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getString("type1Name")))) {
                            Player p = (Player) e.getWhoClicked();
                            e.getWhoClicked().closeInventory();
                            e.setCancelled(true);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getString("type2Name")))) {
                            Player p = (Player) e.getWhoClicked();
                            e.getWhoClicked().closeInventory();
                            e.setCancelled(true);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getString("type3Name")))) {
                            Player p = (Player) e.getWhoClicked();
                            e.getWhoClicked().closeInventory();
                            e.setCancelled(true);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getString("type4Name")))) {
                            Player p = (Player) e.getWhoClicked();
                            e.getWhoClicked().closeInventory();
                            e.setCancelled(true);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getString("type5Name")))) {
                            Player p = (Player) e.getWhoClicked();
                            e.getWhoClicked().closeInventory();
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

