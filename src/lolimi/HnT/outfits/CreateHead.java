package lolimi.HnT.outfits;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class CreateHead {
	
	private ItemStack head;
	private SkullMeta meta;
	
	public CreateHead() {
		head = new ItemStack(Material.PLAYER_HEAD);
		meta = (SkullMeta) head.getItemMeta();
	}
	
	public CreateHead setPlayer(OfflinePlayer player){
		meta.setOwningPlayer(player);
		meta.setDisplayName(player.getName());
		return this;
	}
	
	public ItemStack get() {
		head.setItemMeta(meta);
		return head;
	}
	
	

}
