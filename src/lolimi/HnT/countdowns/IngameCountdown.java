package lolimi.HnT.countdowns;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import lolimi.HnT.gameStates.GameState;
import lolimi.HnT.gameStates.GameStateManager;
import lolimi.HnT.gameStates.IngameState;
import lolimi.HnT.main.Main;
import lolimi.HnT.util.LocationConfigUtil;
import lolimi.HnT.util.PlayingPlayersUtils;

public class IngameCountdown extends Countdown {
	
	private GameStateManager manager;
	private PlayingPlayersUtils util;
	
	private int preID;
	private int preSeconds;
	
	private int seconds;
	
	private boolean isPreRunning,
					isRunning;
	
	IngameState state;
	
	public IngameCountdown(GameStateManager manager) {
		this.manager = manager;
		preSeconds = 60;
		seconds = 8*60;
		state = (IngameState) manager.getCurrentGameState();
		util = new PlayingPlayersUtils(manager.getPlugin());
		isPreRunning = false;
		isRunning = false;
	}

	@Override
	public void start() {
		isRunning = true;
		state.getTagger().teleport(new LocationConfigUtil(manager.getPlugin(), LocationConfigUtil.GAMESTART_SPAWN_ROOT).getLocation());
		
		ID = Bukkit.getScheduler().scheduleSyncRepeatingTask(manager.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				switch (seconds) {
				case 60*8:
					util.broadcastMessage(Main.PREFIX+ "§aThe game starts now.");
					util.vestPlayers(state.getTagger());
					util.healPlayers();
				
				case 60*7: case 60*6: case 60*5: case 60*4: case 60*3: case 60*2: 
					util.broadcastMessage(Main.PREFIX+ "§a"+ seconds / 60+ " minutes §7remaining.");
					util.healPlayers();
					break;
					
				case 60:
					util.broadcastMessage(Main.PREFIX+ "§a1 minute §7remaining.");
					util.healPlayers();
					break;
					
				case 30: case 20: case 10: case 5: case 4: case 3: case 2:
					util.broadcastMessage(Main.PREFIX+ "§a"+ seconds / 60+ " seconds §7remaining.");
					break;
					
				case 1:
					util.broadcastMessage(Main.PREFIX+ "§a1 second §7remaining.");
					util.healPlayers();
					break;
					
				case 0:
					stop();
					manager.setGameState(GameState.FINAL_STATE);
					break;

				default:
					break;
				}
				
				seconds--;
			}
		}, 0, 20);
	}

	@Override
	public void stop() {
		if(!isRunning) return;
		isRunning = false;
		Bukkit.getScheduler().cancelTask(ID);
		new PlayingPlayersUtils(manager.getPlugin()).setGamemode();
		seconds = 60*8;
	}
	
	public void startPre() { 
		isPreRunning = true;
		if(!(manager.getCurrentGameState() instanceof IngameState)) return;
		util.teleportPlayers(new LocationConfigUtil(manager.getPlugin(), LocationConfigUtil.GAMESTART_SPAWN_ROOT).getLocation(), state.getTagger(), false);
		util.givePlayersEffect(state.getTagger(), PotionEffectType.INVISIBILITY, (preSeconds + seconds)*20);
		util.invClear(null);
		util.broadcastMessage(Main.PREFIX+ "§7You have one minute to hide before the tagger comes to find you!", state.getTagger());
		
		state.getTagger().teleport(new LocationConfigUtil(manager.getPlugin(), LocationConfigUtil.TAGGERSPAWN_ROOT).getLocation());
		ItemStack item = new ItemStack(Material.CROSSBOW);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName("§4Shoot the other players with this!");
		item.setItemMeta(m);
		state.getTagger().getInventory().setItem(0, item);
		state.getTagger().getInventory().setItem(1, new ItemStack(Material.SPECTRAL_ARROW));
		state.getTagger().sendMessage(Main.PREFIX+ "§7In one minute you can go to find the other players.");
		
		
		preID = Bukkit.getScheduler().scheduleSyncRepeatingTask(manager.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				switch (preSeconds) {
				case 50: case 40: case 30: case 20: case 10: case 5: case 4: case 3: case 2:
					util.broadcastMessage(Main.PREFIX+ "§c"+ preSeconds+ " seconds §7remaining to the start of the game.");
					break;
				case 1:
					util.broadcastMessage(Main.PREFIX+ "§c1 second §7remaining to the start of the game.");
					break;
				case 0:
					stopPre();
					start();
					break;
				default:
					break;
				}
				preSeconds--;
				
			}
		}, 0, 20);
	}
	
	public void stopPre() {
		isPreRunning = false;
		Bukkit.getScheduler().cancelTask(preID);
		state.getTagger().teleport(new LocationConfigUtil(manager.getPlugin(), LocationConfigUtil.TAGGERSPAWN_ROOT).getLocation());
		preSeconds = 60;
	}
	
	public boolean isPreRunning() {
		return isPreRunning;
	}
	
	public boolean isRunning() {
		return isRunning;
	}

}
