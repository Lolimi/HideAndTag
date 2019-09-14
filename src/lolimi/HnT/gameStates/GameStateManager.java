package lolimi.HnT.gameStates;

import lolimi.HnT.main.Main;

public class GameStateManager {
	
	private Main plugin;
	
	private GameState[] gameStates;
	private GameState currentGameState;
	
	public GameStateManager(Main plugin) {
		this.plugin = plugin;
		
		gameStates = new GameState[3];
		gameStates[GameState.LOBBY_STATE] = new LobbyState(this);
		gameStates[GameState.INGAME_STATE] = new IngameState(this);
		gameStates[GameState.FINAL_STATE] = new FinalState(this);
	}
	
	public void setGameState(int gameStateID) {
		if(currentGameState != null)
			currentGameState.stop();
		currentGameState = gameStates[gameStateID];
		currentGameState.start();
	}
	
	public void stopGameStates() {
		if(currentGameState == null) return;
		currentGameState.stop();
		currentGameState = null;
	}
	
	public void reset() {
		stopGameStates();
		gameStates[GameState.LOBBY_STATE] = new LobbyState(this);
		gameStates[GameState.INGAME_STATE] = new IngameState(this);
		gameStates[GameState.FINAL_STATE] = new FinalState(this);
		setGameState(GameState.LOBBY_STATE);
	}
	
	public GameState getCurrentGameState() {
		return currentGameState;
	}
	
	public Main getPlugin() {
		return plugin;
	}
	
	public GameState[] getGameStates() {
		return gameStates;
	}

}
