package de.mickymaus209.tools.inventorys;

import de.mickymaus209.tools.Tools;
import de.mickymaus209.tools.files.DataFile;
import de.mickymaus209.tools.utils.Base64Encoding;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CompanionInventory {
    public static final String INVENTORY_TITLE = "§4Backpack";
    private Inventory inventory;
    private final Tools plugin;
    //Base64File
    private final DataFile dataFile;

    public CompanionInventory(Tools plugin, UUID uuid) {
        this.plugin = plugin;
        this.dataFile = new DataFile(uuid.toString(), plugin);
        this.inventory = Bukkit.createInventory(null, this.plugin.getConfig().getInt("companions.backpackSize"), INVENTORY_TITLE);
        setDefaultBackpack();
    }

    public void setItems() {
        final String content = dataFile.getConfig().getString("backpack");
        final String[] values = new String[]{content};
        final ItemStack[][] items = Base64Encoding.base64ToInv(values);
        this.inventory.setContents(items[0]);
    }

    public void saveItems() {
        final String[] values = Base64Encoding.invToBase64(inventory);
        dataFile.getConfig().set("backpack", values[0]);
        dataFile.save();
    }

    //set empty inventory
    private void setDefaultBackpack() {
        if (!dataFile.getConfig().contains("backpack")) {
            String[] values = Base64Encoding.invToBase64(Bukkit.createInventory(null, this.plugin.getConfig().getInt("companions.backpackSize")));
            dataFile.getConfig().set("backpack", values[0]);
            dataFile.save();
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}
