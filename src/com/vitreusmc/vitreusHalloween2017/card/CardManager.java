package com.vitreusmc.vitreusHalloween2017.card;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.vitreusmc.vitreusHalloween2017.VitreusHalloween2017;

public class CardManager {
	
	private static JavaPlugin plugin = JavaPlugin.getPlugin(VitreusHalloween2017.class);
	private static FileConfiguration mapsConfig = plugin.getConfig();
	private static HashMap<String, VitreusCard> cards = new HashMap<String, VitreusCard>();
	
	public static void loadCards() {
		List<Map<?, ?>> serializedCards = mapsConfig.getMapList("cards");
		
		for (Map<?, ?> serializedCard : serializedCards) {
			String label = (String) serializedCard.get("label");
			short id = Short.parseShort("" + serializedCard.get("id"));
			File imageFile = new File(plugin.getDataFolder(), (String) serializedCard.get("image"));
			
			cards.put(label, new VitreusCard(label, id, imageFile));
		}
	}
	
	public static void saveCards() {
		List<Map<String, Object>> serializedCards = new ArrayList<Map<String, Object>>();
		
		for (VitreusCard card : cards.values()) {
			serializedCards.add(card.serialize());
		}
		
		mapsConfig.set("cards", serializedCards);
		plugin.saveConfig();
	}
	
	public static void addCard(VitreusCard card) {
		cards.put(card.getLabel(), card);
	}
	
	public static VitreusCard getCard(String label) {
		return cards.get(label);
	}
	
}
