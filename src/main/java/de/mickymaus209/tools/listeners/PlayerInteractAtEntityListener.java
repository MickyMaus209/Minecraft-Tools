package de.mickymaus209.tools.listeners;

import de.mickymaus209.tools.Tools;
import de.mickymaus209.tools.entitys.Companion;
import de.mickymaus209.tools.inventorys.CompanionInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class PlayerInteractAtEntityListener implements Listener {
    private final Tools plugin;

    public PlayerInteractAtEntityListener(Tools plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof Wolf)) {
            return;
        }
        Wolf wolf = (Wolf) event.getRightClicked();

        //check if the clicked wolf is a companion
        PersistentDataContainer container = wolf.getPersistentDataContainer();
        if (!container.has(plugin.getCompanionKey(), PersistentDataType.STRING)) {
            return;
        }

        //Set all items in the backpack inventory and open it
        CompanionInventory backpack = Companion.companions.get(UUID.fromString(wolf.getPersistentDataContainer().get(plugin.getCompanionKey(), PersistentDataType.STRING))).getBackpack();
        backpack.setItems();
        event.getPlayer().openInventory(backpack.getInventory());

        event.getPlayer().sendMessage("§5Wuff");
    }
}
