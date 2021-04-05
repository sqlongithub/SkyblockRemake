package me.sql.skyblockstats;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.sql.skyblockstats.database.Database;
import me.sql.skyblockstats.events.OnPlayerJoin;

public class SkyblockStats extends JavaPlugin {
	
	private Plugin plugin;
	
	public static double getPlayerHearts(int playerHP) {
		if(playerHP>=4000) {
			return 60;
		} else {
			return 20+(playerHP/100);
		}
	}
	
	private boolean aliasCheck(String s) {
		return s.equalsIgnoreCase("sbs") || s.equalsIgnoreCase("sbstats") || s.equalsIgnoreCase("skyblockstats");
	}
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
		plugin = Bukkit.getPluginManager().getPlugin("SkyblockStats");
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private void setStat(String stat, String statName, Object newVal, Player ply) {
		String plyUUID = ply.getUniqueId().toString();
		
		ply.setMetadata(stat, new FixedMetadataValue(plugin, newVal));
		Database.setValue("PLAYERSTATS.db", "playerstats", stat, newVal, plyUUID);
		ply.sendMessage("§8[§6SkyblockStats§8] §aYour base §b§l"+statName+"§a has been set to §c"+newVal);
		
		if(stat=="speed") {
			if(ply.getMetadata("speed").get(0).asInt()<=500) {
				ply.setWalkSpeed(ply.getMetadata("speed").get(0).asFloat()/500);

			} else {
				ply.setWalkSpeed(1);
			}
		} else if(stat=="hp") {
			ply.setMaxHealth(getPlayerHearts((int) newVal));
		}
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player ply = null;
		if(sender instanceof Player)
			ply = (Player) sender;		
		
		if(aliasCheck(label)) {
			if(args.length>0) {
				if(args[0].equalsIgnoreCase("help")) {
					return true;
				}
			}
			return true;
		}
		if(args.length>0) {
			if(ply != null) {
				if(label.startsWith("set")) {
					try {
						@SuppressWarnings("unused")
						int parsedInt = Integer.parseInt(args[0].replaceAll("[\\D]", ""));
					} catch(NumberFormatException e) {
						ply.sendMessage("§cThe new value of the stat can't be higher than 2.147 billion (32bit Integer Limit)	");
						return true;
					}
				}
				if(label.equalsIgnoreCase("setcc")) {
					setStat("cc", SpecialSymbols.critChanceSymbol+" Crit Chance§r", Integer.parseInt(args[0].replaceAll("[\\D]", "")), ply);
					return true;
				} else if(label.equalsIgnoreCase("sethp")) {
					setStat("hp", SpecialSymbols.healthSymbol+" Health§r", Integer.parseInt(args[0].replaceAll("[\\D]", "")), ply);
					return true;
				} else if(label.equalsIgnoreCase("setstrength")) {
					setStat("str", SpecialSymbols.strengthSymbol+" Strength§r", Integer.parseInt(args[0].replaceAll("[\\D]", "")), ply);
					return true;
				} else if(label.equalsIgnoreCase("setintel")) {
					setStat("intel", SpecialSymbols.intelligenceSymbol+" Intelligence§r", Integer.parseInt(args[0].replaceAll("[\\D]", "")), ply);
					return true;
				} else if(label.equalsIgnoreCase("setfero")) {
					setStat("fero", SpecialSymbols.ferocitySymbol+" Ferocity§r", Integer.parseInt(args[0].replaceAll("[\\D]", "")), ply);
					return true;
				} else if(label.equalsIgnoreCase("setcd")) {
					setStat("cd", SpecialSymbols.critDamageSymbol+" Crit Damage§r", Integer.parseInt(args[0].replaceAll("[\\D]", "")), ply);
					return true;
				} else if(label.equalsIgnoreCase("setdef")) {
					setStat("def", SpecialSymbols.defenseSymbol+" Defense§r", Integer.parseInt(args[0].replaceAll("[\\D]", "")), ply);
					return true;
				} else if(label.equalsIgnoreCase("setminingspeed")) {
					setStat("miningspd", SpecialSymbols.miningSpeedSymbol+" Mining Speed§r", Integer.parseInt(args[0].replaceAll("[\\D]", "")), ply);
					return true;
				} else if(label.equalsIgnoreCase("setattackspeed")) {
					setStat("attackspd", SpecialSymbols.attackSpeedSymbol+" Bonus Attack Speed§r", Integer.parseInt(args[0].replaceAll("[\\D]", "")), ply);
					return true;
				} else if(label.equalsIgnoreCase("setabildmg")) {
					setStat("abildmg", SpecialSymbols.abilityDamageSymbol+" Ability Damage§r", Integer.parseInt(args[0].replaceAll("[\\D]", "")), ply);
					return true;
				} else if(label.equalsIgnoreCase("setspeed")) {
					setStat("speed", SpecialSymbols.speedSymbol+" Speed§r", Integer.parseInt(args[0].replaceAll("[\\D]", "")), ply);
					return true;
				} 
			}
			return true;
		}
		return false;
		
	}

}
