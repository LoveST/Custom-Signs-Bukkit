package LoveMST.SChanger;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Managers {

	private Managers() {
	}

	private static Managers instance = new Managers();
	private static sChanger plugin = ConfigManager.getPlugin();
	private static TranslateManager t = TranslateManager.getInstance();
	public static boolean copyEnabled = false;
	public static boolean copyLineEnabled = false;
	public static Block sign = null;
	public static int copiedLine = 0;
	public static String copiedTextLine = null;
	private static String[] codes = new String[] { "%black%", "%green%", "%gray%", "%blue%", "%bold%", "%italic%", "%aqua%", "%yellow%", "%white%",
			"%purple%", "%gold%", "%dark_red%", "%dark_blue%", "%dark_green%", "%dark_aqua%", "%dark_gray%", "%magic%", "%reset%", "%underline%", "%player%" };
	public static boolean editLine = false;
	public static int editLineN = 0;
	public static String editLineText = null;
	public static boolean canChat = true;

	public Managers getInstance() {
		return instance;
	}

	private static ArrayList<String> cmd = new ArrayList<String>();

	public static void getHelp(Player p, String page) {
		int pg = 1;
		int tpg = getTotallHelpPG();
		if (!page.equalsIgnoreCase("")) {
			try {
				pg = Integer.parseInt(page);
			} catch (Exception e) {
				MessageManager.getInstance().error(p, page + " is not a number");
				return;
			}
		}
		if (pg > tpg) {
			pg = 1;
		}
		int start = (pg * 4) - 4;
		int end = (pg * 4);
		if (end > cmd.size()) {
			end = cmd.size();
		}
		List<String> sub = cmd.subList(start, end);
		MessageManager.getInstance().sendMessage(
				p,
				ChatColor.RED + "------------===== " + ChatColor.GREEN + "" + ChatColor.BOLD + "page " + pg + "/" + tpg + ChatColor.RED
						+ " =====------------");
		for (String s : sub) {
			MessageManager.getInstance().sendMessage(p, ChatColor.GOLD + s);
		}
	}

	public static int getTotallHelpPG() {
		int tpg = 0;
		for (int start = 0; start < cmd.size(); start += 5) {
			tpg += 1;
		}

		return tpg;
	}

	public static void setHelp() {
		cmd.clear();
		cmd.add("/sch create (Create custom sign) (Coming soon)");
		cmd.add("/sch copy (copy a sign)");
		cmd.add("/sch copyline <Line> (copy a line from a sign)");
		cmd.add("/sch editline <Line> (edit a line from a sign)");
		cmd.add("/sch reload <reload all configuration file's>");
		cmd.add("/sch version (get the plugin version)");
	}

	public static void sendVersion(Player p) {
		MessageManager.getInstance().info(p, t.get("t_GetVersion") + " " + plugin.getD().getVersion());
	}

	public static void reloadConfig(Player p) {
		plugin.reloadConfig();
		TranslateManager.reloadLangs();
		plugin.setUpLang();
		MessageManager.getInstance().good(p, t.get("t_ConfigReloadComplete"));
	}

	public static void copySign(Player p) {
		if (copyEnabled) {
			copyEnabled = false;
			sign = null;
			MessageManager.getInstance().info(p, t.get("t_disabledSignCopy"));
		} else {
			copyEnabled = true;
			MessageManager.getInstance().info(p, t.get("t_enabledSignCopy"));
			MessageManager.getInstance().info(p, t.get("t_clickOnTheSignCopy"));
		}

	}

	public static void copySignLine(Player p, String line) {
		if (line == null) {
			copyLineEnabled = false;
			copiedLine = 0;
			copiedTextLine = null;
			MessageManager.getInstance().info(p, t.get("t_disabledSignLineCopy"));
			return;
		}

		int l = 0;
		try {
			l = Integer.parseInt(line);
		} catch (Exception e) {
			MessageManager.getInstance().error(p, t.get("t_mostTypeNumber"));
			return;
		}

		if (l < 1 || l > 4) {
			MessageManager.getInstance().error(p, t.get("t_wrongNumber14"));
			return;
		}

		if (copyLineEnabled) {
			copyLineEnabled = true;
			copiedLine = l;
			MessageManager.getInstance().info(p, t.get("t_clickOnSignToCopyLine"));
		} else {
			copyLineEnabled = true;
			copiedLine = l;
			MessageManager.getInstance().info(p, t.get("t_enabledSignLineCopy"));
			MessageManager.getInstance().info(p, t.get("t_clickOnSignToCopyLine"));
		}
	}

	public static int getLine(int l) {
		int line = 0;
		switch (l) {
		case 1:
			line = 0;
			break;
		case 2:
			line = 1;
			break;
		case 3:
			line = 2;
			break;
		case 4:
			line = 3;
		}
		return line;
	}

	public static String getStringCode(String s, Player p) {
		String string = "";
		switch (s) {
		case "%black%":
			string = "&0";
			break;
		case "%green%":
			string = "&a";
			break;
		case "%gray%":
			string = "&8";
			break;
		case "%bold%":
			string = "&l";
			break;
		case "%blue%":
			string = "&1";
			break;
		case "%italic%":
			string = "&o";
			break;
		case "%aqua%":
			string = "&b";
			break;
		case "%yellow%":
			string = "&e";
			break;
		case "%white%":
			string = "&f";
			break;
		case "%purple%":
			string = "&5";
			break;
		case "%gold%":
			string = "&6";
			break;
		case "%dark_red%":
			string = "&4";
			break;
		case "%dark_blue%":
			string = "&1";
			break;
		case "%dark_green%":
			string = "&2";
			break;
		case "%dark_aqua%":
			string = "&1";
			break;
		case "%dark_gray%":
			string = "&1";
			break;
		case "%magic%":
			string = "&k";
			break;
		case "%reset%":
			string = "&r";
			break;
		case "%underline%":
			string = "&n";
			break;
		case "%player%":
			string = p.getName();
		}
		return string;
	}

	public static String translateCode(String s, Player p) {
		String string = s;
		for (String c : codes) {
			if (string.contains(c)) {
				string = string.replaceAll(c, getStringCode(c, p));
			}
		}

		return ChatColor.translateAlternateColorCodes('&', string);
	}

	public static void editLine(Player p, String line) {
		if (line == null) {
			editLine = false;
			editLineN = 0;
			editLineText = null;
			MessageManager.getInstance().info(p, t.get("t_disabledEditLine"));
			return;
		}

		int l = 0;
		try {
			l = Integer.parseInt(line);
		} catch (Exception e) {
			MessageManager.getInstance().error(p, t.get("t_mostTypeNumber"));
			return;
		}

		if (l < 1 || l > 4) {
			MessageManager.getInstance().error(p, t.get("t_wrongNumber14"));
			return;
		}

		if (editLine) {
			editLine = true;
			canChat = false;
			editLineN = getLine(l);
			MessageManager.getInstance().info(p, t.get("t_typeText"));
		} else {
			editLine = true;
			editLineN = getLine(l);
			canChat = false;
			MessageManager.getInstance().info(p, t.get("t_enabledEditLine"));
			MessageManager.getInstance().info(p, t.get("t_typeText"));
		}
	}
}
