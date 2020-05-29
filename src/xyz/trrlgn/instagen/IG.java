package xyz.trrlgn.instagen;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class IG extends JavaPlugin {

	public IG plugin;
	public ItemManager im;
	public BlockPlaceListener bpl;
	public GUI gui;
	public GUIListener gl;
	
	private static Economy econ = null;
	
	@Override
	public void onEnable() {
		setupEconomy();
		saveDefaultConfig();
		im = new ItemManager(this);
		bpl = new BlockPlaceListener(this);
		gui = new GUI(this);
		gui.loadInventory();
		gl = new GUIListener(this);
		registerEvents(this, new BlockPlaceListener(this), new GUIListener(this));
		getCommand("instagen").setExecutor(new GenCommands(this));
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}
	
	public boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public boolean isBlockUnderAir(Block b) {
		if(b.getRelative(BlockFace.DOWN).getLocation().getBlock().isEmpty() == true) {
			return true;
		}
		return false;
	}
	
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    
    public static Economy getEconomy() {
        return econ;
    }
	
}
