package tolley.jabp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tolley.jabp.Main;
import tolley.jabp.handlers.DataHandler;
import tolley.jabp.listeners.InventoryListener;

import static org.bukkit.Bukkit.getServer;

public class PunishGUI implements CommandExecutor {

    Main main;
    FileConfiguration config;
    InventoryListener inventoryListener;

    DataHandler dataHandler;

    public void init(Main mainPlugin, FileConfiguration configuration, DataHandler dataHandler1) {
        main = mainPlugin;
        config = configuration;
        dataHandler = dataHandler1;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("jabp.bangui")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 1) {
                    OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
                    if (!op.hasPlayedBefore()) {
                        sender.sendMessage(ChatColor.RED + "[JABP] " + ChatColor.WHITE + "I could not find that player");
                        return true;
                    }

                    String windowTitle = "Punish: " + args[0];
                    Inventory inv = Bukkit.createInventory(null, 27, windowTitle);

                    ItemStack one = new ItemStack(Material.BARRIER);
                    ItemMeta oneItemMeta = one.getItemMeta();
                    oneItemMeta.setDisplayName(ChatColor.RED + ChatColor.translateAlternateColorCodes('&', config.getString("type1Name")));
                    one.setItemMeta(oneItemMeta);

                    ItemStack two = new ItemStack(Material.RED_WOOL);
                    ItemMeta twoMeta = two.getItemMeta();
                    twoMeta.setDisplayName(ChatColor.RED + ChatColor.translateAlternateColorCodes('&', config.getString("type2Name")));
                    two.setItemMeta(twoMeta);

                    ItemStack three = new ItemStack(Material.APPLE);
                    ItemMeta threeMeta = three.getItemMeta();
                    threeMeta.setDisplayName(ChatColor.RED + ChatColor.translateAlternateColorCodes('&', config.getString("type3Name")));
                    three.setItemMeta(threeMeta);

                    ItemStack four = new ItemStack(Material.COMPASS);
                    ItemMeta fourMeta = four.getItemMeta();
                    fourMeta.setDisplayName(ChatColor.RED + ChatColor.translateAlternateColorCodes('&', config.getString("type4Name")));
                    four.setItemMeta(fourMeta);

                    ItemStack five = new ItemStack(Material.PLAYER_HEAD);
                    ItemMeta fiveMeta = five.getItemMeta();
                    fiveMeta.setDisplayName(ChatColor.RED + ChatColor.translateAlternateColorCodes('&', config.getString("type5Name")));
                    five.setItemMeta(fiveMeta);

                    // Background
                    inv.setItem(0, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                    inv.setItem(2, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                    inv.setItem(4, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                    inv.setItem(6, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                    inv.setItem(8, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

                    inv.setItem(10, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                    //inv.setItem(12, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                    //inv.setItem(14, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                    inv.setItem(16, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

                    inv.setItem(18, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                    inv.setItem(20, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                    inv.setItem(22, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                    inv.setItem(24, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                    inv.setItem(26, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

                    inv.setItem(1, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                    inv.setItem(3, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                    inv.setItem(5, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                    inv.setItem(7, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));

                    inv.setItem(9, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                    //inv.setItem(11, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                    //inv.setItem(13, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                    //inv.setItem(15, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                    inv.setItem(17, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));

                    inv.setItem(19, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                    inv.setItem(21, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                    inv.setItem(23, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
                    inv.setItem(25, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));

                    inv.setItem(11, one);
                    inv.setItem(12, two);
                    inv.setItem(13, three);
                    inv.setItem(14, four);
                    inv.setItem(15, five);
                    player.openInventory(inv);
                    inventoryListener = new InventoryListener();
                    getServer().getPluginManager().registerEvents(inventoryListener, main);
                    inventoryListener.sendInfo(config, windowTitle,  op.getName(), op.getUniqueId().toString(), dataHandler);
                } else {
                    player.sendMessage("Incorrect Syntax. Use: /punish <player>");
                }
            } else {
                Bukkit.getLogger().info("You cannot use BanGUI from console!");
            }
        } else {
            sender.sendMessage("You do not have permission for this!");
        }
        return true;
    }
}