package lolimi.HnT.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import lolimi.HnT.main.Main;

public class LocationConfigUtil {
	
	public static final String 	LOBBY_ROOT = "LobbySpawn",
								SPECTATOR_ROOT = "SectatorSpawn",
								TAGGERSPAWN_ROOT = "TaggerSpawn",
								GAMESTART_SPAWN_ROOT = "GamestartSpawn";
	
	private Main plugin;
	private Location loc;
	private String root;
	
	public LocationConfigUtil(Main plugin, Location loc, String root) {
		this.plugin = plugin;
		this.loc = loc;
		this.root = root;
	}
	
	public LocationConfigUtil(Main plugin, String root) {
		this(plugin, null, root);
	}
	
	public void saveLocation() {
		FileConfiguration conf = plugin.getConfig();
		conf.set(root+".World", loc.getWorld().getName());
		conf.set(root+".X", loc.getBlockX());
		conf.set(root+".Y", loc.getBlockY());
		conf.set(root+".Z", loc.getBlockZ());
		conf.set(root+".Yaw", loc.getYaw());
		conf.set(root+".Pitch", loc.getPitch());
		plugin.saveConfig();
	}
	
	public Location getLocation() {
		FileConfiguration conf = plugin.getConfig();
		World w = Bukkit.getWorld(conf.getString(root+ ".World"));
		loc = new Location(w, conf.getDouble(root+".X"), conf.getDouble(root+".Y"), conf.getDouble(root+".Z"), 
							(float) conf.getDouble(root+".Yaw"), (float) conf.getDouble(root+".Pitch"));
		return loc;
	}

}
