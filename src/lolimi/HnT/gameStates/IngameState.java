package lolimi.HnT.gameStates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.entity.Player;

import lolimi.HnT.countdowns.IngameCountdown;

public class IngameState extends GameState {
	
	private GameStateManager gameStateManager;
	private IngameCountdown countdown;
	
	private Player tagger;
	private int remaining;
	
	public IngameState(GameStateManager manager) {
		gameStateManager = manager;
	}

	@Override
	public void start() {
		List<Player> players = new ArrayList<Player>();
		for(Player p : gameStateManager.getPlugin().getPlayers().keySet()) {
			players.add(p);
		}
		Collections.shuffle(players);
		
		tagger = (Player) players.get(0);
		countdown = new IngameCountdown(gameStateManager);
		countdown.startPre();
		remaining = gameStateManager.getPlugin().getPlayers().size()-1;
	}

	@Override
	public void stop() {
		countdown.stop();
		
	}
	
	public Player getTagger() {
		return tagger;
	}
	
	public int getRemaining() {
		return remaining;
	}
	
	public void rmvFromRemaining() {
		remaining--;
		if(remaining == 0)
			gameStateManager.setGameState(FINAL_STATE);
	}
	
	public IngameCountdown getCountdown() {
		return countdown;
	}

}
