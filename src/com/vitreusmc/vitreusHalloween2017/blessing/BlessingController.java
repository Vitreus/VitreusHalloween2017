package com.vitreusmc.vitreusHalloween2017.blessing;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.vitreusmc.vitreusHalloween2017.VitreusHalloween2017;

public class BlessingController implements Listener {

	private static JavaPlugin plugin = JavaPlugin.getPlugin(VitreusHalloween2017.class);
	private static Server server = Bukkit.getServer();
	private static Scoreboard scoreboard = server.getScoreboardManager().getMainScoreboard();
	private static Objective objective;
	
	@EventHandler
	public void onPluginEnable(PluginEnableEvent event) {
		
		if (!event.getPlugin().equals(plugin)) {
			return;
		}
		
		if (scoreboard.getObjective("blessing") == null) {
			scoreboard.registerNewObjective("blessing", "dummy");
		}
		
		objective = scoreboard.getObjective("blessing");
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		try {
			objective.getScore(player.getName());
		} catch (Exception exception) {
			objective.getScore(player.getName()).setScore(0);
		}
	}

	public static void setBlessing(Player player, int amount) {
		objective.getScore(player.getName()).setScore(amount);
	}
	
	public static void removeBlessing(Player player, int amount) {
		int blessing = getBlessing(player);
		objective.getScore(player.getName()).setScore(blessing - amount);
	}
	
	public static void addBlessing(Player player, int amount) {
		int blessing = getBlessing(player);
		objective.getScore(player.getName()).setScore(blessing + amount);
	}
	
	public static int getBlessing(Player player) {
		Score score = objective.getScore(player.getName());
		return score.getScore();
	}
	
}
