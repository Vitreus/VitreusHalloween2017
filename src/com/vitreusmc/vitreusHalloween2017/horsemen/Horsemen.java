package com.vitreusmc.vitreusHalloween2017.horsemen;

import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import com.vitreusmc.vitreusHalloween2017.VitreusHalloween2017;

public class Horsemen {

	private JavaPlugin plugin = JavaPlugin.getPlugin(VitreusHalloween2017.class);
	
	private Location spawnLocation;
	private LivingEntity horse;
	private LivingEntity rider;
	private BossBar bar;
	
	public Horsemen(Location spawnLocation, LivingEntity horse, LivingEntity rider, BossBar bar) {
		horse.addPassenger(rider);
		
		this.spawnLocation = spawnLocation;
		this.horse = horse;
		this.rider = rider;
		this.bar = bar;
	}
	
	public void updateBar() {
		bar.setProgress(rider.getHealth() / rider.getMaxHealth());
		
		if (rider.isDead() || rider == null) {
			bar.setVisible(false);
			bar.removeAll();
			
			horse.setInvulnerable(false);
		}
	}
	
	public void addTarget(Player player) {
		bar.addPlayer(player);
	}
	
	public LivingEntity getHorse() {
		return horse;
	}
	
	public LivingEntity getRider() {
		return rider;
	}
	
	public Location getSpawnLocation() {
		return spawnLocation;
	}
	
	public BossBar getBar() {
		return bar;
	}
}
