package mudrock.commands.utilities;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import mudrock.permadeathPlugin;
import net.md_5.bungee.api.ChatColor;

public class homeCommand implements CommandExecutor {
    private final permadeathPlugin plugin;
    public homeCommand(permadeathPlugin plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (player.getPersistentDataContainer().has(new NamespacedKey(plugin, "home"))) {
                int[] xyz = player.getPersistentDataContainer().get(new NamespacedKey(plugin, "home"), PersistentDataType.INTEGER_ARRAY);
                String homeWorld = player.getPersistentDataContainer().get(new NamespacedKey(plugin, "homeWorld"), PersistentDataType.STRING);
                Location home = new Location(plugin.getServer().getWorld(homeWorld), xyz[0], xyz[1], xyz[2]);
                Location back = player.getLocation();
                player.getPersistentDataContainer().set(new NamespacedKey(plugin, "back"), PersistentDataType.INTEGER_ARRAY, new int[]{back.getBlockX(),back.getBlockY(),back.getBlockZ()});
                player.getPersistentDataContainer().set(new NamespacedKey(plugin, "backWorld"), PersistentDataType.STRING, back.getWorld().getName());
                player.teleport(home);
                player.sendMessage(ChatColor.GREEN+"Returned Home");
            } else {
                player.sendMessage(ChatColor.RED+"No home set!");
            }
        }
        return true;
    }

}
