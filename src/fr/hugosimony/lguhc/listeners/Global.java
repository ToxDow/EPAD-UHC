package fr.hugosimony.lguhc.listeners;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import fr.hugosimony.lguhc.Main;
import fr.hugosimony.lguhc.State;

@SuppressWarnings("deprecation")
public class Global implements Listener {
	
	// Constructeur
	private Main main;
	public Global(Main main) {
		this.main = main;
	}
	
	//**********************************************************************
	
	// Désactiver le chat 
	@EventHandler
	public void onChat(PlayerChatEvent event) {
		if(main.isState(State.DAY) || main.isState(State.NIGHT) || main.isState(State.INVINCIBILITY) || main.isState(State.STARTING)) {
			event.getPlayer().sendMessage("§4[LG] §cLe chat est désactivé pendant la partie !");
			event.setCancelled(true);
		}
	}
	
	//**********************************************************************
	
	// Empêcher la nourriture de descendre
	@EventHandler
	public void onFood(FoodLevelChangeEvent event) {
		if(main.isState(State.CONFIG) || main.isState(State.STARTING) || main.isState(State.END) || main.noFood)
			event.setCancelled(true); // Pas de nourriture perdue dans la phase de préparation ou de lancement ou de fin ou si le scénario NoFood est actif.
	 	}
	
	//**********************************************************************
	
	// Diamond Limit
	@EventHandler
	public void onMine(BlockBreakEvent event) {
		Player player = event.getPlayer();
		fr.hugosimony.lguhc.player.Player p = main.findPlayer(player);
		if(main.diamondLimit) {
			if(event.getBlock().getType().equals(Material.DIAMOND_ORE)) {
				if(p.getDiamondsMined() >= main.diamondLimitMax) {
					event.setCancelled(true);
					player.sendMessage("§4[LG] §cVous avez atteint votre diamond limit (" + main.diamondLimitMax + ") !");
				}
				else
					p.setDiamondsMined(p.getDiamondsMined()+1);
			}
		}
	}
	
	//**********************************************************************

	// Empêcher la pluie
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		if(event.toWeatherState())
			event.setCancelled(true);
	}
	
	//**********************************************************************
	
}
