package com.vitreusmc.vitreusHalloween2017.timeline.encounter;

import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class SymbolsEncounter extends Encounter {

	public SymbolsEncounter(Player player) {
		super(player, 0.25);
	}

	@Override
	public void run() {
		Player player = getPlayer();
		JavaPlugin plugin = getPlugin();
		
		new BukkitRunnable() {
			@Override
			public void run() {
				player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 85, 1);
				player.giveExpLevels(1);
				
				for (int i = 1; i <= 16; i++) {
					player.getWorld().spawnEntity(player.getLocation(), EntityType.EXPERIENCE_ORB);					
				}
				
				player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
				player.sendTitle(ChatColor.LIGHT_PURPLE + "" + ChatColor.MAGIC + "Alzzar", ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "\"Alzarr\"", 20, 20, 20);
			}
		}.runTask(plugin);
	}

}
