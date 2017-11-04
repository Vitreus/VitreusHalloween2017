package com.vitreusmc.vitreusHalloween2017.timeline.encounter;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class BeatEncounter extends Encounter {

	public BeatEncounter(Player player) {
		super(player, 25);
	}
	
	@Override
	public void run() {
		Player player = getPlayer();
		Location location = player.getLocation();
		
		String message = createWhisper("*The rhythmic sound of a galloping horse lulls in the distance*");
		player.sendMessage(message);

		new BukkitRunnable() {
			@Override
			public void run() {
				player.playSound(location, Sound.ENTITY_HORSE_GALLOP, 75, (float) 0.75);
			}
		}.runTask(getPlugin());
	}

}
