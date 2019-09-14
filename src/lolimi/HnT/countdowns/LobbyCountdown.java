package lolimi.HnT.countdowns;

import org.bukkit.Bukkit;

import lolimi.HnT.gameStates.GameState;
import lolimi.HnT.gameStates.GameStateManager;
import lolimi.HnT.gameStates.LobbyState;
import lolimi.HnT.main.Main;
import lolimi.HnT.util.PlayingPlayersUtils;

public class LobbyCountdown extends Countdown {
	
	private Main plugin;
	
	private PlayingPlayersUtils util;
	private GameStateManager gameStateManager;
	
	private boolean isRunning = false;
	
	private int idleID;
	private boolean isIdle = false;
	
	private final int IDLE_TIME = 30 * 20, RUNNING_TIME = 60 * 20;
	private int seconds;
	
	public LobbyCountdown(GameStateManager manager) {
		gameStateManager = manager;
		plugin = gameStateManager.getPlugin();
		seconds = RUNNING_TIME / 20;
		util = new PlayingPlayersUtils(plugin);
	}

	@Override
	public void start() {
		if(isRunning) return;
		isRunning = true;
		
		ID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				switch (seconds) {
				
				case 60: case 50: case 40: case 30: case 20: case 10: case 5: case 4: case 3: case 2: 
					util.broadcastMessage(Main.PREFIX+ "§7The game starts in §a"+ seconds+ " seconds§7.");
					break;
				case 1:
					util.broadcastMessage(Main.PREFIX+ "§7The game starts in§a 1 second§7.");
					break;
				case 0:
					gameStateManager.setGameState(GameState.INGAME_STATE);
					
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
	}
	
	public void startIdle() {
		if(isIdle) return;
		isIdle = true;
		idleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				int missing = LobbyState.MIN_PLAYERS - plugin.getPlayers().size();
				if(missing > 1)
				 util.broadcastMessage(Main.PREFIX + "§7There are §c" + missing +" players §7missing to start the game.");
				else if(missing == 1)
					util.broadcastMessage(Main.PREFIX + "§7There is §c" + missing +" player §7missing to start the game.");
				
			}
		}, 0, IDLE_TIME);
	}
	
	public void stopIdle() {
		if(!isIdle) return;
		isIdle = false;
		seconds = RUNNING_TIME / 20;
		Bukkit.getScheduler().cancelTask(idleID);
	}
	
	public boolean isIdle() {
		return isIdle;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void startFaster() {
		seconds = 10;
	}

}
