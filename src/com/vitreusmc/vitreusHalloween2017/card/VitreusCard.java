package com.vitreusmc.vitreusHalloween2017.card;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MapView.Scale;

public class VitreusCard implements ConfigurationSerializable {

	private MapView view;
	private MapRenderer renderer;
	private short ID;
	private String label;
	private File imageFile;
	
	public VitreusCard(String label, short ID, File imageFile) {
		this.label = label;
		this.ID = ID;
		this.imageFile = imageFile;
		this.renderer = new MapRenderer() {	
			@Override
			public void render(MapView map, MapCanvas canvas, Player player) {
				Image image;
				
				try {
					image = ImageIO.read(imageFile);
					canvas.drawImage(0, 0, image);
				} catch (IOException exception) {
					
				}
			}
		};
		
		if (ID == 0) {
			this.view = Bukkit.getServer().createMap(Bukkit.getWorlds().get(0));
			this.ID = view.getId();
			
			for (MapRenderer render : view.getRenderers()) {
				view.removeRenderer(render);
			}
			
			view.setScale(Scale.CLOSEST);
			view.addRenderer(renderer);	
			
			return;
		}
	
		this.view = Bukkit.getServer().getMap(ID);				
	}
	
	public MapView getView() {
		return view;
	}
	
	public void setView(MapView view) {
		this.view = view;
	}
	
	public MapRenderer getRenderer() {
		return renderer;
	}
	
	public void setRenderer(MapRenderer renderer) {
		this.renderer = renderer;
	}
	
	public short getID() {
		return ID;
	}
	
	public void setID(short iD) {
		ID = iD;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public Map<String, Object> serialize() {
		HashMap<String, Object> section = new HashMap<String, Object>();
		section.put("id", ID);
		section.put("label", label);
		section.put("image", imageFile.getAbsolutePath());
		
		return section;
	}
	
}
