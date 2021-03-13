package me.sql.skyblockmobs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.sql.skyblockmobs.events.CreatureBurn;
import me.sql.skyblockmobs.events.CreatureSpawn;
import me.sql.skyblockmobs.mobs.Mob;
import me.sql.skyblockmobs.mobs.Monsters;
import me.sql.skyblockmobs.mobs.customentities.CustomEntityEnderman;
import me.sql.skyblockmobs.mobs.customentities.CustomEntityUtility;
import net.minecraft.server.v1_8_R3.EntityEnderman;

public class SkyblockMobs extends JavaPlugin {

	private boolean aliasCheck(String s) {
		return s.equalsIgnoreCase("sbm") || s.equalsIgnoreCase("sbmobs") || s.equalsIgnoreCase("skyblockmobs");
	}
	
	private SkyblockMobs sbMobs;
	private World theWorld;
	private Plugin plugin;
	
	public void setMetadata(LivingEntity creature, Mob mob) {
		creature.setMetadata("level", new FixedMetadataValue(this.plugin, mob.level));
		creature.setMetadata("maxHP", new FixedMetadataValue(this.plugin, mob.maxHP));
		creature.setMetadata("name", new FixedMetadataValue(this.plugin, mob.name));
		creature.setMetadata("type", new FixedMetadataValue(this.plugin, mob.type));
		creature.setMetadata("nametag", new FixedMetadataValue(this.plugin, mob.nametag));
		creature.setMetadata("hp", new FixedMetadataValue(this.plugin, mob.maxHP));
		Bukkit.getLogger().info("Setting metadata of "+creature.getName()+". Example metadata: "+mob.level);
	}
	
	public void damageMob(LivingEntity entity, int dmg) {
		if(dmg>entity.getMetadata("hp").get(0).asInt()) {
			if(entity instanceof Damageable) {
				entity.setHealth(0);
			} else {
				entity.remove();
			}
		} else {
			entity.damage(0);
			entity.setMetadata("hp",new FixedMetadataValue(plugin,entity.getMetadata("hp").get(0).asInt()-dmg));
			updateName(entity);
		}
		
		Location damageDisplayLocation = entity.getLocation().add(entity.getLocation().getDirection().getZ(), entity.getEyeHeight(), entity.getLocation().getDirection().getX());
		ArmorStand damageDisplay = (ArmorStand) theWorld.spawnEntity(damageDisplayLocation,EntityType.ARMOR_STAND);
		
		damageDisplay.setMarker(true);
		damageDisplay.setVisible(false);
		damageDisplay.setCustomName(getDamageTag(dmg));
		damageDisplay.setCustomNameVisible(true);
		damageDisplay.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 127, false, false));
		damageDisplay.setGravity(false);
		damageDisplay.setSmall(true);
		
	}
	
	private String getDamageTag(int dmgDealt) {
		String damageTag = "§f"+SpecialSymbols.dealtDamageSymbol;
		String dmgDealtString = Integer.toString(dmgDealt);
		String[] dmgColors = {"§f","§e","§6","§c","§c","§f"};
		int j = 0;
		for(int i = 0; i<dmgDealtString.length()-1; i++) {
			if(i%6==0 && i>0) {
				j=0;
			}
			damageTag+=dmgColors[j]+dmgDealtString.charAt(i);
			j++;
			
		}
		damageTag+="§f"+SpecialSymbols.dealtDamageSymbol;
		return damageTag;
		
		
		
		
	}
	
	public void updateName(LivingEntity entity) {
		for(Entity ent : theWorld.getNearbyEntities(entity.getLocation(), 1.0, 4.0, 1.0)) {
			if(ent.getType()==EntityType.ARMOR_STAND) {
				ent.setCustomName("§8[§7Lv"+entity.getMetadata("level").get(0).asString()+"§8] §c"+entity.getMetadata("name").get(0).asString()+" §a"+entity.getMetadata("hp").get(0).asString()+"§r/§a"+entity.getMetadata("maxHP").get(0).asString()+SpecialSymbols.healthSymbol);
			}
		}
	}
	
	@Override
	public void onEnable() {
		sbMobs = this;
		theWorld = Bukkit.getWorld("world");
		Bukkit.getServer().getPluginManager().registerEvents(new CreatureSpawn(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CreatureBurn(), this);
		
		CustomEntityUtility util = new CustomEntityUtility();
		
		util.registerEntity("Enderman", 58, EntityEnderman.class, CustomEntityEnderman.class);
		
		plugin = Bukkit.getPluginManager().getPlugin("SkyblockMobs");
	}
	
	public void onDisable() {
		
	}
	
	public SkyblockMobs getInstance() {
		return this.sbMobs;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player ply = null;
		if(aliasCheck(label)) {
			if(args.length>0) {
				if(args[1].equalsIgnoreCase("help")) {
					return true;
				} else if(args[1].equalsIgnoreCase("reload")) {
					return true;
				}
			}
		}
		if(label.equalsIgnoreCase("summonmob") ) {
			if(sender instanceof Player) {
				ply = (Player) sender;
			} else {
				sender.sendMessage("You cannot use this command from the console!");
				return true;
			}
			if(args.length>0) {
				Vector direction = ply.getLocation().getDirection();
				Location spawnLocation = ply.getLocation().add(direction.getX(), 0, direction.getZ());
				EntityType type = Monsters.getType(args[0]);
				LivingEntity summonedEntity;
				if(args[0].equalsIgnoreCase("Zealot") || args[0].equalsIgnoreCase("Enderman")) {
					Enderman enderman = CustomEntityEnderman.spawn(spawnLocation);
					Mob summonedMob = Monsters.getMob(enderman, args[0]);
					setMetadata(enderman, summonedMob);
					CreatureSpawn.createName(enderman, summonedMob);
					enderman.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999999, 10, false, false));
					return true; 
				}
				if(type != null) {
					Bukkit.getLogger().info(type.toString());
					summonedEntity = (LivingEntity) theWorld.spawnEntity(spawnLocation, type);
					Mob summonedMob = Monsters.getMob(summonedEntity, args[0]);
					setMetadata(summonedEntity, summonedMob);
					CreatureSpawn.createName(summonedEntity, summonedMob);
					summonedEntity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999999, 10, false, false));
				} else {
					summonedEntity = (LivingEntity) theWorld.spawnEntity(spawnLocation, EntityType.ARMOR_STAND);
				}
				return true;
			}
		}
		return false;
	}
	
}
