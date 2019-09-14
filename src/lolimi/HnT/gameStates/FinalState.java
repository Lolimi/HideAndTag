package lolimi.HnT.gameStates;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import lolimi.HnT.main.Main;
import lolimi.HnT.util.LocationConfigUtil;
import lolimi.HnT.util.PlayingPlayersUtils;

public class FinalState extends GameState {

	GameStateManager manager;
	PlayingPlayersUtils util;

	public FinalState(GameStateManager manager) {
		this.manager = manager;
		util = new PlayingPlayersUtils(manager.getPlugin());
	}

	@Override
	public void start() {
		IngameState ingameState = (IngameState) manager.getGameStates()[GameState.INGAME_STATE];
		util.broadcastMessage(Main.PREFIX+ "§6The game has ended!");
		ArrayList<String> winners = new ArrayList<String>();
		if(ingameState.getRemaining() > 0) {
			util.broadcastMessage(Main.PREFIX+ "§aThe winners are:");
			for(Player p : manager.getPlugin().getPlayers().keySet()) {
				if(manager.getPlugin().getPlayers().get(p)) continue;
				if(p.equals(ingameState.getTagger())) continue;
				winners.add(p.getDisplayName());
			}
			util.broadcastMessage(Main.PREFIX+ "§b"+  String.join("§7, §b", winners));
		}else {
			util.broadcastMessage(Main.PREFIX+ "§aThe tagger §b"+ ingameState.getTagger().getDisplayName()+ "§a has won the game!");
		}
		util.noSpectators();
		util.invClear(null);
		util.teleportPlayers(new LocationConfigUtil(manager.getPlugin(), LocationConfigUtil.LOBBY_ROOT).getLocation(), null, true);
		
		manager.stopGameStates();
		
	}

	@Override
	public void stop() {
		manager.getPlugin().reset();

	}

}
