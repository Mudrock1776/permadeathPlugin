package mudrock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import mudrock.commands.utilities.backCommand;
import mudrock.commands.utilities.homeCommand;
import mudrock.commands.utilities.sethomeCommand;
import mudrock.commands.utilities.tpaCommand;
import mudrock.commands.utilities.tpacceptCommand;
import mudrock.commands.utilities.tpdenyCommand;
import mudrock.listeners.DeathListener;
import mudrock.listeners.deathcountStorage;

public class permadeathPlugin extends JavaPlugin {
    public static permadeathPlugin PLUGINID;
    private HashMap<String, Integer> deathCount;
    private deathcountStorage deathCountFile;
    
    @Override
    public void onEnable(){
        PLUGINID = this;
        this.deathCount = new HashMap<>();
        this.deathCountFile = new deathcountStorage(PLUGINID);
        this.deathCountFile.init();
        Path dataPath = Paths.get(this.getDataFolder().getAbsolutePath());
        if (!Files.isDirectory(dataPath)){
            try {
                Files.createDirectory(dataPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        getCommand("sethome").setExecutor(new sethomeCommand(PLUGINID));
        getCommand("home").setExecutor(new homeCommand(PLUGINID));
        getCommand("back").setExecutor(new backCommand(PLUGINID));
        getCommand("tpa").setExecutor(new tpaCommand(PLUGINID));
        getCommand("tpaccept").setExecutor(new tpacceptCommand(PLUGINID));
        getCommand("tpdeny").setExecutor(new tpdenyCommand(PLUGINID));
        getServer().getPluginManager().registerEvents(new DeathListener(PLUGINID), PLUGINID);
        getLogger().info("Permadeath enabled");
    }
    @Override
    public void onDisable(){
        getLogger().info("permadeath disabled");
        try {
            this.deathCountFile.terminate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDeathCount(String id, Integer count){
        this.deathCount.put(id, count);
    }
    public Integer getPlayerDeathCount(String id){
        return this.deathCount.get(id);
    }
    public HashMap<String, Integer> getDeathCount(){
        return this.deathCount;
    }
    public boolean hasDeathCount(String id){
        return this.deathCount.containsKey(id);
    }
}
