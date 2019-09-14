package lolimi.HnT.util;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
	
	ItemStack itemStack;
	ItemMeta meta;
	
	public ItemBuilder(Material material) {
		itemStack = new ItemStack(material);
		meta = itemStack.getItemMeta();
	}
	
	public ItemBuilder setMaterial(Material mat) {
		itemStack = new ItemStack(mat);
		meta = itemStack.getItemMeta();
		return this;
	}
	
	public ItemBuilder setName(String name) {
		meta.setDisplayName(name);
		return this;
	}
	
	public ItemBuilder setLore(String...lore) {
		List<String> list = Arrays.asList(lore);
		meta.setLore(list);
		return this;
	}
	
	public ItemBuilder setAmount(int amount) {
		itemStack.setAmount(amount);
		return this;
	}
	
	public ItemStack build() {
		itemStack.setItemMeta(meta);
		return itemStack;
	}
	
	
	public static ItemStack getOutfitSelector() {
		ItemStack outfitSelector = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemMeta m = outfitSelector.getItemMeta();
		m.setDisplayName("§aClick to select your outfit");
		outfitSelector.setItemMeta(m);
		return outfitSelector;
	}
	

}
