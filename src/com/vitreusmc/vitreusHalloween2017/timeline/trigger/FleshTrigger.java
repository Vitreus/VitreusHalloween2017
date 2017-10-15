package com.vitreusmc.vitreusHalloween2017.timeline.trigger;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import com.vitreusmc.vitreusHalloween2017.timeline.encounter.StrengthEncounter;

public class FleshTrigger implements Listener {

	@EventHandler
	public void onFleshEaten(PlayerItemConsumeEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getItem();
		
		if (item.getType().equals(Material.ROTTEN_FLESH)) {
			new StrengthEncounter(player).start();
		}
	}
}
