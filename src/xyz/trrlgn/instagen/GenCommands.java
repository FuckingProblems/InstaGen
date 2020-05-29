package xyz.trrlgn.instagen;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GenCommands implements CommandExecutor {

	public IG plugin;

	public GenCommands(IG pl) {
		plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("instagen")) {

			switch (args.length) {
			case 0:
				openGui(sender);
				break;

			case 1:
				return false;

			case 2:
				return false;

			case 3:
				if(args[0].equalsIgnoreCase("give")) {
					if(!sender.hasPermission("instagen.give")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.noPermission")));
						return true;
					}
					Player p;
					try {
						p = Bukkit.getPlayer(args[1]);
						if(args[2].equalsIgnoreCase("cobble") || args[2].equalsIgnoreCase("cobblestone")) {
							p.getInventory().addItem(plugin.im.cobbleGenWall());
						} else if(args[2].equalsIgnoreCase("obsidian") || args[2].equalsIgnoreCase("obby")) {
							p.getInventory().addItem(plugin.im.obsidianGenWall());
						} else if(args[2].equalsIgnoreCase("sand")) {
							p.getInventory().addItem(plugin.im.sandGenWall());
						}
					} catch (Exception e) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.playerNotFound")));
						return true;
					}

				}
				break;

			default:
				openGui(sender);
				break;
			}
		}
		return true;
	}

	public void openGui(CommandSender sender) {
		if(sender instanceof Player) {
			if(sender.hasPermission("instagen.gui")) {
				plugin.gui.openGUI((Player) sender);
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.noPermission")));
			}
		} else {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.mustBePlayer")));
		}
	}

}
