package lolimi.HnT.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import lolimi.HnT.gameStates.IngameState;
import lolimi.HnT.main.Main;
import lolimi.HnT.util.PlayingPlayersUtils;

public class PlayerGettingHitListener implements Listener{
	
	Main plugin;
	
	public PlayerGettingHitListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void gotHit(EntityDamageEvent e) {
		if(!e.getCause().equals(DamageCause.PROJECTILE)) return;
		if(!(plugin.getGameStateManager().getCurrentGameState() instanceof IngameState)) return;
		if(!(e.getEntity() instanceof Player)) return;
		if(!plugin.getPlayers().keySet().contains(e.getEntity())) return;
		if(plugin.getPlayers().get(e.getEntity())) return;
		IngameState state = (IngameState) plugin.getGameStateManager().getCurrentGameState();
		if(e.getEntity().equals(state.getTagger())) return;
		if(!state.getCountdown().isRunning()) {
			e.setCancelled(true);
			return;
		}
		new PlayingPlayersUtils(plugin).gotHit((Player) e.getEntity());
		state.rmvFromRemaining();
	}

}
