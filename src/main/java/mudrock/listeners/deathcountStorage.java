package mudrock.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import mudrock.permadeathPlugin;

public class deathcountStorage {
    private final permadeathPlugin plugin;
    public deathcountStorage(permadeathPlugin plugin){
        this.plugin = plugin;
    }

    public void init(){
        File deathcountFile = new File(plugin.getDataFolder().getAbsolutePath(), "deathcount.yml");
        if (deathcountFile.exists()) {
            YamlConfiguration deathcountConfig = YamlConfiguration.loadConfiguration(deathcountFile);
            for ( String s : deathcountConfig.getKeys(false)){
                Integer count = deathcountConfig.getInt(s);
                plugin.addDeathCount(s, count);
            }
        }
    }

    public void terminate() throws IOException{
        File deathcountFile = new File(plugin.getDataFolder().getAbsolutePath(), "deathcount.yml");
        if (!deathcountFile.exists()) {
            deathcountFile.createNewFile();
        }
        YamlConfiguration deathcountConfig = YamlConfiguration.loadConfiguration(deathcountFile);
        for (String id : plugin.getDeathCount().keySet()) {
            deathcountConfig.set(id, plugin.getPlayerDeathCount(id));
        }
        deathcountConfig.save(deathcountFile);
    }
}
