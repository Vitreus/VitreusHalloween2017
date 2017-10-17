package com.vitreusmc.vitreusHalloween2017.blessing;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import com.vitreusmc.vitreusHalloween2017.VitreusHalloween2017;

public class BlessingController implements Listener {

	private static JavaPlugin plugin = JavaPlugin.getPlugin(VitreusHalloween2017.class);
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		try {
			player.getMetadata("halloween2017.blessing").get(0).asDouble();
		} catch (Exception exception) {
			player.setMetadata("halloween2017.blessing", new FixedMetadataValue(plugin, 0));
		}
	}
	
	public static void removeBlessing(Player player, double amount) {
		double blessing = player.getMetadata("halloween2017.blessing").get(0).asDouble();
		player.setMetadata("halloween2017.blessing", new FixedMetadataValue(plugin, blessing - amount));
	}
	
	public static void addBlessing(Player player, double amount) {
		double blessing = player.getMetadata("halloween2017.blessing").get(0).asDouble();
		player.setMetadata("halloween2017.blessing", new FixedMetadataValue(plugin, blessing + amount));
	}
	
	public static double getBlessing(Player player) {
		return player.getMetadata("halloween2017.blessing").get(0).asDouble();
	}
	
}
