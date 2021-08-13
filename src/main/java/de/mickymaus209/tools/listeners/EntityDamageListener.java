package de.mickymaus209.tools.listeners;

import de.mickymaus209.tools.Tools;
import org.bukkit.Bukkit;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class EntityDamageListener implements Listener {
    private Tools plugin;

    public EntityDamageListener(Tools plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Wolf)){
            return;
        }
        Wolf wolf = (Wolf) event.getEntity();

        //check if the damaged wolf is a companion
        PersistentDataContainer container = wolf.getPersistentDataContainer();
        if(!container.has(plugin.getCompanionKey(), PersistentDataType.STRING)){
            return;
        }

        event.setDamage(0);
    }
}
