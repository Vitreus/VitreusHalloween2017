package com.vitreusmc.vitreusHalloween2017;

import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.vitreusmc.vitreusHalloween2017.blessing.BlessingCommand;
import com.vitreusmc.vitreusHalloween2017.blessing.BlessingController;
import com.vitreusmc.vitreusHalloween2017.card.CardManager;
import com.vitreusmc.vitreusHalloween2017.timeline.trigger.CauldronTrigger;
import com.vitreusmc.vitreusHalloween2017.timeline.trigger.FleshTrigger;
import com.vitreusmc.vitreusHalloween2017.timeline.trigger.SleepTrigger;

public class VitreusHalloween2017 extends JavaPlugin {

	private Server server = getServer();
	private FileConfiguration config = getConfig();
	
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
		boolean eventActive = config.getBoolean("active", true);

		if (eventActive) {
			server.getPluginManager().registerEvents(new SleepTrigger(), this);
			server.getPluginManager().registerEvents(new FleshTrigger(), this);
			server.getPluginManager().registerEvents(new CauldronTrigger(), this);
		}
		
		server.getPluginManager().registerEvents(new BlessingController(), this);

	}

}
