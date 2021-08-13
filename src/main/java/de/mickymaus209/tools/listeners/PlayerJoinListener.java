package de.mickymaus209.tools.listeners;

import de.mickymaus209.tools.Tools;
import de.mickymaus209.tools.entitys.Companion;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final Tools plugin;

    public PlayerJoinListener(Tools plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //JoinMessage
        if(this.plugin.getConfig().getString("settings.chef").equals(event.getPlayer().getName()) || event.getPlayer().hasPermission("rank.admin")){
            event.setJoinMessage(this.plugin.translate("settings.adminJoinMessage").replaceAll("%playerDisplayName%", event.getPlayer().getDisplayName()));
        }else{
            event.setJoinMessage(this.plugin.translate("settings.joinMessage").replaceAll("%playerDisplayName%", event.getPlayer().getDisplayName()));
        }

        //Companion
        if(event.getPlayer().hasPermission("companion")) {
            Companion companion = new Companion(event.getPlayer(), this.plugin);
            companion.create();
        }

        event.getPlayer().sendMessage(this.plugin.translate("messages.gotCompanion"));
        event.getPlayer().setHealth(20);
        event.getPlayer().setFoodLevel(20);
    }
}
