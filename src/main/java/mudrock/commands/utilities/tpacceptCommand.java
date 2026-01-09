package mudrock.commands.utilities;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import mudrock.permadeathPlugin;

public class tpacceptCommand implements CommandExecutor {
    private final permadeathPlugin plugin;
    public tpacceptCommand(permadeathPlugin plugin){
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
                    target.sendMessage(ChatColor.GREEN+"Telporting to "+ChatColor.WHITE+player.getName());
                    player.sendMessage(ChatColor.GREEN+"Telporting "+ChatColor.WHITE+target.getName());
                    target.teleport(player);
                }
                player.getPersistentDataContainer().remove(new NamespacedKey(plugin, "tprequest"));
            } else {
                player.sendMessage(ChatColor.RED+"No one is requesting to teleport to you");
            }
        }
        return true;
    }
}
