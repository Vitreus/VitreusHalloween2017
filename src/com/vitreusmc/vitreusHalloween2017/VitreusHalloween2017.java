package com.vitreusmc.vitreusHalloween2017;

import java.io.File;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import com.vitreusmc.vitreusHalloween2017.blessing.BlessingCommand;
import com.vitreusmc.vitreusHalloween2017.blessing.BlessingController;
import com.vitreusmc.vitreusHalloween2017.card.CardManager;
import com.vitreusmc.vitreusHalloween2017.card.VitreusCard;
import com.vitreusmc.vitreusHalloween2017.timeline.trigger.CauldronTrigger;
import com.vitreusmc.vitreusHalloween2017.timeline.trigger.FleshTrigger;
import com.vitreusmc.vitreusHalloween2017.timeline.trigger.SleepTrigger;

public class VitreusHalloween2017 extends JavaPlugin {

	Server server = getServer();
	
	@Override
	public void onEnable() {
		registerExecutors();
		registerListeners();
		
		CardManager.loadCards();
		CardManager.saveCards();
	}
	
	private void registerExecutors() {
		getCommand("blessing").setExecutor(new BlessingCommand());
	}
	
	private void registerListeners() {
		server.getPluginManager().registerEvents(new BlessingController(), this);
		server.getPluginManager().registerEvents(new SleepTrigger(), this);
		server.getPluginManager().registerEvents(new FleshTrigger(), this);
		server.getPluginManager().registerEvents(new CauldronTrigger(), this);
	}
	
}
