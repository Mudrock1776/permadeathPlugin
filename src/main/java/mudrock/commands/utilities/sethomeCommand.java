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

public class sethomeCommand implements CommandExecutor{
    private final permadeathPlugin plugin;
    public sethomeCommand(permadeathPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location home = player.getLocation();
            NamespacedKey homeKey = new NamespacedKey(plugin, "home");
            player.getPersistentDataContainer().set(homeKey, PersistentDataType.INTEGER_ARRAY, new int[]{home.getBlockX(),home.getBlockY(),home.getBlockZ()});
            player.getPersistentDataContainer().set(new NamespacedKey(plugin, "homeWorld"), PersistentDataType.STRING, player.getWorld().getName());
            player.sendMessage(ChatColor.GREEN+"Home set.");
        }
        return true;
    }

}
