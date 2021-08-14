package de.mickymaus209.tools.listeners;

import de.mickymaus209.tools.Tools;
import de.mickymaus209.tools.entitys.Companion;
import de.mickymaus209.tools.inventorys.CompanionInventory;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {
    private Tools plugin;

    public InventoryCloseListener(Tools plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!event.getView().getTitle().equals(CompanionInventory.INVENTORY_TITLE)) {
            return;
        }
        //Save items in file by using Base64
        Companion.companions.get(event.getPlayer().getUniqueId()).getBackpack().saveItems();
        event.getPlayer().sendMessage("§bDas Backpack wurde gespeichert.");
    }
}
