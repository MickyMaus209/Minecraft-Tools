package de.mickymaus209.tools.listeners;

import de.mickymaus209.tools.Tools;
import de.mickymaus209.tools.entitys.Companion;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangedWorldListener implements Listener {
    private final Tools plugin;

    public PlayerChangedWorldListener(Tools plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        if (!Companion.companions.containsKey(event.getPlayer().getUniqueId())) {
            return;
        }
        //Companion will teleport to the player location when the player changed the world
        Companion.companions.get(event.getPlayer().getUniqueId()).getEntity().teleport(event.getPlayer().getLocation());
    }
}
