package com.vitreusmc.vitreusHalloween2017.timeline.encounter;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.vitreusmc.vitreusHalloween2017.VitreusHalloween2017;
import com.vitreusmc.vitreusHalloween2017.blessing.BlessingController;

import net.md_5.bungee.api.ChatColor;

public abstract class Encounter extends BukkitRunnable {

	private JavaPlugin plugin = JavaPlugin.getPlugin(VitreusHalloween2017.class);

	private Player player;
	private int blessing;
	
	public Encounter(Player player, int blessing) {
		this.player = player;
		this.blessing = blessing;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public double getBlessing() {
		return blessing;
	}
	
	public void setBlessing(int blessing) {
		this.blessing = blessing;
	}
	
	public JavaPlugin getPlugin() {
		return plugin;
	}
	
	public void start() {
		this.runTask(plugin);
		addBlessing();
	}
	
	public void addBlessing() {	
		BlessingController.addBlessing(player, blessing);
	}
	
	public String createWhisper(String message) {
		return ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + message ;
	}

}
