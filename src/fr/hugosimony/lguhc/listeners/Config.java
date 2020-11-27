package fr.hugosimony.lguhc.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.hugosimony.lguhc.Main;
import fr.hugosimony.lguhc.State;
import fr.hugosimony.lguhc.items.ItemsInv;
import fr.hugosimony.lguhc.timers.StartingTimer;

public class Config implements Listener{

	// Constructeur
	private Main main;
	private ItemsInv items_inv;
	public Config(Main main) {
		this.main = main;
		items_inv = new ItemsInv(main);
	}
	
	//**********************************************************************
	
	// Accéder au menu de configuration
	@EventHandler
	public void onRightClick (PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(player.getItemInHand().isSimilar(items_inv.config)){
		    	//Lancer le menu de configuration
				player.openInventory(items_inv.InvConfig(player));
		    }
		}
	}
	
	//**********************************************************************
	
	// Différents clics possibles dans le menu de configuration
	@EventHandler
	public void onInventoryClick (InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if(event.getInventory().getName().equalsIgnoreCase("§6§oConfiguration")){
			if(event.getCurrentItem().isSimilar(items_inv.start)){
				if(main.countRoles() == main.Connected.size()) {
					main.setState(State.STARTING);
					//Démarrer la partie
					player.closeInventory();
					player.openInventory(items_inv.InvConfig(player));
					StartingTimer task = new StartingTimer(main, "La téléportation des joueurs");
					task.runTaskTimer(main, 0, 20);
				}else {
					player.sendMessage("§c[Erreur] Le nombre de rôles ne correspond pas au nombre de joueurs !");
				}
		    }
			else if(event.getCurrentItem().isSimilar(items_inv.starting)){
				main.setState(State.CONFIG);
				//Arréter de démarrer la partie
				player.closeInventory();
				player.openInventory(items_inv.InvConfig(player));
		    }else if(event.getCurrentItem().isSimilar(items_inv.scenarios)) {
				player.closeInventory();
				player.openInventory(items_inv.InvScenarios(player));
			}else if(event.getCurrentItem().isSimilar(items_inv.uhc)) {
				player.closeInventory();
				player.openInventory(items_inv.InvUHC(player));
			}else if(event.getCurrentItem().isSimilar(items_inv.roles)) {
				player.closeInventory();
				player.openInventory(items_inv.InvAllRoles(player));
			}
			event.setCancelled(true);
		}else if(event.getInventory().getName().equalsIgnoreCase("§9§oScénarios")){
			
			//**********************************************************************
			// Scénarios
			if(event.getCurrentItem().isSimilar(items_inv.noFood)) {
				event.getInventory().setItem(10, items_inv.noFoodOff);
				main.noFood = false;
			}else if(event.getCurrentItem().isSimilar(items_inv.noFoodOff)) {
				event.getInventory().setItem(10, items_inv.noFood);
				main.noFood = true;
			}else if(event.getCurrentItem().isSimilar(items_inv.noFall)) {
				event.getInventory().setItem(11, items_inv.noFallOff);
				main.noFall = false;
			}else if(event.getCurrentItem().isSimilar(items_inv.noFallOff)) {
				event.getInventory().setItem(11, items_inv.noFall);
				main.noFall = true;
			}else if(event.getCurrentItem().isSimilar(items_inv.noFire)) {
				event.getInventory().setItem(12, items_inv.noFireOff);
				main.noFire = false;
			}else if(event.getCurrentItem().isSimilar(items_inv.noFireOff)) {
				event.getInventory().setItem(12, items_inv.noFire);
				main.noFire = true;
			}else if(event.getCurrentItem().isSimilar(items_inv.diamondLimit)) {
				event.getInventory().setItem(13, items_inv.noDiamondLimit);
				main.diamondLimit = false;
			}else if(event.getCurrentItem().isSimilar(items_inv.noDiamondLimit)) {
				player.closeInventory();
				main.diamondLimit = true;
				player.openInventory(items_inv.InvDiamondLimit(player));
			}
			//**********************************************************************
			if(event.getCurrentItem().isSimilar(items_inv.valider)) {
				player.closeInventory();
				player.openInventory(items_inv.InvConfig(player));
			}
			event.setCancelled(true);
			//**********************************************************************
		}else if(event.getInventory().getName().equalsIgnoreCase("§9§oDiamond Limit")){
			//**********************************************************************
			// Diamond Limit
			if(event.getCurrentItem().isSimilar(items_inv.plus))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeDiamondLimit(1));
			else if(event.getCurrentItem().isSimilar(items_inv.moins))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeDiamondLimit(-1));
			//**********************************************************************
			else if(event.getCurrentItem().isSimilar(items_inv.valider)) {
				player.closeInventory();
				player.openInventory(items_inv.InvScenarios(player));
			}
			event.setCancelled(true);
		}else if(event.getInventory().getName().equalsIgnoreCase("§2§oUHC")){
			//**********************************************************************
			// UHC
			if(event.getCurrentItem().isSimilar(items_inv.pvp)) {
				player.closeInventory();
				player.openInventory(items_inv.InvPVP(player));
			}else if(event.getCurrentItem().isSimilar(items_inv.border)) {
				player.closeInventory();
				player.openInventory(items_inv.InvBorder(player));
			}else if(event.getCurrentItem().isSimilar(items_inv.max_player)) {
				player.closeInventory();
				player.openInventory(items_inv.InvMaxPlayer(player));
			}else if(event.getCurrentItem().isSimilar(items_inv.day_night)) {
				player.closeInventory();
				player.openInventory(items_inv.InvDayNight(player));
			}else if(event.getCurrentItem().isSimilar(items_inv.role_time)) {
				player.closeInventory();
				player.openInventory(items_inv.InvRolesTime(player));
			}
			//**********************************************************************
			if(event.getCurrentItem().isSimilar(items_inv.valider)) {
				player.closeInventory();
				player.openInventory(items_inv.InvConfig(player));
			}
			event.setCancelled(true);
			//**********************************************************************
		}else if(event.getInventory().getName().equalsIgnoreCase("§4§oPVP")){
			//**********************************************************************
			// PVP
			if(event.getCurrentItem().isSimilar(items_inv.plus1))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizePVPTime(1));
			else if(event.getCurrentItem().isSimilar(items_inv.moins1))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizePVPTime(-1));
			else if(event.getCurrentItem().isSimilar(items_inv.plus5))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizePVPTime(5));
			else if(event.getCurrentItem().isSimilar(items_inv.moins5))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizePVPTime(-5));
			else if(event.getCurrentItem().isSimilar(items_inv.plus10))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizePVPTime(10));
			else if(event.getCurrentItem().isSimilar(items_inv.moins10))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizePVPTime(-10));
			//**********************************************************************
			if(event.getCurrentItem().isSimilar(items_inv.valider)) {
				player.closeInventory();
				player.openInventory(items_inv.InvUHC(player));
			}
			main.updateScoreBoard();
			event.setCancelled(true);
			//**********************************************************************
		}else if(event.getInventory().getName().equalsIgnoreCase("§8§oBordure")){
			//**********************************************************************
			// Bordure
			if(event.getCurrentItem().isSimilar(items_inv.plus1))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-10, items_inv.ActualizeBorderTime(1));
			else if(event.getCurrentItem().isSimilar(items_inv.moins1))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-10, items_inv.ActualizeBorderTime(-1));
			else if(event.getCurrentItem().isSimilar(items_inv.plus5))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-10, items_inv.ActualizeBorderTime(5));
			else if(event.getCurrentItem().isSimilar(items_inv.moins5))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-10, items_inv.ActualizeBorderTime(-5));
			else if(event.getCurrentItem().isSimilar(items_inv.plus10))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-10, items_inv.ActualizeBorderTime(10));
			else if(event.getCurrentItem().isSimilar(items_inv.moins10))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-10, items_inv.ActualizeBorderTime(-10));
			else if(event.getCurrentItem().isSimilar(items_inv.plus10s))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeBorderSize(10));
			else if(event.getCurrentItem().isSimilar(items_inv.moins10s))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeBorderSize(-10));
			else if(event.getCurrentItem().isSimilar(items_inv.plus50s))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeBorderSize(50));
			else if(event.getCurrentItem().isSimilar(items_inv.moins50s))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeBorderSize(-50));
			else if(event.getCurrentItem().isSimilar(items_inv.plus100s))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeBorderSize(100));
			else if(event.getCurrentItem().isSimilar(items_inv.moins100s))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeBorderSize(-100));
			else if(event.getCurrentItem().isSimilar(items_inv.plus))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2+8, items_inv.ActualizeBorderSpeed(0.5));
			else if(event.getCurrentItem().isSimilar(items_inv.moins))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2+8, items_inv.ActualizeBorderSpeed(-0.5));
			//**********************************************************************
			if(event.getCurrentItem().isSimilar(items_inv.valider)) {
				player.closeInventory();
				player.openInventory(items_inv.InvUHC(player));
			}
			main.border.setSize(main.border_size*2);
			main.updateScoreBoard();
			event.setCancelled(true);
			//**********************************************************************
		}else if(event.getInventory().getName().equalsIgnoreCase("§e§oJoueurs Maximum")){

			if(event.getCurrentItem().isSimilar(items_inv.plus))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeMaxPlayer(1));
			else if(event.getCurrentItem().isSimilar(items_inv.moins))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeMaxPlayer(-1));
			//**********************************************************************
			if(event.getCurrentItem().isSimilar(items_inv.valider)) {
				player.closeInventory();
				player.openInventory(items_inv.InvUHC(player));
			}
			main.updateScoreBoard();
			event.setCancelled(true);
			//**********************************************************************
		}else if(event.getInventory().getName().equalsIgnoreCase("§a§oCycle Jour/Nuit")){

			if(event.getCurrentItem().isSimilar(items_inv.plus))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeDayNight(1));
			else if(event.getCurrentItem().isSimilar(items_inv.moins))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeDayNight(-1));
			//**********************************************************************
			if(event.getCurrentItem().isSimilar(items_inv.valider)) {
				player.closeInventory();
				player.openInventory(items_inv.InvUHC(player));
			}
			event.setCancelled(true);
			//**********************************************************************
		}else if(event.getInventory().getName().equalsIgnoreCase("§4§oTemps des rôles")){
			//**********************************************************************
			// Temps des rôles
			if(event.getCurrentItem().isSimilar(items_inv.plus1))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeRolesTime(1));
			else if(event.getCurrentItem().isSimilar(items_inv.moins1))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeRolesTime(-1));
			else if(event.getCurrentItem().isSimilar(items_inv.plus5))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeRolesTime(5));
			else if(event.getCurrentItem().isSimilar(items_inv.moins5))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeRolesTime(-5));
			else if(event.getCurrentItem().isSimilar(items_inv.plus10))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeRolesTime(10));
			else if(event.getCurrentItem().isSimilar(items_inv.moins10))
				event.getInventory().setItem((event.getInventory().getSize()+1)/2-1, items_inv.ActualizeRolesTime(-10));
			//**********************************************************************
			if(event.getCurrentItem().isSimilar(items_inv.valider)) {
				player.closeInventory();
				player.openInventory(items_inv.InvUHC(player));
			}
			main.updateScoreBoard();
			event.setCancelled(true);
			//**********************************************************************
		}else if(event.getInventory().getName().equalsIgnoreCase("§4§oRôles")){

			if(event.getCurrentItem().isSimilar(items_inv.valider)) {
				player.closeInventory();
				player.openInventory(items_inv.InvConfig(player));
			}else if(!event.getCurrentItem().isSimilar(items_inv.contours)) {
				player.closeInventory();
				player.openInventory(items_inv.InvRole(player, event.getCurrentItem()));
			}
			event.setCancelled(true);
			//**********************************************************************
		}else if(event.getInventory().getItem(4).isSimilar(items_inv.contours_role)) {
			
			if(event.getCurrentItem().isSimilar(items_inv.valider)) {
				player.closeInventory();
				player.openInventory(items_inv.InvAllRoles(player));
			}else if(event.getCurrentItem().isSimilar(items_inv.plus)) {
				if(main.countRoles() < main.maxPlayer)
					main.plusMoinsRoles(1, event.getInventory().getName());
				items_inv.reloadRoles();
			}else if(event.getCurrentItem().isSimilar(items_inv.moins)) {
				main.plusMoinsRoles(-1, event.getInventory().getName());
				items_inv.reloadRoles();
			}
			event.setCancelled(true);
			//**********************************************************************
		}
	}

	//**********************************************************************
	
	// Empécher le host de drop son item de configuration
	@EventHandler
	public void onItemDrop (PlayerDropItemEvent event) {
		if(event.getItemDrop().getItemStack().isSimilar(items_inv.config))
			event.setCancelled(true);
	}
	
}
