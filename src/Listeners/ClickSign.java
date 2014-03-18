package Listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import LoveMST.SChanger.Managers;
import LoveMST.SChanger.MessageManager;
import LoveMST.SChanger.TranslateManager;

public class ClickSign implements Listener {

	private static TranslateManager t = TranslateManager.getInstance();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void clickSign(PlayerInteractEvent e) {
		Player p = (Player) e.getPlayer();

		// check if action is right click
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getClickedBlock().getTypeId() == 63 || e.getClickedBlock().getTypeId() == 68) {
				if (p.hasPermission("MST - CopySign")) {
					if (p.getInventory().getItemInHand().getType() == Material.WOOD_HOE) {
						if (Managers.copyEnabled) {
							e.setCancelled(true);
							Block s = (Block) e.getClickedBlock();
							Managers.sign = s;
							MessageManager.getInstance().good(p, t.get("t_SignCopied"));
						} else if (Managers.copyLineEnabled) {
							e.setCancelled(true);
							Sign s = (Sign) e.getClickedBlock().getState();
							Managers.copiedTextLine = s.getLine(Managers.getLine(Managers.copiedLine));
							MessageManager.getInstance().info(p, t.get("t_lineCopied"));

						}
					}
				}
			}
		}

		// check if action is left click
		if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if (e.getClickedBlock().getTypeId() == 63 || e.getClickedBlock().getTypeId() == 68) {
				if (p.getInventory().getItemInHand().getType() == Material.WOOD_HOE) {
					// check if have permission
					if (p.hasPermission("MST - CopySign")) {
						if (Managers.copyEnabled) {
							e.setCancelled(true);
							Block s = (Block) e.getClickedBlock();
							if (Managers.sign != null) {
								p.getWorld().getBlockAt(s.getLocation()).setType(s.getType());
								Sign sign = (Sign) p.getWorld().getBlockAt(s.getLocation()).getState();
								Sign copiedSign = (Sign) Managers.sign.getState();
								sign.setLine(0, copiedSign.getLine(0));
								sign.setLine(1, copiedSign.getLine(1));
								sign.setLine(2, copiedSign.getLine(2));
								sign.setLine(3, copiedSign.getLine(3));
								sign.update();
								MessageManager.getInstance().good(p, t.get("t_signPasted"));

							} else {
								MessageManager.getInstance().error(p, t.get("t_youMostCopySignFirst"));
							}
						} else if (Managers.copyLineEnabled) {
							if (Managers.copiedTextLine != null) {
								e.setCancelled(true);
								Sign s = (Sign) e.getClickedBlock().getState();
								s.setLine(Managers.getLine(Managers.copiedLine), Managers.copiedTextLine);
								s.update();
								MessageManager.getInstance().good(p, t.get("t_linePasted"));
							} else {
								e.setCancelled(true);
								MessageManager.getInstance().error(p, t.get("t_mostCopyLineFirst"));
							}
						} else if (Managers.editLine){
							if(Managers.editLineText != null){
								e.setCancelled(true);
								Sign s = (Sign) e.getClickedBlock().getState();
								s.setLine(Managers.editLineN, Managers.translateCode(Managers.editLineText, p));
								s.update();
								MessageManager.getInstance().good(p, t.get("t_lineEdited"));
							} else {
								e.setCancelled(true);
								MessageManager.getInstance().error(p, t.get("t_mostTypeATextFirst"));
							}
						}
					}
				}
			}
		}

	}

}
