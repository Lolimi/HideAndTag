package lolimi.HnT.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import lolimi.HnT.gameStates.IngameState;
import lolimi.HnT.main.Main;

public class ShootArrowListener implements Listener {
	
	Main plugin;
	
	public ShootArrowListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onShootArrow(PlayerInteractEvent e) {
		if(!(plugin.getGameStateManager().getCurrentGameState() instanceof IngameState)) return;
		if(e.getItem() == null) return;
		if(!e.getItem().getType().equals(Material.CROSSBOW)) return;
		IngameState state = (IngameState) plugin.getGameStateManager().getCurrentGameState();
		if(!e.getPlayer().equals(state.getTagger())) return;
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR)||e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			try {
				if(e.getPlayer().getInventory().getItem(1).getAmount() >= 1) return;
				else e.getPlayer().getInventory().setItem(1, new ItemStack(Material.SPECTRAL_ARROW));
			}catch(NullPointerException f) {
				e.getPlayer().getInventory().setItem(1, new ItemStack(Material.SPECTRAL_ARROW));	
			}
		}
	}

}
