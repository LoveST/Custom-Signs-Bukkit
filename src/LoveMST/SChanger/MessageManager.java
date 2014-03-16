package LoveMST.SChanger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageManager {

	private MessageManager() {
	}

	private static MessageManager instance = new MessageManager();

	public static MessageManager getInstance() {
		return instance;
	}

	private String prefix = ChatColor.GRAY + "[MST - Sign Changer] ";

	public void info(CommandSender s, String msg) {
		msg(s, ChatColor.YELLOW, msg);
	}

	public void error(CommandSender s, String msg) {
		msg(s, ChatColor.RED, msg);
	}

	public void good(CommandSender s, String msg) {
		msg(s, ChatColor.GREEN, msg);
	}

	public void infoConsole(String msg) {
		msgConsole(ChatColor.YELLOW, msg);
	}

	public void errorConsole(String msg) {
		msgConsole(ChatColor.RED, msg);
	}

	public void goodConsole(String msg) {
		msgConsole(ChatColor.GREEN, msg);
	}

	public void sendMessage(CommandSender s, String msg) {
		s.sendMessage(msg);
	}

	private void msgConsole(ChatColor color, String msg) {
		Bukkit.getConsoleSender().sendMessage(prefix + color + msg);
	}

	private void msg(CommandSender s, ChatColor color, String msg) {
		s.sendMessage(prefix + color + msg);
	}

}
