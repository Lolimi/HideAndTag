package lolimi.HnT.gameStates;

import lolimi.HnT.countdowns.LobbyCountdown;

public class LobbyState extends GameState {
	
	public static final int MIN_PLAYERS = 2,
							MAX_PLAYERS = 5;
	
	LobbyCountdown countdown;
	GameStateManager gameStateManager;
	
	public LobbyState(GameStateManager manager) {
		gameStateManager = manager;
	}

	@Override
	public void start() {
		countdown = new LobbyCountdown(gameStateManager);
		countdown.startIdle();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
	public LobbyCountdown getCountdown() {
		return countdown;
	}

}
