package lolimi.HnT.outfits;

import java.util.HashMap;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Outfit {

	public static HashMap<Player, Outfit> outfits= new HashMap<Player, Outfit>();
	
	public static void addOutfit(Player player, Outfit outfit) {
		outfits.put(player, outfit);
	}
	
	public static Outfit getOutfit(Player player) {
		return outfits.get(player);
	}
	
	private Color color;
	private ItemStack head;
	
	public Outfit randomColor() {
		color = Color.fromRGB(Math.round(Math.round(Math.random()*255)), Math.round(Math.round(Math.random()*255)), Math.round(Math.round(Math.random()*255)));
		return this;
	}
	
	public Outfit setHead(ItemStack head) {
		this.head = head;
		return this;
	}
	
	public Outfit setHead(Player p) {
		
		ItemStack head = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta m = (SkullMeta) head.getItemMeta();
		m.setOwningPlayer(p);
		head.setItemMeta(m);
		
		this.head = head;
		return this;
	}
	
	public Outfit setColor(Color color) {
		this.color = color;
		return this;
	}
	
	public ItemStack getHead() {
		return head;
	}
	
	public Color getColor() {
		return color;
	}

}
