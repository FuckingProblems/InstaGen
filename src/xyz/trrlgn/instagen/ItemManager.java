package xyz.trrlgn.instagen;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ItemManager {

	public IG plugin;
	
	public ItemManager(IG pl) {
		plugin = pl;
	}

	private ItemStack cgw;
	private ItemStack sgw;
	private ItemStack ogw;
	
	public ItemStack cobbleGenWall() {
		cgw = new ItemStack(Material.COBBLESTONE);
		ItemMeta cgwMeta = cgw.getItemMeta();
		cgwMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("cobbleGen.name")));
		ArrayList<String> cgwLore = new ArrayList<String>();
		for(String cgwl : plugin.getConfig().getStringList("cobbleGen.lore")) {
			cgwLore.add(ChatColor.translateAlternateColorCodes('&', cgwl));
		}
		cgwMeta.setLore(cgwLore);
		cgw.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		cgwMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		cgw.setItemMeta(cgwMeta);

		return cgw;
	}
	
	public ItemStack sandGenWall() {
		sgw = new ItemStack(Material.SAND);
		ItemMeta sgwMeta = sgw.getItemMeta();
		sgwMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("sandGen.name")));
		ArrayList<String> sgwLore = new ArrayList<String>();
		for(String sgwl : plugin.getConfig().getStringList("sandGen.lore")) {
			sgwLore.add(ChatColor.translateAlternateColorCodes('&', sgwl));
		}
		sgwMeta.setLore(sgwLore);
		sgw.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		sgwMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		sgw.setItemMeta(sgwMeta);

		return sgw;
	}
	
	public ItemStack obsidianGenWall() {
		ogw = new ItemStack(Material.OBSIDIAN);
		ItemMeta ogwMeta = ogw.getItemMeta();
		ogwMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("obsidianGen.name")));
		ArrayList<String> ogwLore = new ArrayList<String>();
		for(String ogwl : plugin.getConfig().getStringList("obsidianGen.lore")) {
			ogwLore.add(ChatColor.translateAlternateColorCodes('&', ogwl));
		}
		ogwMeta.setLore(ogwLore);
		ogw.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ogwMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ogw.setItemMeta(ogwMeta);

		return ogw;
	}
	
	public void giveObsidianWall(Player p, int amount) {
		for(int i = 0; i < amount; i++) {
			p.getInventory().addItem(obsidianGenWall());
		}
	}
	
	public void giveSandWall(Player p, int amount) {
		for(int i = 0; i < amount; i++) {
			p.getInventory().addItem(sandGenWall());
		}
	}

	public void giveCobbleWall(Player p, int amount) {
		for(int i = 0; i < amount; i++) {
			p.getInventory().addItem(cobbleGenWall());
		}
	}
}
