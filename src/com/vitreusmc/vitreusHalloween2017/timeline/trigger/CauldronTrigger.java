package com.vitreusmc.vitreusHalloween2017.timeline.trigger;

import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.vitreusmc.vitreusHalloween2017.VitreusHalloween2017;
import com.vitreusmc.vitreusHalloween2017.blessing.BlessingController;
import com.vitreusmc.vitreusHalloween2017.horsemen.MoriHorsemen;

public class CauldronTrigger implements Listener {

	private static JavaPlugin plugin = JavaPlugin.getPlugin(VitreusHalloween2017.class);
	
	@EventHandler
	public void onItemThrown(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		Item item = event.getItemDrop();
		
		new BukkitRunnable() {
			@Override
			public void run() {
				Location itemLocation = item.getLocation();
				Block blockAtLocation = itemLocation.getBlock();
								
				if (!blockAtLocation.getType().equals(Material.CAULDRON)) {
					return;
				}

				if (!(blockAtLocation.getWorld().getTime() >= 12966.6 && blockAtLocation.getWorld().getTime() <= 22800)) {
					return;
				}
				
				if (blockAtLocation.getLightFromSky() < 13) {
					return;
				}
				
				if (blockAtLocation.getState().getRawData() < 2) {
					return;
				}
				
				if (!item.getItemStack().getType().equals(Material.ROTTEN_FLESH)) {
					return;
				}
				
				Location cauldronLocation = blockAtLocation.getLocation().add(new Vector(0.5, 1, 0.5));
				World world = cauldronLocation.getWorld();
				Player thrower = null;

				for (Entity entity : world.getNearbyEntities(itemLocation, 32, 32, 32)) {
					if (entity instanceof Player) {
						thrower = (Player) entity;
					}
				}
				
				if (BlessingController.getBlessing(thrower) <= 450) {
					return;
				}

				BukkitRunnable cauldronParticlesTask = new BukkitRunnable() {
					@Override
					public void run() {
						world.spawnParticle(Particle.WATER_SPLASH, cauldronLocation, 24, 0.2, 0.4, 0.2);
						world.spawnParticle(Particle.VILLAGER_ANGRY, cauldronLocation, 1, 0.4, 0.5, 0.4);
					}
				};
				cauldronParticlesTask.runTaskTimerAsynchronously(plugin, 0, 5);
				
				final Player player = thrower;
				
				BukkitRunnable summonTask = new BukkitRunnable() {
					@Override
					public void run() {
						world.strikeLightningEffect(cauldronLocation);
						
						MoriHorsemen horsemen = MoriHorsemen.spawn(cauldronLocation);
						
						cauldronParticlesTask.cancel();										

						BukkitRunnable bossBarUpdater = new BukkitRunnable() {
							@Override
							public void run() {
								horsemen.updateBar();
								
								for (Entity entity : horsemen.getRider().getNearbyEntities(50, 50, 50)) {

									if (entity instanceof Player) {
										horsemen.addTarget((Player) entity);
									}
								}
								
								if (horsemen.getRider().isDead() || horsemen.getRider() == null) {
									this.cancel();
									BlessingController.setBlessing(player, -1);
								}
							}
						};
						bossBarUpdater.runTaskTimerAsynchronously(plugin, 20, 10);

					}
				};				
				summonTask.runTaskLater(plugin, 150);
			}
		}.runTaskLaterAsynchronously(plugin, 30);
	}
	
}
