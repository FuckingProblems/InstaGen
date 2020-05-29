package xyz.trrlgn.instagen;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockPlaceListener implements Listener {

	public IG plugin;

	public BlockPlaceListener(IG pl) {
		plugin = pl;
	}

	@EventHandler
	public void onPlace(final BlockPlaceEvent e) {
		if(e.getItemInHand().getItemMeta().equals(plugin.im.cobbleGenWall().getItemMeta())) {
			placedCobble(e);
		} else if(e.getItemInHand().getItemMeta().equals(plugin.im.sandGenWall().getItemMeta())) {
			placedSand(e);
		} else if(e.getItemInHand().getItemMeta().equals(plugin.im.obsidianGenWall().getItemMeta())) {
			placedObsidian(e);
		}
	}

	@SuppressWarnings("deprecation")
	public void placedCobble(BlockPlaceEvent e) {
		if(e.isCancelled() == true) {
			return;
		}
		if(!canPlayerAfford(e.getPlayer(), plugin.getConfig().getInt("cobbleGen.price"))) {
			e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.cannotAfford")).replaceAll("%price%", String.valueOf(plugin.getConfig().getInt("cobbleGen.price"))));
			e.setCancelled(true);
			return;
		}
		e.getPlayer().setItemInHand(plugin.im.cobbleGenWall());
		IG.getEconomy().withdrawPlayer(e.getPlayer(), plugin.getConfig().getInt("cobbleGen.price"));
		e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.paid")).replaceAll("%price%", String.valueOf(plugin.getConfig().getInt("cobbleGen.price"))));
		final Location l = e.getBlock().getLocation();
		new BukkitRunnable() {
			Location l1 = l;
			@Override
			public void run() {
				l1 = l1.getBlock().getRelative(BlockFace.DOWN).getLocation();
				if(plugin.isBlockUnderAir(l1.getBlock().getRelative(BlockFace.UP)) == true) {
					l1.getBlock().setType(Material.COBBLESTONE);
				} else {
					cancel();
					return;
				}

			}
		}.runTaskTimer(plugin, 0L, (long)plugin.getConfig().getInt("cobbleGen.ticksPerGen"));
	}

	@SuppressWarnings("deprecation")
	public void placedSand(BlockPlaceEvent e) {
		if(e.isCancelled() == true) {
			return;
		}
		if(!canPlayerAfford(e.getPlayer(), plugin.getConfig().getInt("sandGen.price"))) {
			e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.cannotAfford")).replaceAll("%price%", String.valueOf(plugin.getConfig().getInt("sandGen.price"))));
			e.setCancelled(true);
			return;
		}
		e.getPlayer().setItemInHand(plugin.im.sandGenWall());
		IG.getEconomy().withdrawPlayer(e.getPlayer(), plugin.getConfig().getInt("sandGen.price"));
		e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.paid")).replaceAll("%price%", String.valueOf(plugin.getConfig().getInt("sandGen.price"))));
		final Location l = e.getBlock().getLocation();
		if(l.getBlock().getRelative(BlockFace.DOWN).isEmpty() == true) {
			l.getBlock().getRelative(BlockFace.DOWN).setType(Material.BARRIER);
		}
		new BukkitRunnable() {
			Location l1 = l;
			@Override
			public void run() {
				l1 = l1.getBlock().getRelative(BlockFace.DOWN).getLocation();
				if(l1.getBlock().getRelative(BlockFace.DOWN).isEmpty() == true) {
					l1.getBlock().setType(Material.SAND);
					l1.getBlock().getRelative(BlockFace.DOWN).setType(Material.BARRIER);
				} else {
					l1.getBlock().setType(Material.SAND);
					cancel();
					return;
				}

			}
		}.runTaskTimer(plugin, 0L, (long)plugin.getConfig().getInt("sandGen.ticksPerGen"));
	}

	@SuppressWarnings("deprecation")
	public void placedObsidian(BlockPlaceEvent e) {
		if(e.isCancelled() == true) {
			return;
		}
		if(!canPlayerAfford(e.getPlayer(), plugin.getConfig().getInt("obsidianGen.price"))) {
			e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.cannotAfford")).replaceAll("%price%", String.valueOf(plugin.getConfig().getInt("obsidianGen.price"))));
			e.setCancelled(true);
			return;
		}
		e.getPlayer().setItemInHand(plugin.im.obsidianGenWall());
		IG.getEconomy().withdrawPlayer(e.getPlayer(), plugin.getConfig().getInt("obsidianGen.price"));
		e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message.paid")).replaceAll("%price%", String.valueOf(plugin.getConfig().getInt("obsidianGen.price"))));
		final Location l = e.getBlock().getLocation();
		new BukkitRunnable() {
			Location l1 = l;
			@Override
			public void run() {
				l1 = l1.getBlock().getRelative(BlockFace.DOWN).getLocation();
				if(plugin.isBlockUnderAir(l1.getBlock().getRelative(BlockFace.UP)) == true) {
					l1.getBlock().setType(Material.OBSIDIAN);
				} else {
					cancel();
					return;
				}

			}
		}.runTaskTimer(plugin, 0L, (long)plugin.getConfig().getInt("obsidianGen.ticksPerGen"));
	}
	
	private boolean canPlayerAfford(Player p, int price) {
		if(IG.getEconomy().getBalance(p) >= price) {
			return true;
		}
		return false;
	}

}
