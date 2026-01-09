package mudrock.commands.utilities;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import mudrock.permadeathPlugin;

public class backCommand implements CommandExecutor{
    private final permadeathPlugin plugin;
    public backCommand(permadeathPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (player.getPersistentDataContainer().has(new NamespacedKey(plugin, "back"))) {
                int[] xyz = player.getPersistentDataContainer().get(new NamespacedKey(plugin, "back"), PersistentDataType.INTEGER_ARRAY);
                Location back = new Location(plugin.getServer().getWorld(player.getPersistentDataContainer().get(new NamespacedKey(plugin, "backWorld"), PersistentDataType.STRING)), xyz[0], xyz[1], xyz[2]);
                player.teleport(back);
                player.sendMessage(ChatColor.GREEN+"Going back!");
            } else {
                player.sendMessage(ChatColor.RED+"No back set!");
            }
            
        }
        return true;
    }

}
