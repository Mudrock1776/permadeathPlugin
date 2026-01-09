package mudrock.listeners;

import java.io.IOException;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import mudrock.permadeathPlugin;

public class DeathListener implements Listener{
    private final permadeathPlugin plugin;
    public DeathListener(permadeathPlugin plugin){
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onDeath(PlayerDeathEvent event) throws IOException{
        Player player = event.getEntity();
        Server server = plugin.getServer();
        Integer deathCount;

        if (plugin.hasDeathCount(player.getName())) {
            deathCount = plugin.getPlayerDeathCount(player.getName())+1;
        } else {
            deathCount = 1;
        }
        plugin.addDeathCount(player.getName(), deathCount);
        for (Player p : server.getOnlinePlayers()) {
            p.kickPlayer(player.getName()+" Died");
        }
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "start", server.getWorldContainer().getAbsolutePath().replace(".", "restart.bat")
        );
        builder.start();
        server.shutdown();
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Integer deathCount;
        if (plugin.hasDeathCount(player.getName())) {
            deathCount = plugin.getPlayerDeathCount(player.getName());
        } else {
            deathCount = 0;
        }
        player.setPlayerListName(player.getName() + " Deaths: " + deathCount);
    }
}
