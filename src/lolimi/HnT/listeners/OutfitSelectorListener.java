package lolimi.HnT.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import lolimi.HnT.gameStates.LobbyState;
import lolimi.HnT.main.Main;
import lolimi.HnT.outfits.Heads;
import lolimi.HnT.outfits.Outfit;
import lolimi.HnT.util.ItemBuilder;

public class OutfitSelectorListener implements Listener{
	
	Main plugin;
	
	public OutfitSelectorListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if(!(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState)) return;
		if(e.getPlayer().getInventory().getItemInMainHand().isSimilar(ItemBuilder.getOutfitSelector())) {
			e.setCancelled(true);
			Inventory inv = createInv();
			e.getPlayer().openInventory(inv);
			
		}
	}
	
	@EventHandler
	public void onInInvClick(InventoryClickEvent e) {
		if(!(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState)) return;
		if(!e.getView().getTitle().equals("Select your outfit here")) return;
		if(e.getInventory().equals(e.getView().getBottomInventory())) return;
		e.setCancelled(true);
		if(e.getRawSlot() == 0 || e.getRawSlot() == 27) return;
		if(e.getRawSlot()<27) {
			if(Outfit.getOutfit((Player) e.getWhoClicked()) == null)
				Outfit.addOutfit((Player) e.getWhoClicked(), new Outfit().setHead(e.getCurrentItem()));
			else
				Outfit.getOutfit((Player) e.getWhoClicked()).setHead(e.getCurrentItem()); 
		}else {
			if(Outfit.getOutfit((Player) e.getWhoClicked()) == null)
				setColor((Player) e.getWhoClicked(), e.getCurrentItem(), false);
			else
				setColor((Player) e.getWhoClicked(), e.getCurrentItem(), true);
		}
	}
	
	private void setColor(Player p, ItemStack item, boolean already) {
		if(!already) {
			if(item.getType().equals(Material.LAPIS_LAZULI))
				Outfit.addOutfit(p, new Outfit().setColor(Color.BLUE));
			else if(item.getType().equals(Material.RED_DYE))
				Outfit.addOutfit(p, new Outfit().setColor(Color.RED));
			else if(item.getType().equals(Material.GREEN_DYE))
				Outfit.addOutfit(p, new Outfit().setColor(Color.GREEN));
			else if(item.getType().equals(Material.CYAN_DYE))
				Outfit.addOutfit(p, new Outfit().setColor(Color.fromRGB(0, 183, 235)));
			else if(item.getType().equals(Material.LIGHT_BLUE_DYE))
				Outfit.addOutfit(p, new Outfit().setColor(Color.fromRGB(0, 255, 255)));
			else if(item.getType().equals(Material.LIME_DYE))
				Outfit.addOutfit(p, new Outfit().setColor(Color.LIME));
			else if(item.getType().equals(Material.ORANGE_DYE))
				Outfit.addOutfit(p, new Outfit().setColor(Color.ORANGE));
			else if(item.getType().equals(Material.YELLOW_DYE))
				Outfit.addOutfit(p, new Outfit().setColor(Color.YELLOW));
			else if(item.getType().equals(Material.WHITE_DYE))
				Outfit.addOutfit(p, new Outfit().setColor(Color.WHITE));
			else if(item.getType().equals(Material.LIGHT_GRAY_DYE))
				Outfit.addOutfit(p, new Outfit().setColor(Color.fromRGB(211, 211, 211)));
			else if(item.getType().equals(Material.GRAY_DYE))
				Outfit.addOutfit(p, new Outfit().setColor(Color.GRAY));
			else if(item.getType().equals(Material.BLACK_DYE))
				Outfit.addOutfit(p, new Outfit().setColor(Color.BLACK));
		}else {
			if(item.getType().equals(Material.LAPIS_LAZULI))
				Outfit.addOutfit(p, Outfit.getOutfit(p).setColor(Color.BLUE));
			else if(item.getType().equals(Material.RED_DYE))
				Outfit.addOutfit(p, Outfit.getOutfit(p).setColor(Color.RED));
			else if(item.getType().equals(Material.GREEN_DYE))
				Outfit.addOutfit(p, Outfit.getOutfit(p).setColor(Color.GREEN));
			else if(item.getType().equals(Material.CYAN_DYE))
				Outfit.addOutfit(p, Outfit.getOutfit(p).setColor(Color.fromRGB(0, 183, 235)));
			else if(item.getType().equals(Material.LIGHT_BLUE_DYE))
				Outfit.addOutfit(p, Outfit.getOutfit(p).setColor(Color.fromRGB(0, 255, 255)));
			else if(item.getType().equals(Material.LIME_DYE))
				Outfit.addOutfit(p, Outfit.getOutfit(p).setColor(Color.LIME));
			else if(item.getType().equals(Material.ORANGE_DYE))
				Outfit.addOutfit(p, Outfit.getOutfit(p).setColor(Color.ORANGE));
			else if(item.getType().equals(Material.YELLOW_DYE))
				Outfit.addOutfit(p, Outfit.getOutfit(p).setColor(Color.YELLOW));
			else if(item.getType().equals(Material.WHITE_DYE))
				Outfit.addOutfit(p, Outfit.getOutfit(p).setColor(Color.WHITE));
			else if(item.getType().equals(Material.LIGHT_GRAY_DYE))
				Outfit.addOutfit(p, Outfit.getOutfit(p).setColor(Color.fromRGB(211, 211, 211)));
			else if(item.getType().equals(Material.GRAY_DYE))
				Outfit.addOutfit(p, Outfit.getOutfit(p).setColor(Color.GRAY));
			else if(item.getType().equals(Material.BLACK_DYE))
				Outfit.addOutfit(p, Outfit.getOutfit(p).setColor(Color.BLACK));
		}
		
		
	}
	
	private Inventory createInv() {
		Inventory inv = Bukkit.createInventory(null, 9*6, "Select your outfit here");
		inv.setItem(0, new ItemBuilder(Material.PLAYER_HEAD).setName("§3Click on one of the heads on the").setLore("§3right to select your head!").build());
		inv.setItem(2, Heads.LOLIMI.getItemStack());
		inv.setItem(3, Heads.BIGIERC.getItemStack());
		inv.setItem(4, Heads.WURZTHA.getItemStack());
		inv.setItem(5, Heads.ZHIQING.getItemStack());
		inv.setItem(6, Heads.SARA2696.getItemStack());
		inv.setItem(7, Heads.JASONGOES.getItemStack());
		inv.setItem(8, Heads.SQUASHYHYDRA.getItemStack());
		inv.setItem(11, Heads.GOODTIMEWITHSCAR.getItemStack());
		inv.setItem(12, Heads.XISUMAVOID.getItemStack());
		inv.setItem(13, Heads.MUMBO.getItemStack());
		inv.setItem(14, Heads.GRIAN.getItemStack());
		inv.setItem(15, Heads.DOCM77.getItemStack());
		inv.setItem(16, Heads.VILLAGER.getItemStack());
		inv.setItem(17, Heads.SQUID.getItemStack());
		
		inv.setItem(27, new ItemBuilder(Material.CRAFTING_TABLE).setName("§3Click on one of the colors on the").setLore("§3right to select your armor color!").build());
		inv.setItem(29, new ItemBuilder(Material.LAPIS_LAZULI).setName(ChatColor.DARK_BLUE+ "Blue").build());
		inv.setItem(30, new ItemBuilder(Material.RED_DYE).setName(ChatColor.RED+ "Red").build());
		inv.setItem(31, new ItemBuilder(Material.GREEN_DYE).setName(ChatColor.DARK_GREEN+ "Green").build());
		inv.setItem(32, new ItemBuilder(Material.CYAN_DYE).setName(ChatColor.DARK_AQUA+ "Cyan").build());
		inv.setItem(33, new ItemBuilder(Material.LIGHT_BLUE_DYE).setName(ChatColor.BLUE+ "Light Blue").build());
		inv.setItem(34, new ItemBuilder(Material.LIME_DYE).setName(ChatColor.GREEN+ "Lime").build());
		inv.setItem(35, new ItemBuilder(Material.ORANGE_DYE).setName(ChatColor.GOLD+ "Orange").build());
		inv.setItem(38, new ItemBuilder(Material.YELLOW_DYE).setName(ChatColor.YELLOW+ "Yellow").build());
		inv.setItem(39, new ItemBuilder(Material.WHITE_DYE).setName(ChatColor.WHITE+ "White").build());
		inv.setItem(40, new ItemBuilder(Material.LIGHT_GRAY_DYE).setName(ChatColor.GRAY+ "Light Gray").build());
		inv.setItem(41, new ItemBuilder(Material.GRAY_DYE).setName(ChatColor.DARK_GRAY+ "Gray").build());
		inv.setItem(42, new ItemBuilder(Material.BLACK_DYE).setName(ChatColor.BLACK+ "Black").build());
		
		return inv;
	}

}
