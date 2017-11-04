package com.vitreusmc.vitreusHalloween2017.timeline.encounter;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class NeighEncounter extends Encounter {

	public NeighEncounter(Player player) {
		super(player, 25);
	}
	
	@Override
	public void run() {
		Player player = getPlayer();
		Location location = player.getLocation();
		
		String message = createWhisper("*A whinney of a neigh sounds in the distance*");
		player.sendMessage(message);

		new BukkitRunnable() {
			@Override
			public void run() {
				player.playSound(location, Sound.ENTITY_HORSE_ANGRY, 75, (float) 0.75);
			}
		}.runTask(getPlugin());
	}

}
