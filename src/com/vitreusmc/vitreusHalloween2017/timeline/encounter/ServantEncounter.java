package com.vitreusmc.vitreusHalloween2017.timeline.encounter;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ServantEncounter extends Encounter {

	public ServantEncounter(Player player) {
		super(player, 0.25);
	}

	@Override
	public void run() {
		Player player = getPlayer();
		JavaPlugin plugin = getPlugin();
		Location location = player.getLocation();
		World world = player.getWorld();
		
		String message = createWhisper("Hee... A servant for the humblest of my own! \n*Cackles Echo*");
		player.sendMessage(message);

		BukkitRunnable particleTask = new BukkitRunnable() {
			@Override
			public void run() {
				world.spawnParticle(Particle.PORTAL, location, 32, 0.5, 0.5, 0.5);
			}
		};
		particleTask.runTaskTimerAsynchronously(plugin, 0, 5);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				world.spawnEntity(location, EntityType.VEX);
				player.playSound(location, Sound.BLOCK_END_PORTAL_SPAWN, SoundCategory.MASTER, 100, 1);
				particleTask.cancel();
			}
		}.runTaskLater(plugin, 120);
	}

}
