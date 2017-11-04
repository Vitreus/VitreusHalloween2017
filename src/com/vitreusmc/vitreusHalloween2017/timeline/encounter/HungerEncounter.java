package com.vitreusmc.vitreusHalloween2017.timeline.encounter;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class HungerEncounter extends Encounter {

	public HungerEncounter(Player player) {
		super(player, 25);
	}

	@Override
	public void run() {
		Player player = getPlayer();
		JavaPlugin plugin = getPlugin();
		Location location = player.getLocation();
		
		String message = createWhisper("*Deep and Desperate Breathing*");
		player.sendMessage(message);	
		
		new BukkitRunnable() {
			@Override
			public void run() {
				String message = createWhisper("MORE! More!... Sleep! I command it of thee!");
				player.sendMessage(message);						

				player.playSound(location, Sound.ENTITY_ZOMBIE_PIG_DEATH, 100, (float) 0.5);
				player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 10, false), true);
				player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 200, 1, false), true);
				player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 175, 1, false), true);
			}
		}.runTaskLater(plugin, 50);
	}

}
