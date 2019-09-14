package lolimi.HnT.util;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import lolimi.HnT.main.Main;
import lolimi.HnT.outfits.Outfit;

public class PlayingPlayersUtils {

	Main plugin;

	public PlayingPlayersUtils(Main plugin) {
		this.plugin = plugin;
	}
	

	public void broadcastMessage(String message) {
		if(plugin.getPlayers() == null || plugin.getPlayers().size() == 0) return;
		for (Player p : plugin.getPlayers().keySet()) {
			p.sendMessage(message);
		}
	}

	public void broadcastMessage(String message, Player not) {
		if(plugin.getPlayers() == null || plugin.getPlayers().size() == 0) return;
		for (Player p : plugin.getPlayers().keySet()) {
			if (p.equals(not))
				continue;
			p.sendMessage(message);
		}
	}

	public void teleportPlayers(Location loc, Player not, boolean withSpectators) {
		if (withSpectators) {
			for (Player p : plugin.getPlayers().keySet()) {
				if (p.equals(not))
					continue;
				p.teleport(loc);
			}
		} else {
			for (Player p : plugin.getPlayers().keySet()) {
				if (p.equals(not) || plugin.getPlayers().get(p))
					continue;
				p.teleport(loc);
			}
		}
	}

	public void givePlayersEffect(Player not, PotionEffectType effectType, int ticks) {
		for (Player p : plugin.getPlayers().keySet()) {
			if (p.equals(not) || plugin.getPlayers().get(p))
				continue;
			PotionEffect pe = new PotionEffect(effectType, ticks, 1, false, false);
			p.addPotionEffect(pe);
		}
	}

	public void vestPlayers(Player not) {
		for (Player p : plugin.getPlayers().keySet()) {
			if (p.equals(not) || plugin.getPlayers().get(p))
				continue;
			if(Outfit.getOutfit(p) == null) {
				Outfit.addOutfit(p, new Outfit().randomColor().setHead(p));	
			}else if(Outfit.getOutfit(p).getColor() == null) {
				Outfit.getOutfit(p).randomColor();
			}else if(Outfit.getOutfit(p).getHead() == null){
				Outfit.getOutfit(p).setHead(p);
			}
			ItemStack[] armor = new ItemStack[3];
			armor[0] = new ItemStack(Material.LEATHER_CHESTPLATE);
			armor[1] = new ItemStack(Material.LEATHER_LEGGINGS);
			armor[2] = new ItemStack(Material.LEATHER_BOOTS);
			for(ItemStack i : armor) {
				LeatherArmorMeta m = (LeatherArmorMeta) i.getItemMeta();
				m.setColor(Outfit.getOutfit(p).getColor());
				i.setItemMeta(m);
			}
			
			p.getInventory().setHelmet(Outfit.getOutfit(p).getHead());
			p.getInventory().setChestplate(armor[0]);
			p.getInventory().setLeggings(armor[1]);
			p.getInventory().setBoots(armor[2]);
		}
	}

	public void invClear(Player not) {
		for (Player p : plugin.getPlayers().keySet()) {
			if (p.equals(not) || plugin.getPlayers().get(p))
				continue;
			p.getInventory().clear();
		}
	}

	public void gotHit(Player player) {
		if (plugin.getPlayers().containsKey(player)) {
			plugin.getPlayers().remove(player);
			plugin.getPlayers().put(player, true);
			player.teleport(new LocationConfigUtil(plugin, LocationConfigUtil.SPECTATOR_ROOT).getLocation());
			player.setGameMode(GameMode.SPECTATOR);
		}
	}

	public void setGamemode() {
		for (Player p : plugin.getPlayers().keySet()) {
			p.setGameMode(GameMode.SURVIVAL);
			p.setHealth(20);
			p.setFoodLevel(20);
		}
	}
	
	public void noSpectators() {
		ArrayList<Player> spectators = new ArrayList<Player>();
		for(Player p : plugin.getPlayers().keySet()) {
			if(plugin.getPlayers().get(p)) {
				p.setGameMode(GameMode.SURVIVAL);
				p.setHealth(20);
				p.setFoodLevel(20);
				p.removePotionEffect(PotionEffectType.INVISIBILITY);
				spectators.add(p);
			}
		}
		for(Player p : spectators) {
			plugin.getPlayers().remove(p);
			plugin.getPlayers().put(p, false);
		}
	}
	
	public boolean isSpectator(Player player) {
		return plugin.getPlayers().get(player);
	}
	
	public void healPlayers() {
		for(Player p : plugin.getPlayers().keySet()) {
			p.setHealth(20);
			p.setFoodLevel(20);
		}
	}
	

}
