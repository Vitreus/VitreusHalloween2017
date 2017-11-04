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
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.vitreusmc.vitreusHalloween2017.VitreusHalloween2017;

import net.md_5.bungee.api.ChatColor;

public class MoriHorsemen extends Horsemen {
	
	private static JavaPlugin plugin = JavaPlugin.getPlugin(VitreusHalloween2017.class);
	
	public MoriHorsemen(Location location, LivingEntity horse, LivingEntity rider, BossBar bar) {
		super(location, horse, rider, bar);
	}

	public static MoriHorsemen spawn(Location location) {
		World world = location.getWorld();
		
		ZombieHorse horse = (ZombieHorse) world.spawnEntity(location, EntityType.ZOMBIE_HORSE);
			horse.setAdult();
			horse.setBreed(false);
			horse.setTamed(true);
			horse.getInventory().setItem(0, new ItemStack(Material.SADDLE));
			horse.setInvulnerable(true);
			
		ZombieVillager rider = (ZombieVillager) world.spawnEntity(location, EntityType.ZOMBIE_VILLAGER);
			ItemStack axe = new ItemStack(Material.IRON_AXE);
			List<String> axeLore = new ArrayList<String>();
				axeLore.add(ChatColor.DARK_RED + "Axe once held by Alzzar, Lord of Mori and the Horseman of Death.");
			ItemMeta axeMeta = axe.getItemMeta();
				axeMeta.setDisplayName(ChatColor.DARK_PURPLE + "Hatchet of Mortality");
				axeMeta.setUnbreakable(true);
				axeMeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
				axeMeta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
				axeMeta.setLore(axeLore);
				axe.setItemMeta(axeMeta);	
			rider.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
			rider.getEquipment().setItemInOffHand(new ItemStack(Material.SHIELD));
			rider.getEquipment().setItemInMainHand(axe);
			rider.getEquipment().setItemInHandDropChance(100);
			rider.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(160);
			rider.setHealth(rider.getMaxHealth());
			rider.setCustomName("Alzzar");
			rider.setCustomNameVisible(true);
			rider.setBaby(false);
			rider.setVillagerProfession(Profession.BLACKSMITH);
			rider.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 3600, 1, false));

			
		BossBar bar = Bukkit.getServer().createBossBar(ChatColor.DARK_GREEN + "- Alzzar, Lord of Mori -", BarColor.PURPLE, BarStyle.SEGMENTED_20, BarFlag.CREATE_FOG, BarFlag.DARKEN_SKY, BarFlag.PLAY_BOSS_MUSIC);
		bar.setVisible(true);
		MoriHorsemen horsemen = new MoriHorsemen(location, horse, rider, bar);
		
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
				Vindicator minion = (Vindicator) world.spawnEntity(spawnLocation, EntityType.VINDICATOR);
				ItemStack minionHelmet = new ItemStack(Material.LEATHER_HELMET);
					minionHelmet.addEnchantment(Enchantment.DURABILITY, 3);
				minion.getEquipment().setHelmet(minionHelmet);
				minion.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_AXE));
				minion.setCustomName("Alzzarite");
				minion.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3600, 1, false));
				
				particlesTask.cancel();
			}
		};
		summonTask.runTaskLater(plugin, 40);
	}
	
}
