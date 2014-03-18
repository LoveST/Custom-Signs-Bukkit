package Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import LoveMST.SChanger.Managers;
import LoveMST.SChanger.MessageManager;
import LoveMST.SChanger.TranslateManager;

public class PlayerChat implements Listener {

	public TranslateManager t = TranslateManager.getInstance();

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player p = (Player) e.getPlayer();
		if (Managers.editLine) {
			if (!Managers.canChat) {
				e.setCancelled(true);
				Managers.canChat = true;
				Managers.editLineText = e.getMessage();
				MessageManager.getInstance().info(p, t.get("t_textAdded"));
				MessageManager.getInstance().info(p, t.get("t_leftClickSignEdit"));
			}
		}
	}

}
