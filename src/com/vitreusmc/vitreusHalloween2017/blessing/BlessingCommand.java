package com.vitreusmc.vitreusHalloween2017.blessing;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class BlessingCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String playerName;
		Player player;
		double blessing;
		
		if (args.length != 1) {
			return false;
		}
		
		playerName = args[0];
		player = Bukkit.getPlayer(playerName);
		blessing = BlessingController.getBlessing(player);
		
		sender.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Blessing: " + ChatColor.RESET + "" + ChatColor.DARK_PURPLE + blessing);
		
		return true;
	}

}
