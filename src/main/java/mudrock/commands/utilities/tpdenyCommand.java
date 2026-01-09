package mudrock.commands.utilities;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import mudrock.permadeathPlugin;

public class tpdenyCommand implements CommandExecutor {
    private final permadeathPlugin plugin;
    public tpdenyCommand(permadeathPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (player.getPersistentDataContainer().has(new NamespacedKey(plugin, "tprequest"))) {
                Player target = plugin.getServer().getPlayer(player.getPersistentDataContainer().get(new NamespacedKey(plugin, "tprequest"), PersistentDataType.STRING));
                if (target == null) {
                    player.sendMessage(ChatColor.WHITE+player.getPersistentDataContainer().get(new NamespacedKey(plugin, "tprequest"),PersistentDataType.STRING)+ChatColor.RED+" Player has disconnected");
                } else {
                    target.sendMessage(ChatColor.WHITE+player.getName()+ChatColor.RED+" declined your request");
                    player.sendMessage(ChatColor.GREEN+"Declined request");
                }
                player.getPersistentDataContainer().remove(new NamespacedKey(plugin, "tprequest"));
            } else {
                player.sendMessage(ChatColor.RED+"No one is requesting to teleport to you");
            }
        }
        return true;
    }
}
