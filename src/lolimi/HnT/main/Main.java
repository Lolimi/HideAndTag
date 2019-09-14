package lolimi.HnT.main;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import lolimi.HnT.commands.HnTCommand;
import lolimi.HnT.gameStates.GameState;
import lolimi.HnT.gameStates.GameStateManager;
import lolimi.HnT.listeners.LobbyPlayerQuitListener;
import lolimi.HnT.listeners.OutfitSelectorListener;
import lolimi.HnT.listeners.PlayerGettingHitListener;
import lolimi.HnT.listeners.ShootArrowListener;

public class Main extends JavaPlugin {
	
	
	
	public static final String PREFIX = "§3[§6Hide and Tag§3] §r",
								NO_PERMISSION = "§4You don't have permission to use this command!";
	
	private GameStateManager gameStateManager;
	private HashMap<Player, Boolean> players;
	
	@Override
	public void onEnable() {
		init(Bukkit.getPluginManager());
		
		players = new HashMap<Player, Boolean>();
		
		gameStateManager = new GameStateManager(this);
		gameStateManager.setGameState(GameState.LOBBY_STATE);
		
		Bukkit.broadcastMessage("§aHideAndTag loaded.");
	}
	
	private void init(PluginManager pluginManager) {
		String[] hnta = {"hidentag","hideandtag"};
		List<String> hnt = Arrays.asList(hnta); 
		getCommand("hnt").setAliases(hnt);
		getCommand("hnt").setExecutor(new HnTCommand(this));
		
		pluginManager.registerEvents(new LobbyPlayerQuitListener(this), this);
		pluginManager.registerEvents(new ShootArrowListener(this), this);
		pluginManager.registerEvents(new PlayerGettingHitListener(this), this);
		pluginManager.registerEvents(new OutfitSelectorListener(this), this);
	}
	
	@Override
	public void onDisable() {
		gameStateManager = null;
		players = null;
		
	}
	
	public GameStateManager getGameStateManager() {
		return gameStateManager;
	}
	
	public HashMap<Player, Boolean> getPlayers() {
		return players;
	}
	
	public void reset() {
		
		players = new HashMap<Player, Boolean>();
		gameStateManager = new GameStateManager(this);
		gameStateManager.setGameState(GameState.LOBBY_STATE);
	}
	
	public void playerLeaveGame(Player player) {
		
	}
	
	
	 public static ItemStack createSkull(String url, String name)
	    {
	        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
	        if (url.isEmpty()) return head;
	       
	        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
	        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
	       
	        profile.getProperties().put("textures", new Property("textures", url));
	       
	        try
	        {
	            Field profileField = headMeta.getClass().getDeclaredField("profile");
	            profileField.setAccessible(true);
	            profileField.set(headMeta, profile);
	           
	        }
	        catch (IllegalArgumentException|NoSuchFieldException|SecurityException | IllegalAccessException error)
	        {
	            error.printStackTrace();
	        }
	        head.setItemMeta(headMeta);
	        return head;
	    }
	
}
