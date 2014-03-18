package LoveMST.SChanger;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {

	private ConfigManager() {
	}

	public static sChanger plugin;
	private ConfigManager instance = new ConfigManager();

	public ConfigManager getInstance() {
		return instance;
	}

	private static FileConfiguration config;
	private static File cfile;

	public static sChanger getPlugin() {
		return plugin;
	}

	public static void saveConfig() {

		try {
			config.save(cfile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void loadConfig(String path, String fName) {
		cfile = new File(ConfigManager.getPlugin().getDataFolder() + File.separator + path, fName + ".yml");
		config = YamlConfiguration.loadConfiguration(cfile);
	}

	public static boolean checkFile(String path, String fName) {
		cfile = new File(ConfigManager.getPlugin().getDataFolder() + File.separator + path, fName + ".yml");
		config = YamlConfiguration.loadConfiguration(cfile);
		if (!(cfile.exists())) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkFolder(String fName) {
		cfile = new File(ConfigManager.getPlugin().getDataFolder() + File.separator + fName);
		if (cfile.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public static void makeFolder(String fName) {
		cfile = new File(ConfigManager.getPlugin().getDataFolder() + File.separator + fName);
		if (!cfile.exists()) {
			cfile.mkdir();
		}
	}

	public static FileConfiguration createConfig(String path, String fName) {
		cfile = new File(ConfigManager.getPlugin().getDataFolder() + File.separator + path, fName + ".yml");
		config = YamlConfiguration.loadConfiguration(cfile);
		if (!(cfile.exists())) {
			try {
				cfile.createNewFile();
				config.load(cfile);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		}
		return config;
	}

	public static String getString(String path, String fpath, String fName) {
		loadConfig(fpath, fName);
		return config.getString(path);
	}

	public static int getInteger(String path, String fpath, String fName) {
		loadConfig(fpath, fName);
		return config.getInt(path);
	}

	public static List<?> getList(String path, String fpath, String fName) {
		loadConfig(fpath, fName);
		return config.getList(path);
	}

	public static boolean getBoolean(String path, String fpath, String fName) {
		loadConfig(fpath, fName);
		return config.getBoolean(path);
	}

	public static boolean contains(String path, String fpath, String fName) {
		loadConfig(fpath, fName);
		return config.contains(path);
	}

	public static void setString(String path, String result, String fpath, String fName) {
		loadConfig(fpath, fName);
		config.set(path, result);
		saveConfig();
	}

	public static void setInteger(String path, int result, String fpath, String fName) {
		loadConfig(fpath, fName);
		config.set(path, result);
		saveConfig();
	}

	public static void setList(String path, List<?> result, String fpath, String fName) {
		loadConfig(fpath, fName);
		config.set(path, result);
		saveConfig();
	}

	public static void setBoolean(String path, boolean result, String fpath, String fName) {
		loadConfig(fpath, fName);
		config.set(path, result);
		saveConfig();
	}

}
