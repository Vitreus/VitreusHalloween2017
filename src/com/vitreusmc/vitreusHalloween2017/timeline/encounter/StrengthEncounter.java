package com.vitreusmc.vitreusHalloween2017.timeline.encounter;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class StrengthEncounter extends Encounter {

	public StrengthEncounter(Player player) {
		super(player, 0.5);
	}
	
	@Override
	public void run() {
		Player player = getPlayer();
		Location location = player.getLocation();
		
		String message = createWhisper("Yes! My child! Feast! Feast on the flesh of your Father! \nIn it... We grow STRONG!");
		player.sendMessage(message);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				player.spawnParticle(Particle.PORTAL, location, 16);
				player.playSound(location, Sound.AMBIENT_CAVE, 100, 1);
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1, false), true);	
			}
		}.runTask(getPlugin());
	}
}
