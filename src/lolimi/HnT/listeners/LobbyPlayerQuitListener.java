package lolimi.HnT.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import lolimi.HnT.gameStates.GameState;
import lolimi.HnT.gameStates.IngameState;
import lolimi.HnT.gameStates.LobbyState;
import lolimi.HnT.main.Main;

public class LobbyPlayerQuitListener implements Listener {
	
	private Main plugin;
	
	public LobbyPlayerQuitListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onConnect(PlayerQuitEvent e) {
		if(!plugin.getPlayers().keySet().contains(e.getPlayer())) return;
		plugin.getPlayers().remove(e.getPlayer());
		if(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState) {
			LobbyState lobbyState = (LobbyState) plugin.getGameStateManager().getCurrentGameState();
			if(lobbyState.getCountdown().isRunning()) {
				if(plugin.getPlayers().size() < LobbyState.MIN_PLAYERS) {
					lobbyState.getCountdown().stop();
					lobbyState.getCountdown().startIdle();
				}
			}
		}else if(plugin.getGameStateManager().getCurrentGameState() instanceof IngameState) {
			IngameState state = (IngameState) plugin.getGameStateManager().getCurrentGameState();
			if(state.getTagger().equals(e.getPlayer())) {
				plugin.getGameStateManager().setGameState(GameState.FINAL_STATE);
			}else {
				state.rmvFromRemaining();
			}
		}
	}

}
