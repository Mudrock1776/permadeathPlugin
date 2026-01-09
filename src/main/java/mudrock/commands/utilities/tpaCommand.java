package mudrock.commands.utilities;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import mudrock.permadeathPlugin;

public class tpaCommand implements CommandExecutor{
    private final permadeathPlugin plugin;
    public tpaCommand(permadeathPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED+"Required Player Name");
            } else {
                Player target = plugin.getServer().getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.RED+"Unknown Player: "+args[0]);
                } else {
                    target.getPersistentDataContainer().set(new NamespacedKey(plugin, "tprequest"), PersistentDataType.STRING, player.getName());
                    target.sendMessage(ChatColor.WHITE+player.getName()+ChatColor.GOLD+" would like to teleport to you /tpaccept to accept, /tpdeny to deny");
                }
            }
        }
        return true;
    }

}
