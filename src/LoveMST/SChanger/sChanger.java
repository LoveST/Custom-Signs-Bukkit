package LoveMST.SChanger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class sChanger extends JavaPlugin {

	public static String currentLang = "english";

	@Override
	public void onEnable() {
		ConfigManager.plugin = this;
		setUpLang();
		MessageManager.getInstance().good(getServer().getConsoleSender(), "Enabling ...");
		Managers.setHelp();
		getConfig().options().copyDefaults(true);
		saveConfig();

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

					}
					// check if command args is 2
				} else if (args.length == 2) {
					if (args[0].equalsIgnoreCase("help")) {
						Managers.getHelp(p, args[1]);
					}
					// check if command args is 3
				} else if (args.length == 3) {

				} else { // check if command args is not supported
					MessageManager.getInstance().error(p, "unknown command");
				}
			} else {
				MessageManager.getInstance().error(p, "You dont have permission to do that");
			}
		}

		return true;
	}

	public void setUpLang() {
		if (!ConfigManager.checkFolder("langs")) {
			ConfigManager.makeFolder("langs");
		}

		if (ConfigManager.checkFile("langs/", getConfig().getString("currentLanguage"))) {
			currentLang = getConfig().getString("currentLanguage");
		} else {
			TranslateManager.checkMainLangFile();
		}
	}

}