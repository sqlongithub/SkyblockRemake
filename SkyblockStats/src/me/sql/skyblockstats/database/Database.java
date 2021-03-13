package me.sql.skyblockstats.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

import me.sql.skyblockstats.PlayerStats;

public class Database {

	public static void createDatabase(String databaseName) {
		
		String url = "jdbc:sqlite:D:/Minecraft Servers/Skyblock Remake/db/"+databaseName;
		
		try(Connection con = DriverManager.getConnection(url)) {
			if(con != null) {
				DatabaseMetaData meta = con.getMetaData();
				Bukkit.getLogger().info("New database created: "+databaseName+" Driver name: "+meta.getDriverName());
			}
		} catch (SQLException e) {
			Bukkit.getLogger().info("SkyblockStats SQLException while creating database");
			Bukkit.getLogger().info(e.getMessage());
		}
	}
	
	public static Object getValue(String databaseName, String tableName, String value, String plyUUID) {
		String url = "jdbc:sqlite:D:/Minecraft Servers/Skyblock Remake/db/"+databaseName;
		String getValue = "select "+value+" from "+tableName+" where plyUUID = \""+plyUUID+"\"";
		try(Connection con = DriverManager.getConnection(url);
				Statement stmt = con.createStatement()) {
				return stmt.executeQuery(getValue).getObject(value);
		} catch (SQLException e) {
				Bukkit.getLogger().info(e.getMessage());
		} 
		return null;
	}
	
	public static void setValue(String databaseName, String tableName, String value, Object newValue, String plyUUID) {
		String url = "jdbc:sqlite:D:/Minecraft Servers/Skyblock Remake/db/"+databaseName;
		String setValue = "update "+tableName+" set "+value+" = "+newValue+" where plyUUID = \""+plyUUID+"\";";
		try(Connection con = DriverManager.getConnection(url);
				Statement stmt = con.createStatement()) {
				stmt.executeUpdate(setValue);
		} catch (SQLException e) {
				Bukkit.getLogger().info("Error while setting database value");
				Bukkit.getLogger().info(e.getMessage());
		} 
	}

	
	public static boolean rowExists(String databaseName, String tableName, String plyUUID) {
		String url = "jdbc:sqlite:D:/Minecraft Servers/Skyblock Remake/db/"+databaseName;
		String selectRow = "select plyUUID from "+tableName+" where plyUUID = \""+plyUUID+"\";";
		try(Connection con = DriverManager.getConnection(url);
			PreparedStatement stmt = con.prepareStatement(selectRow)) {
			return stmt.executeQuery().next();
		} catch (SQLException e) {
			Bukkit.getLogger().info(e.getMessage());
		}
		return false;
	}
	
	
	public static boolean databaseExists(String databaseName) {
		File database = new File("D:/Minecraft Servers/Skyblock Remake/db/"+databaseName);
		return database.exists();
	}
	
	public static void createRowInTable(String databaseName, String tableName, PlayerStats ps) {
		String url = "jdbc:sqlite:D:/Minecraft Servers/Skyblock Remake/db/"+databaseName;
		
		String createRow = "INSERT INTO "+tableName+" (plyUUID, hp, def, str, fero, abildmg, intel, attackspd, cc, cd, miningspd)\n" + 
				"VALUES ('"+ps.plyUUID.toString()+"', "+ps.hp+", "+ps.def+", "+ps.str+", "+ps.fero+", "+ps.abildmg+", "+ps.intel+", "+ps.attackspd+", "+ps.cc+", "+ps.cd+", "+ps.miningspd+");";
		
		try (Connection con = DriverManager.getConnection(url);
                Statement stmt = con.createStatement()) {
            // create a new row
            stmt.execute(createRow);
            Bukkit.getLogger().info("Row created");
        } catch (SQLException e) {
            Bukkit.getLogger().info(e.getMessage());
        }
	}
	
	public static void createTable(String databaseName) {
		String url = "jdbc:sqlite:D:/Minecraft Servers/Skyblock Remake/db/"+databaseName;
		
		String createTable = "CREATE TABLE IF NOT EXISTS playerstats (\n" 
				+ "	plyUUID TEXT PRIMARY KEY,\n" 
				+ "	hp INTEGER NOT NULL,\n" 
				+ "	def INTEGER NOT NULL,\n" 
				+ "	str INTEGER NOT NULL,\n" 
				+ "	fero INTEGER NOT NULL,\n" 
				+ "	abildmg INTEGER NOT NULL,\n" 
				+ "	intel INTEGER NOT NULL,\n" 
				+ "	attackspd INTEGER NOT NULL,\n" 
				+ "	cc INTEGER NOT NULL,\n"
				+ "	cd INTEGER NOT NULL,\n"
				+ "	miningspd INTEGER NOT NULL,\n"
				+ " speed INTEGER NOT NULL\n"
				+ ");";
		try (Connection con = DriverManager.getConnection(url);
                Statement stmt = con.createStatement()) {
            // create a new table
            stmt.execute(createTable);
            Bukkit.getLogger().info("Table created");
        } catch (SQLException e) {
        	Bukkit.getLogger().info(e.getMessage());
        }
	}
	
	public static void connectDatabase(String databaseName) {
		String url = "jdbc:sqlite:D:/Minecraft Servers/Skyblock Remake/db/"+databaseName;
		Connection con = null;
		try {
			con = DriverManager.getConnection(url);
			Bukkit.getLogger().info("Connected to database "+databaseName);
		} catch(SQLException e) {
			Bukkit.getLogger().info("SkyblockStats SQLException while connecting to database. Method: connectDatabase");
			Bukkit.getLogger().info(e.getMessage());
		} finally {
			 try {
				 if (con != null) {
					 con.close();
	            }
	         } catch (SQLException ex) {
	        	 Bukkit.getLogger().info(ex.getMessage());
	         }
		}
		
	}
	
}
