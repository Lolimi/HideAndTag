package lolimi.HnT.outfits;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lolimi.HnT.main.Main;

public enum Heads {

	LOLIMI("ODUzMjE2NjIzODBlOTM1MTAxMzYwMTBiYWU1ODg5ODYyMjA2OTAxYzI4NmVmMmMxZTU1MzNmYzI5ZGU2OWVjMyJ9fX0=","Lolimi"),
	BIGIERC("YzlkZmI2NzMzOWRkOWZhNzI2N2VlN2E5MjQwMjgzMTY3NjUzMDI5MjAyMWM1YmFmNjhkNmE4NzYyZWI1MTZjOCJ9fX0=","bigierc617"),
	ZHIQING("YjU1NTU4OTliNmE4OTFmNzc0Y2RlNzljNjdmMDc4N2E0Y2M3ZDY2ZjNhOTlmNzg0Yzc2YWU4YzE0YzViYTVhZiJ9fX0=","Zhiqing"),
	WURZTHA("NGUwOWMzNGM4NWEwY2IxZTZlOGEzZmU0NmRkMDM2MmRhNTI4ZDdlZGMzMTMyYjVhMDE4YjYyOTY1ZjM4NWY1ZiJ9fX0=","Wurztha"),
	SARA2696("YjAwYTVmMTMwYzVlZjgyY2EwOTZiMTdiMGFkYTI4Y2NmMjkyM2ZkZDZlNjIwZDkyZjQyYWM1MDEzODRhOWY5In19fQ==","Sara2696"),
	JASONGOES("NzkxYzgyNDY2MWYwYjRiMGRiNGI0ZDEwMWE3YTQxMWUzZmY2NmU1MDhhZWViYjNlOTA3NDlkNDFkZmUzOWRlZSJ9fX0=","JasonGoes"),
	SQUASHYHYDRA("NTkwYzRmYzA3OTU3Yzk2ZDhlYjQ0YTI0Y2QxODY5YTBmMWFiNTMzZDM2NDQ5NmE3MTFlNTQxNmM3YzY5MTFiMyJ9fX0=","SquashyHydra"),
	XISUMAVOID("ZjNkOTk5NTI0M2MxYWZlN2FjNjJmZDMyNWY3NmIxZDBiZTIwNTg0MzllZjc5ODY5NWRlYjRjZWFmMmE5ZjM5In19fQ==", "xisumavoid"),
	MUMBO("MmNiNDZkN2UyNzI5MzI2NWFmZjE1ZDAxYTY5YjFlMTcyZTIyZWFiNGYxNDkxYzJhYmZiZjI3ODYxODEzODcifX19", "Mumbo"),
	GRIAN("MTNhODhlMjMxZjNiNTI5MmFjNTU0YjI1MzcyNjljNmYyY2M2NmE2NWE5MThlMTdjNTI2YjkzNmM2NmQ4ZSJ9fX0=", "Grian"),
	GOODTIMEWITHSCAR("ODY4YjcyOGFkNDA0ZmFmYTUzZTUxOGU0MGQzZTNkMDQxNzMyYjg5ZGRmMzM3ZjI1MzFjNjg4NTljZGI4ZGFiNCJ9fX0=","GoodTimeWithScar"),
	DOCM77("YjI5ZDg0OWM3ZWE5OGU4Mzc4YmE1Y2Q0ZjljZGUwMTZjZTIwMTMzOTMwYzJjNTU3Y2ExNDI2OTYwZWY3MTI0ZiJ9fX0=","Docm77"),
	VILLAGER("YjRiZDgzMjgxM2FjMzhlNjg2NDg5MzhkN2EzMmY2YmEyOTgwMWFhZjMxNzQwNDM2N2YyMTRiNzhiNGQ0NzU0YyJ9fX0=","Villager"),
	SQUID("NWU4OTEwMWQ1Y2M3NGFhNDU4MDIxYTA2MGY2Mjg5YTUxYTM1YTdkMzRkOGNhZGRmYzNjZGYzYjJjOWEwNzFhIn19fQ==","Squid"),
	CREEPER("YmE1ZTk1NzM1YTNmMzc3MmIxYjQ4NWUxNTAyODA3YWUzOTZhNzJjNjFiZmQzNmFiNDFmYTcxYmVjMmY2NGFhMiJ9fX0=","Creeper");

	private ItemStack item;
	private String idTag;
	private String prefix = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";

	private Heads(String texture, String id) {
		item = Main.createSkull(prefix + texture, id);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(id);
		item.setItemMeta(m);
		idTag = id;
	}

	public ItemStack getItemStack() {
		return item;
	}

	public String getName() {
		return idTag;
	}

}
