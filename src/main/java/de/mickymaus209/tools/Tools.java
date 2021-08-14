package de.mickymaus209.tools;

import de.mickymaus209.tools.commands.BowCommand;
import de.mickymaus209.tools.listeners.*;
import de.mickymaus209.tools.utils.CompanionTimer;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class Tools extends JavaPlugin {

    private final NamespacedKey companionKey = new NamespacedKey(this, "wolfCompanion");
    private final NamespacedKey bowKey = new NamespacedKey(this, "power");

    @Override
    public void onEnable() {
        saveDefaultConfig();
        register();
    }

    private void register() {

        //Listeners
        new PlayerJoinListener(this);
        new PlayerQuitListener(this);
        new PlayerChangedWorldListener(this);
        new InventoryCloseListener(this);
        new PlayerInteractAtEntityListener(this);
        new CompanionTimer(this);
        new EntityDamageListener(this);
        new ProjectileHitListener(this);

        //Commands
        new BowCommand(this);
    }

    public String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString(message));
    }

    public NamespacedKey getCompanionKey() {
        return companionKey;
    }

    public NamespacedKey getBowKey() {
        return bowKey;
    }
}
