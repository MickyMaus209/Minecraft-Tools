package de.mickymaus209.tools.listeners;

import de.mickymaus209.tools.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ProjectileHitListener implements Listener {
    private Tools plugin;

    public ProjectileHitListener(Tools plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event){
        if (!(event.getEntity().getShooter() instanceof Player)) {
            return;
        }

        if(!(event.getEntity() instanceof Arrow)){
            return;
        }

        Player player = (Player) event.getEntity().getShooter();
        if(!player.hasPermission("bow")){
            return;
        }

        if(player.getInventory().getItemInMainHand().getItemMeta() == null) {
            return;
        }

        if(!player.getInventory().getItemInMainHand().getType().equals(Material.BOW)){
            return;
        }

        if(event.getHitEntity() instanceof Mob || event.getHitEntity() instanceof Animals){
            return;
        }

        PersistentDataContainer container = player.getInventory().getItemInHand().getItemMeta().getPersistentDataContainer();
        if (!container.has(plugin.getBowKey(), PersistentDataType.INTEGER)) {
            return;
        }

        Arrow arrow = (Arrow) event.getEntity();
        arrow.getWorld().createExplosion(arrow.getLocation(), container.get(plugin.getBowKey(), PersistentDataType.INTEGER));
        arrow.remove();
    }
}
