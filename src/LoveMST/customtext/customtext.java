package LoveMST.customtext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class customtext extends JavaPlugin {
	
	public listeners event = new listeners(this);
	HashMap <String,String> cords = new HashMap <String,String>();
	public String mst = "[MST-Custom Text]";
	
	public void onEnable(){
		
		getServer().getPluginManager().registerEvents(event, this);
        getConfig().options().copyDefaults(true);
		saveConfig();
		
	}
	
	
	
	
	public boolean onCommand(CommandSender sender,Command cmd,String commandLabel,String[] args){
		Player p = (Player) sender;
		
		
		if(cmd.getName().equalsIgnoreCase("mstct")) {
			if(args[0].equalsIgnoreCase("fletter")){
			
			Location playercords = p.getLocation();
			Double blockx = playercords.getX();
			Double blocky = playercords.getY() - 1 ;
			Double blockz = playercords.getZ();
			
			cords.put("firstblockcords", blockx + ":" + blocky + ":" + blockz);
			
			TextCreate();
			
			} else if (args[0].equalsIgnoreCase("direction")) {
				Location playercords = p.getLocation();
				Double blockx = playercords.getX();
				Double blocky = playercords.getY() - 1 ;
				Double blockz = playercords.getZ();
			
				cords.put("stblockcords", blockx + ":" + blocky + ":" + blockz);
			} else if (args[0].equalsIgnoreCase("create")){
				TextCreate();
				
			}
			
		} // end if command is mstct
		return true;
	}
	
	
	
	
	public String TextCreate(){
		// TextANormal();
		if(cords.containsKey("firstblockcords")){
			if(cords.containsKey("secondblockcords")){
			
				
				
				
				
				
				return "";
			} else {
				return ChatColor.RED + mst + "Text direction block is missing!";
			}
			
			
		} else {
			return ChatColor.RED + mst + "First letter location is missing!";
		}

	}
	
	
	public void TextANormal(){
		String[] block = cords.get("firstblockcords").split(":");
		World world = Bukkit.getWorld("world");
		double x = Double.parseDouble(block[0]);
		double y = Double.parseDouble(block[1]);
		double z = Double.parseDouble(block[2]);
		
		
		Location b1 = new Location(world,x,y,z);
		Location b2 = new Location(world,x,y + 1,z);
		Location b3 = new Location(world,x,y + 2,z);
		Location b4 = new Location(world,x,y + 3,z);
		
		Location b5 = new Location(world,x,y,z +2);
		Location b6 = new Location(world,x,y + 1,z +2);
		Location b7 = new Location(world,x,y + 2,z +2);
		Location b8 = new Location(world,x,y + 3,z +2);
		
		Location b9 = new Location(world,x,y + 3,z +1);
		Location b10 = new Location(world,x,y + 1,z +1);
		
		List<Location> list = Arrays.asList(b1, b2, b3,b4,b5,b6,b7,b8,b9,b10);
		
	
		for(Location loc:list){
			
		Block bloc =	Bukkit.getWorld("world").getBlockAt(loc);
		bloc.setType(Material.QUARTZ_BLOCK);
		
		
		}
		

	}

	
	
	
	public void TextCNormal(){
		String[] block = cords.get("firstblockcords").split(":");
		World world = Bukkit.getWorld("world");
		double x = Double.parseDouble(block[0]);
		double y = Double.parseDouble(block[1]);
		double z = Double.parseDouble(block[2]);
		
		
		Location b1 = new Location(world,x,y,z);
		Location b2 = new Location(world,x,y + 1,z);
		Location b3 = new Location(world,x,y + 2,z);
		Location b4 = new Location(world,x,y + 3,z);
		
		Location b5 = new Location(world,x,y,z +2);
		Location b6 = new Location(world,x,y + 1,z +2);
		Location b7 = new Location(world,x,y + 2,z +2);
		Location b8 = new Location(world,x,y + 3,z +2);
		
		Location b9 = new Location(world,x,y + 3,z +1);
		Location b10 = new Location(world,x,y + 1,z +1);
		
		List<Location> list = Arrays.asList(b1, b2, b3,b4,b5,b6,b7,b8,b9,b10);
		
		for(Location loc:list){
			
			Block bloc =	Bukkit.getWorld("world").getBlockAt(loc);
			bloc.setType(Material.QUARTZ_BLOCK);	
			
			}

		
		
	}
	
	
	public String TextDirection(){
		String[] b1 = cords.get("firstblockcords").split(":");
		String[] b2 = cords.get("secondblockcords").split(":");
		Double b1x = Double.parseDouble(b1[0]);
		Double b1y = Double.parseDouble(b1[1]);
		Double b1z = Double.parseDouble(b1[2]);
		
		Double b2x = Double.parseDouble(b2[0]);
		Double b2y = Double.parseDouble(b2[0]);
		Double b2z = Double.parseDouble(b2[0]);
		
		
		
		return "";
	}
	
	
	
	
	public boolean CheckIfNorth(){
		
		
		
		return true;
	}
	
	
	
	
}
