package com.vitreusmc.vitreusHalloween2017.timeline.trigger;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.vitreusmc.vitreusHalloween2017.VitreusHalloween2017;
import com.vitreusmc.vitreusHalloween2017.timeline.encounter.EncouragementEncounter;
import com.vitreusmc.vitreusHalloween2017.timeline.encounter.HungerEncounter;
import com.vitreusmc.vitreusHalloween2017.timeline.encounter.ServantEncounter;

import net.md_5.bungee.api.ChatColor;

public class SleepTrigger implements Listener {

	JavaPlugin plugin = JavaPlugin.getPlugin(VitreusHalloween2017.class);
	
	@EventHandler
	public void onPlayerSleep(PlayerBedEnterEvent event) {
		Player player = event.getPlayer();
		sendEncounter(player);		
	}
	
	private void sendEncounter(Player player) {
		Random random = new Random();
		
		switch (random.nextInt(4)) {
			case 0: {
				break;
			}
			case 1: {
				new EncouragementEncounter(player).start();
				break;
			}
			case 2: {
				new HungerEncounter(player).start();
				break;
			}
			case 3: {
				new ServantEncounter(player).start();
				break;
			}
		}
	}
}
