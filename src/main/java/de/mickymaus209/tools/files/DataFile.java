package de.mickymaus209.tools.files;

import de.mickymaus209.tools.Tools;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataFile {

    private String file;
    private File folder;
    private FileConfiguration cfg;
    private File cfgFile;

    public DataFile(String file, Tools plugin) {
        this.file = file + ".yml";
        folder = new File(plugin.getDataFolder(), "//Backpacks//");
        reload();
    }

    public void reload() {
        if (!folder.exists()) {
            folder.mkdirs();
        }

        cfgFile = new File(folder, file);

        if (!cfgFile.exists()) {
            try {
                cfgFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        cfg = YamlConfiguration.loadConfiguration(cfgFile);
    }

    public FileConfiguration getConfig() {
        if (cfg == null) {
            reload();
        }

        return cfg;
    }

    public void save() {
        if (cfg == null || (cfgFile == null)) {
            return;
        }

        try {
            cfg.save(cfgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
