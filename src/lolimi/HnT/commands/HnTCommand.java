package lolimi.HnT.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lolimi.HnT.gameStates.IngameState;
import lolimi.HnT.gameStates.LobbyState;
import lolimi.HnT.main.Main;
import lolimi.HnT.util.ItemBuilder;
import lolimi.HnT.util.LocationConfigUtil;
import lolimi.HnT.util.PlayingPlayersUtils;

public class HnTCommand implements CommandExecutor {
	
	private Main plugin;
	private PlayingPlayersUtils playerUtil;
	
	public HnTCommand(Main plugin) {
		this.plugin = plugin;
		playerUtil = new PlayingPlayersUtils(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Main.PREFIX+"§4Only players can use this command!");
			return false;
		}
		Player p = (Player) sender;
		
		if(args.length == 0) {
			if(!p.isOp()) {
				p.sendMessage(Main.PREFIX+ "§cPlease use /rch <JOIN // LEAVE>");
			}else
				p.sendMessage(Main.PREFIX+ "§cPlease use /rch <JOIN // LEAVE // START // SETLOBBY // SETSPECTATOR // SETTAGGERSPAWN // SETGAMESTARTSPAWN>");
		}
		
	//join
		if(args[0].equalsIgnoreCase("join")) {
			if((plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState)) {
				plugin.getPlayers().put(p, false);
				p.getInventory().clear();
				LocationConfigUtil util = new LocationConfigUtil(plugin, LocationConfigUtil.LOBBY_ROOT);
				p.teleport(util.getLocation());
				p.setGameMode(GameMode.SURVIVAL);
				p.setHealth(20);
				p.setFoodLevel(20);
				p.getInventory().setItem(4, ItemBuilder.getOutfitSelector());
				playerUtil.broadcastMessage(Main.PREFIX+ "§a"+ p.getDisplayName()+" §7has joined the game. There are §c"+ 
								(LobbyState.MIN_PLAYERS-plugin.getPlayers().size())+
								" §7players missing to start the game.");
				LobbyState lobbyState = (LobbyState) plugin.getGameStateManager().getCurrentGameState();
				if(lobbyState.getCountdown().isIdle()) {
					if(plugin.getPlayers().size() == LobbyState.MIN_PLAYERS) {
						lobbyState.getCountdown().stopIdle();
						lobbyState.getCountdown().start();
					}
				}
			}else {
				plugin.getPlayers().put(p, true);
				LocationConfigUtil util = new LocationConfigUtil(plugin, LocationConfigUtil.SPECTATOR_ROOT);
				p.teleport(util.getLocation());
			}
			
	//leave
		}else if(args[0].equalsIgnoreCase("leave")) {
			if(!plugin.getPlayers().containsKey(p)) {
				p.sendMessage(Main.PREFIX+ "§7You have to join before you can leave!");
				return false;
			}
			
			
			p.teleport(new LocationConfigUtil(plugin, LocationConfigUtil.LOBBY_ROOT).getLocation());
			if(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState) {
				LobbyState lobbyState = (LobbyState) plugin.getGameStateManager().getCurrentGameState();
				plugin.getPlayers().remove(p);
				if(lobbyState.getCountdown().isRunning()) {
					if(plugin.getPlayers().size() < LobbyState.MIN_PLAYERS) {
						lobbyState.getCountdown().stop();
						lobbyState.getCountdown().startIdle();
					}
				}
			}else if(plugin.getGameStateManager().getCurrentGameState() instanceof IngameState) {
				IngameState state = (IngameState) plugin.getGameStateManager().getCurrentGameState();
				if(state.getTagger().equals(p)) {
					p.sendMessage(Main.PREFIX+ "§cYou can't leave now, you are the tagger!");
				}else {
					plugin.getPlayers().remove(p);
					state.rmvFromRemaining();
				}
			}
			
	//start
		}else if(args[0].equalsIgnoreCase("start")){
			if(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState) {
				LobbyState lobbyState = (LobbyState) plugin.getGameStateManager().getCurrentGameState();
				lobbyState.getCountdown().startFaster();
			}
		
			
	//setLobby
		} else if(args[0].equalsIgnoreCase("setlobby")) {
			if(!p.isOp()) {
				p.sendMessage(Main.NO_PERMISSION);
				return false;
			}
			LocationConfigUtil util = new LocationConfigUtil(plugin, p.getLocation(), LocationConfigUtil.LOBBY_ROOT);
			util.saveLocation();
			p.sendMessage(Main.PREFIX+ "§aYou have placed the §6Lobby Spawn §aat: §b"+p.getLocation()+"§a.");
			
	//setSpectator
		}else if(args[0].equalsIgnoreCase("setspectator")) {
			if(!p.isOp()) {
				p.sendMessage(Main.NO_PERMISSION);
				return false;
			}
			LocationConfigUtil util = new LocationConfigUtil(plugin, p.getLocation(), LocationConfigUtil.SPECTATOR_ROOT);
			util.saveLocation();
			p.sendMessage(Main.PREFIX+ "§aYou have placed the §6Spectator Spawn §aat: §b"+p.getLocation()+"§a.");
		
		
	//setTaggerPos
		}else if(args[0].equalsIgnoreCase("settaggerspawn")) {
			if(!p.isOp()) {
				p.sendMessage(Main.NO_PERMISSION);
				return false;
			}
			LocationConfigUtil util = new LocationConfigUtil(plugin, p.getLocation(), LocationConfigUtil.TAGGERSPAWN_ROOT);
			util.saveLocation();
			p.sendMessage(Main.PREFIX+ "§aYou have placed the §6Tagger Spawn §aat: §b"+p.getLocation()+"§a.");
		
			
	//setGamestartSpawn
		}else if(args[0].equalsIgnoreCase("setgamestartspawn")) {
			if(!p.isOp()) {
				p.sendMessage(Main.NO_PERMISSION);
				return false;
			}
			LocationConfigUtil util = new LocationConfigUtil(plugin, p.getLocation(), LocationConfigUtil.GAMESTART_SPAWN_ROOT);
			util.saveLocation();
			p.sendMessage(Main.PREFIX+ "§aYou have placed the §6Game Starting Spawn §aat: §b"+p.getLocation()+"§a.");
		}
		
		
		
		
		return false;
	}

}
