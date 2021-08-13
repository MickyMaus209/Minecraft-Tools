package de.mickymaus209.tools.utils;

import de.mickymaus209.tools.Tools;
import de.mickymaus209.tools.entitys.Companion;
import org.bukkit.entity.Creature;
import org.bukkit.scheduler.BukkitRunnable;

public class CompanionTimer {
    private Tools plugin;

    public CompanionTimer(Tools plugin) {
        this.plugin = plugin;
        this.startTimer();
    }

    private void startTimer() {
        new BukkitRunnable() {

            @Override
            public void run() {
                Companion.companions.forEach((uuid, companion) -> companion.follow((Creature) companion.getEntity(),
                        companion.getPlayer().getLocation(),
                        plugin.getConfig().getDouble("companions.speed")));
            }
        }.runTaskTimer(this.plugin, 0, 20);
    }
}
