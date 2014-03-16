package LoveMST.SChanger;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Managers {

	private Managers() {
	}

	private static Managers instance = new Managers();

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
		int start = (pg * 5) - 5;
		int end = (pg * 5);
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
		cmd.add("/sch version <get the version>");
		cmd.add("/sch reload <reload all the config's>");
		cmd.add("/sch copy <enable-disable sign copy>");
		cmd.add("/sch paste <paste the sign that u copied>");
		cmd.add("/sch editsign <Line number>");
		cmd.add("/sch copyline <copy certion line in a sign>");
	}
}
