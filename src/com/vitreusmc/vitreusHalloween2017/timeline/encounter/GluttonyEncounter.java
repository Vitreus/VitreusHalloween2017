package com.vitreusmc.vitreusHalloween2017.timeline.encounter;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.vitreusmc.vitreusHalloween2017.card.CardManager;
import com.vitreusmc.vitreusHalloween2017.card.VitreusCard;

public class GluttonyEncounter extends Encounter {

	public GluttonyEncounter(Player player) {
		super(player, 0.25);
	}

	@Override
	public void run() {
		Player player = getPlayer();
		JavaPlugin plugin = getPlugin();
		World world = player.getWorld();
		Location location = player.getBedSpawnLocation();
		VitreusCard gluttonyCard = CardManager.getCard("gluttonyCard");
		ItemStack gluttonyCardMap = new ItemStack(Material.MAP, 1, gluttonyCard.getID());		
		
		new BukkitRunnable() {
			@Override
			public void run() {				
				player.sendMessage(createWhisper("Though dead,\nIt is said,\nThat the flesh of the Lord,\nIs ripe with reward.\n\nEat my child..."));
				
				new BukkitRunnable() {
					@Override
					public void run() {
						player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
						player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 200, 1));
						player.playSound(player.getBedSpawnLocation(), Sound.ENTITY_ZOMBIE_AMBIENT, 100, (float) 0.2);
					}
				}.runTask(plugin);
				
				if (player.hasMetadata("halloween2017.gluttonyMap")) {
					return;
				}
				
				world.dropItem(location, gluttonyCardMap);
				player.setMetadata("halloween2017.gluttonyMap", new FixedMetadataValue(plugin, true));
			}
		}.runTask(plugin);
	}

}
