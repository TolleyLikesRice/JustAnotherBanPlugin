package tolley.jabp.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class InventoryListener implements Listener {
    FileConfiguration config;
    String windowTitle;
    public void sendInfo(FileConfiguration incomingConfig, String incomingWindowTitle) {
        config = incomingConfig;
        windowTitle = incomingWindowTitle;
    }

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(windowTitle)) {
            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Rules")) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        e.getWhoClicked().closeInventory();
                        List<String> rules = config.getStringList("rules");
                        StringBuilder rulesFormatted = new StringBuilder();
                        for(String rule : rules){
                            rulesFormatted.append(rule).append("\n");
                        }
                        p.sendMessage("The rules are:\n" + rulesFormatted);
                        e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Report")) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        e.getWhoClicked().closeInventory();
                        p.sendMessage(config.getString("reportusage"));
                        e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Events")) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        e.getWhoClicked().closeInventory();
                        p.sendMessage(config.getString("event"));
                        e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Game Selector")) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        e.getWhoClicked().closeInventory();
                        p.sendMessage("In Progress");
                        e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Staff")) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        e.getWhoClicked().closeInventory();
                        List<String> staff = config.getStringList("staff");
                        StringBuilder staffFormatted = new StringBuilder();
                        for(String staffMember : staff){
                            staffFormatted.append(staffMember).append("\n");
                        }
                        p.sendMessage("Our staff are:\n" + staffFormatted);
                        e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Website")) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        e.getWhoClicked().closeInventory();
                        p.sendMessage(ChatColor.GREEN + "Check out our website at " + config.getString("website"));
                        e.setCancelled(true);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Discord")) {
                        //code
                        Player p = (Player) e.getWhoClicked();
                        e.getWhoClicked().closeInventory();
                        p.sendMessage(ChatColor.AQUA + "Check out our discord at " + config.getString("discord"));
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

}

