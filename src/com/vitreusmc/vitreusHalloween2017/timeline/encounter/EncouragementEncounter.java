package com.vitreusmc.vitreusHalloween2017.timeline.encounter;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class EncouragementEncounter extends Encounter {

	public EncouragementEncounter(Player player) {
		super(player, 25);
	}
	
	@Override
	public void run() {
		Player player = getPlayer();
		Location location = player.getLocation();
		
		String message = createWhisper("Yes! Sleep! \nSleep my child...");
		player.sendMessage(message);

		new BukkitRunnable() {
			@Override
			public void run() {
				player.playSound(location, Sound.BLOCK_WATER_AMBIENT, 75, (float) 0.75);
				player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200, 1, false), true);
			}
		}.runTask(getPlugin());
	}

}
