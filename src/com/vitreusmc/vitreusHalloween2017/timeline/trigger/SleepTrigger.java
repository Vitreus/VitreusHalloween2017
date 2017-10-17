package com.vitreusmc.vitreusHalloween2017.timeline.trigger;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.vitreusmc.vitreusHalloween2017.VitreusHalloween2017;
import com.vitreusmc.vitreusHalloween2017.blessing.BlessingController;
import com.vitreusmc.vitreusHalloween2017.timeline.encounter.BatsEncounter;
import com.vitreusmc.vitreusHalloween2017.timeline.encounter.EncouragementEncounter;
import com.vitreusmc.vitreusHalloween2017.timeline.encounter.GluttonyEncounter;
import com.vitreusmc.vitreusHalloween2017.timeline.encounter.HungerEncounter;
import com.vitreusmc.vitreusHalloween2017.timeline.encounter.ServantEncounter;
import com.vitreusmc.vitreusHalloween2017.timeline.encounter.SymbolsEncounter;

public class SleepTrigger implements Listener {

	JavaPlugin plugin = JavaPlugin.getPlugin(VitreusHalloween2017.class);
	
	@EventHandler
	public void onPlayerSleep(PlayerBedEnterEvent event) {
		Player player = event.getPlayer();
		
		if (BlessingController.getBlessing(player) <= 2.5) {
			sendLevelOneEncounter(player);
		}
		
		if (BlessingController.getBlessing(player) >= 2.6) {
			sendLevelTwoEncounter(player);					
		}
	}
	
	private void sendLevelOneEncounter(Player player) {
		Random random = new Random();
		
		if (random.nextBoolean()) {
			return;
		}
		
		switch (random.nextInt(3)) {
			case 0: {
				new EncouragementEncounter(player).start();
				break;
			}
			case 1: {
				new HungerEncounter(player).start();
				break;
			}
			case 2: {				
				new BatsEncounter(player).start();
				break;
			}
		}
	}
	
	private void sendLevelTwoEncounter(Player player) {
		Random random = new Random();
		
		if (random.nextBoolean()) {
			return;
		}
		
		switch (random.nextInt(3)) {
			case 0: {
				new SymbolsEncounter(player).start();
				break;
			}
			case 1: {
				new GluttonyEncounter(player).start();
				break;
			}
			case 2: {
				new ServantEncounter(player).start();
				break;
			}
		}
	}
}
