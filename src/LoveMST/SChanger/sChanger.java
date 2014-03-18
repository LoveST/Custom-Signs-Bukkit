package LoveMST.SChanger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import Listeners.ClickSign;
import Listeners.PlayerChat;

public class sChanger extends JavaPlugin {

	public static String currentLang = "english";
	public PluginDescriptionFile d = getDescription();
	public static TranslateManager t = TranslateManager.getInstance();

	@Override
	public void onEnable() {
		ConfigManager.plugin = this;
		setUpLang();
		MessageManager.getInstance().good(getServer().getConsoleSender(), "Enabling ...");
		Managers.setHelp();
		getConfig().options().copyDefaults(true);
		saveConfig();
		Bukkit.getPluginManager().registerEvents(new ClickSign(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerChat(), this);
	}

	@Override
	public void onDisable() {
		MessageManager.getInstance().error(getServer().getConsoleSender(), "Disabling ...");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		// check if sender is a real player
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;

		// check if command is 'sch'
		if (cmd.getName().equalsIgnoreCase("sch")) {
			// check if player has permission
			if (p.hasPermission("MST-SignChanger")) {
				// check if command args is 0
				if (args.length == 0) {

					// check if command args is 1
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help")) {
						Managers.getHelp(p, "");
					} else if (args[0].equalsIgnoreCase("version")) {
						Managers.sendVersion(p);
					} else if (args[0].equalsIgnoreCase("reload")) {
						Managers.reloadConfig(p);
					} else if (args[0].equalsIgnoreCase("copy")) {
						Managers.copySign(p);
					} else if (args[0].equalsIgnoreCase("copyline")) {
						Managers.copySignLine(p, null);
					} else if (args[0].equalsIgnoreCase("editline")){
						Managers.editLine(p, null);
					}
					// check if command args is 2
				} else if (args.length == 2) {
					if (args[0].equalsIgnoreCase("help")) {
						Managers.getHelp(p, args[1]);
					} else if (args[0].equalsIgnoreCase("copyline")) {
						Managers.copySignLine(p, args[1]);
					} else if (args[0].equalsIgnoreCase("editline")){
						Managers.editLine(p, args[1]);
					}
					// check if command args is 3
				} else if (args.length == 3) {

				} else { // check if command args is not supported
					MessageManager.getInstance().error(p, t.get("t_UnknownCommand"));
				}
			} else {
				MessageManager.getInstance().error(p, t.get("t_NoPermission"));
			}
		}

		return true;
	}

	public void setUpLang() {
		if (!ConfigManager.checkFolder("langs")) {
			ConfigManager.makeFolder("langs");
		}

		if (ConfigManager.checkFile("langs", getConfig().getString("currentLanguage"))) {
			currentLang = getConfig().getString("currentLanguage");
			TranslateManager.setupLangs();
		} else {
			TranslateManager.checkMainLangFile();
			TranslateManager.setupLangs();
		}
	}

	public PluginDescriptionFile getD() {
		return d;
	}

}