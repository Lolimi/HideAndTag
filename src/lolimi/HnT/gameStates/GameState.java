package lolimi.HnT.gameStates;


public abstract class GameState {
	
	public static final int LOBBY_STATE = 0,
							INGAME_STATE = 1,
							FINAL_STATE = 2;
	
	public abstract void start();
	public abstract void stop();
}
