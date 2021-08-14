package de.mickymaus209.tools.commands;

import de.mickymaus209.tools.Tools;
import de.mickymaus209.tools.builder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class BowCommand implements CommandExecutor {
    public static final String BOW_DISPLAY_NAME = "§4§l§nExplosiv Bogen§r";
    private final Tools plugin;

    public BowCommand(Tools plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("bow").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("bow")) {
            player.sendMessage("§cNo Permission");
            return false;
        }

        if (args.length != 1) {
            player.sendMessage("§cUsage: /bow <power>");
            return false;
        }

        ItemBuilder builder = new ItemBuilder(Material.BOW, this.plugin).setAmount(1).setDisplayName(BOW_DISPLAY_NAME + " §cPower: §e " + args[0]).addEnchantment(Enchantment.ARROW_INFINITE, 1);
        builder.getItemMeta().getPersistentDataContainer().set(plugin.getBowKey(), PersistentDataType.INTEGER, Integer.parseInt(args[0]));
        player.getInventory().addItem(builder.getItemStack());
        return false;
    }
}
