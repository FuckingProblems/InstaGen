package xyz.trrlgn.instagen;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GUI {

	public IG plugin;
	
	public GUI(IG pl) {
		plugin = pl;
	}
	
	public Inventory inv;
	
	public void loadInventory() {
		inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("guiTitle")));
		inv.setItem(0, plugin.im.cobbleGenWall());
		inv.setItem(1, plugin.im.sandGenWall());
		inv.setItem(2, plugin.im.obsidianGenWall());
	}
	
	public void openGUI(Player p) {
		p.openInventory(inv);
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.openedGUI")));
	}
	
}
