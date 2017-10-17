package com.vitreusmc.vitreusHalloween2017.timeline.encounter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class BatsEncounter extends Encounter {

	public BatsEncounter(Player player) {
		super(player, 0.25);
	}

	@Override
	public void run() {
		Player player = getPlayer();
		JavaPlugin plugin = getPlugin();
		
		new BukkitRunnable() {
			@Override
			public void run() {
				List<Entity> bats = new ArrayList<Entity>();
				
				player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 100, 1);
				
				for (int i = 1; i <= 4; i++) {
					Entity bat = player.getWorld().spawnEntity(player.getLocation(), EntityType.BAT);					
					bats.add(bat);
				}

				player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 40, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
				
				new BukkitRunnable() {
					@Override
					public void run() {
						for (Entity bat : bats) {
							bat.remove();
						}
					}
				}.runTaskLater(plugin, 100);
			}
		}.runTask(plugin);
	}

}
