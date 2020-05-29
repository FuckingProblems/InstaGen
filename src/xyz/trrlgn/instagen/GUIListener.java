package xyz.trrlgn.instagen;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {

	public IG plugin;
	
	public GUIListener(IG pl) {
		plugin = pl;
	}
	
	@EventHandler
	public void onGUIClick(InventoryClickEvent e) {
		if(!e.getInventory().equals(plugin.gui.inv)) {
			return;
		}
		e.setCancelled(true);
		if(e.getRawSlot() < 3) {
			e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
		}
		e.getWhoClicked().closeInventory();
	}
	
}
