package com.vitreusmc.vitreusHalloween2017.horsemen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.vitreusmc.vitreusHalloween2017.VitreusHalloween2017;

import net.md_5.bungee.api.ChatColor;

public class ConquestHorsemen extends Horsemen {
	
	private static JavaPlugin plugin = JavaPlugin.getPlugin(VitreusHalloween2017.class);
	
	public ConquestHorsemen(Location location, Horse horse, Skeleton rider, BossBar bar) {
		super(location, horse, rider, bar);
	}

	public static ConquestHorsemen spawn(Location location) {
		World world = location.getWorld();
		
		Horse horse = (Horse) world.spawnEntity(location, EntityType.HORSE);
			horse.setAdult();
			horse.setBreed(false);
			horse.setTamed(true);
			horse.setColor(Color.WHITE);
			horse.setStyle(Style.NONE);
			horse.getInventory().setArmor(new ItemStack(Material.GOLD_BARDING));
			horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
			horse.setInvulnerable(true);
			
		Skeleton rider = (Skeleton) world.spawnEntity(location, EntityType.SKELETON);
			ItemStack helmet = new ItemStack(Material.GOLD_HELMET);
				helmet.addEnchantment(Enchantment.DURABILITY, 3);
			List<String> helmetLore = new ArrayList<String>();
				helmetLore.add("Once worn by the Horseman Conquest");
			ItemMeta helmetMeta = helmet.getItemMeta();			
				helmetMeta.setDisplayName(ChatColor.GOLD + "Shining Helm of the Horseman Conquest");
				helmetMeta.setLore(helmetLore);
				helmetMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				helmetMeta.setUnbreakable(true);
			helmet.setItemMeta(helmetMeta);
			rider.getEquipment().setHelmet(helmet);
			rider.getEquipment().setHelmetDropChance(100);
			rider.getEquipment().setItemInOffHand(new ItemStack(Material.BOW));
			rider.getEquipment().setItemInMainHand(new ItemStack(Material.GOLD_SWORD));
			rider.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(160);
			rider.setHealth(rider.getMaxHealth());
			rider.setCustomName("The Horseman of Conquest");
			rider.setCustomNameVisible(true);
			
		BossBar bar = Bukkit.getServer().createBossBar("- Horseman Conquest -", BarColor.YELLOW, BarStyle.SOLID, BarFlag.DARKEN_SKY, BarFlag.CREATE_FOG, BarFlag.PLAY_BOSS_MUSIC);
		bar.setVisible(true);
		ConquestHorsemen horsemen = new ConquestHorsemen(location, horse, rider, bar);
		
		BukkitRunnable spawnMinionsTask = new BukkitRunnable() {
			
			@Override
			public void run() {
				horsemen.spawnMinion();
			}
		};
		spawnMinionsTask.runTaskTimer(plugin, 0, 40);

		BukkitRunnable stopSpawnMinionsTask = new BukkitRunnable() {
			
			@Override
			public void run() {
				spawnMinionsTask.cancel();
			}
		};
		stopSpawnMinionsTask.runTaskLater(plugin, 125);
		
		return horsemen;
	}

	public void spawnMinion() {
		Random random = new Random();
		Location spawnLocation = getSpawnLocation().clone();
		World world = spawnLocation.getWorld();
		int xAddon = random.nextInt(5) + 1;
		int zAddon = random.nextInt(5) + 1;
		
		if (random.nextBoolean()) {
			xAddon = -xAddon;
		}
		
		if (random.nextBoolean()) {
			zAddon = -zAddon;
		}
		
		spawnLocation.add(xAddon, 0, zAddon);				
		
		BukkitRunnable particlesTask = new BukkitRunnable() {
			
			@Override
			public void run() {
				world.spawnParticle(Particle.MOB_APPEARANCE, spawnLocation, 4, 0.5, 1.5, 0.5);
			}
		};
		particlesTask.runTaskTimer(plugin, 0, 10);
		
		BukkitRunnable summonTask = new BukkitRunnable() {
			
			@Override
			public void run() {
				Skeleton minion = (Skeleton) world.spawnEntity(spawnLocation, EntityType.SKELETON);
				ItemStack minionHelmet = new ItemStack(Material.LEATHER_HELMET);
					minionHelmet.addEnchantment(Enchantment.DURABILITY, 3);
				minion.getEquipment().setHelmet(minionHelmet);
				minion.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
				minion.setCustomName("Horseman's Minion");
				
				particlesTask.cancel();
			}
		};
		summonTask.runTaskLater(plugin, 40);
	}
	
}
