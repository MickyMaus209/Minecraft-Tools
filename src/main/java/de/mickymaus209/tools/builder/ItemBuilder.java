package de.mickymaus209.tools.builder;

import de.mickymaus209.tools.Tools;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
    private final ItemStack item;
    private ItemMeta itemMeta;
    private final Tools plugin;


    public ItemBuilder(Material material, short subId, Tools plugin) {
        this.plugin = plugin;
        item = new ItemStack(material, 1, subId);
        itemMeta = item.getItemMeta();
    }

    public ItemBuilder(Material material, Tools plugin) {
        this(material, (short) 0, plugin);
    }

    public ItemStack getItemStack() {
        item.setItemMeta(itemMeta);
        return item;
    }

    public ItemBuilder setDisplayName(String displayName) {
        itemMeta.setDisplayName(displayName);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemStack getItem() {
        return item;
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }
}
