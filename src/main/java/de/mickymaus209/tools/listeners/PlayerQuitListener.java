package de.mickymaus209.tools.listeners;

import de.mickymaus209.tools.Tools;
import de.mickymaus209.tools.entitys.Companion;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final Tools plugin;

    public PlayerQuitListener(Tools plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){

        //QuitMessage
        if(this.plugin.getConfig().getString("settings.chef").equals(event.getPlayer().getName())){
            event.setQuitMessage(this.plugin.translate("settings.adminQuitMessage").replaceAll("%playerDisplayName%", event.getPlayer().getDisplayName()));
        }else{
            event.setQuitMessage(this.plugin.translate("settings.quitMessage").replaceAll("%playerDisplayName%", event.getPlayer().getDisplayName()));
        }

        //Companion remove
        if(!Companion.companions.containsKey(event.getPlayer().getUniqueId())){
            return;
        }
        Companion.companions.get(event.getPlayer().getUniqueId()).remove();
    }
}
