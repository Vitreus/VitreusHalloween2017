package com.vitreusmc.vitreusHalloween2017.timeline.encounter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.vitreusmc.vitreusHalloween2017.card.CardManager;
import com.vitreusmc.vitreusHalloween2017.card.VitreusCard;

import net.md_5.bungee.api.ChatColor;

public class CallingEncounter extends Encounter {

	public CallingEncounter(Player player) {
		super(player, 25);
	}

	@Override
	public void run() {
		Player player = getPlayer();
		JavaPlugin plugin = getPlugin();
		World world = player.getWorld();
		Location location = player.getBedSpawnLocation();
		VitreusCard bubbleBoilCard = CardManager.getCard("bubbleBoilCard");
		List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GOLD + "Halloween 2017");
		ItemStack bubbleBoilMap = new ItemStack(Material.MAP, 1, bubbleBoilCard.getID());
		MapMeta bubbleBoilCardMeta = (MapMeta) bubbleBoilMap.getItemMeta();
			bubbleBoilCardMeta.setDisplayName(ChatColor.DARK_PURPLE + "Bubble and Boil Card");
			bubbleBoilCardMeta.setLore(lore);
		bubbleBoilMap.setItemMeta(bubbleBoilCardMeta);
		
		new BukkitRunnable() {
			@Override
			public void run() {				
				player.sendMessage(createWhisper("Boil, Bubble,\nBubble up some trouble..."));
				
				new BukkitRunnable() {
					@Override
					public void run() {
						player.playSound(player.getBedSpawnLocation(), Sound.ENTITY_ZOMBIE_AMBIENT, 100, (float) 0.2);
					}
				}.runTask(plugin);
				
				if (player.hasMetadata("halloween2017.bubbleBoilMap")) {
					return;
				}
				
				world.dropItem(location, bubbleBoilMap);
				player.setMetadata("halloween2017.bubbleBoilMap", new FixedMetadataValue(plugin, true));
			}
		}.runTask(plugin);
	}

}
