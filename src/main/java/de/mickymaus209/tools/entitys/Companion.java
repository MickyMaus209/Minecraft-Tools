package de.mickymaus209.tools.entitys;

import de.mickymaus209.tools.Tools;
import de.mickymaus209.tools.inventorys.CompanionInventory;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftCreature;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataType;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class Companion {
    private Entity entity;
    private final Player player;
    private final Tools plugin;
    public static final Map<UUID, Companion> companions = new LinkedHashMap<>();
    private final CompanionInventory backpack;

    public Companion(Player player, Tools plugin) {
        this.player = player;
        this.plugin = plugin;
        //BackpackInventory for companion
        this.backpack = new CompanionInventory(this.plugin, this.player.getUniqueId());
    }

    public void create() {
        this.entity = player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
        this.entity.setCustomName(plugin.translate("companions.customName").replaceAll("%playerName%", player.getName()));
        this.entity.setCustomNameVisible(true);
        this.entity.getPersistentDataContainer().set(plugin.getCompanionKey(), PersistentDataType.STRING, player.getUniqueId().toString());
        companions.put(player.getUniqueId(), this);
    }

    public void follow(Creature creature, Location loc, double speed) {
        if (!(entity instanceof Wolf)) {
            return;
        }
        ((CraftCreature) creature).getHandle().getNavigation().a(loc.getX(), loc.getY(), loc.getZ(), speed);

        if (entity.getLocation().distance(player.getLocation()) < 40) {
            return;
        }
        entity.teleport(player.getLocation());
    }

    public CompanionInventory getBackpack() {
        return backpack;
    }

    public Entity getEntity() {
        return entity;
    }

    public Player getPlayer() {
        return player;
    }

    public void remove() {
        this.entity.remove();
        companions.remove(player.getUniqueId());
    }
}
