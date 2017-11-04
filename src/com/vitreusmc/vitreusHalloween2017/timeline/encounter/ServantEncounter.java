package com.vitreusmc.vitreusHalloween2017.timeline.encounter;

import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender.Spigot;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vex;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class ServantEncounter extends Encounter {

	public ServantEncounter(Player player) {
		super(player, 25);
	}

	@Override
	public void run() {
		Player player = getPlayer();
		JavaPlugin plugin = getPlugin();
		Location location = player.getLocation();
		World world = player.getWorld();
		
		String message = createWhisper("Hee... A servant for the humblest of my own! \n*Cackles Echo*");
		player.sendMessage(message);

		BukkitRunnable particleTask = new BukkitRunnable() {
			@Override
			public void run() {
				world.spawnParticle(Particle.PORTAL, location, 32, 0.5, 0.5, 0.5);
			}
		};
		particleTask.runTaskTimerAsynchronously(plugin, 0, 5);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				Vex vex = (Vex) world.spawnEntity(location, EntityType.VEX);
				vex.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 90, 1));

				player.playSound(location, Sound.BLOCK_END_PORTAL_SPAWN, SoundCategory.MASTER, 100, 1);
				
				particleTask.cancel();
				
				new BukkitRunnable() {
					@Override
					public void run() {
						vex.remove();
					}
				}.runTaskLater(plugin, 90);
			}
		}.runTaskLater(plugin, 120);
	}

}
