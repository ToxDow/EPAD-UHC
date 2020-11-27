package fr.hugosimony.lguhc.listeners;

import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.hugosimony.lguhc.Main;
import fr.hugosimony.lguhc.Scoreboard;
import fr.hugosimony.lguhc.State;
import fr.hugosimony.lguhc.Tab;
import fr.hugosimony.lguhc.items.ItemsInv;
import fr.hugosimony.lguhc.player.Player;

public class Connexion implements Listener{
	
	// Constructeur :
	private Main main;
	private ItemsInv items_inv;
	public Connexion(Main main) {
		this.main = main;
		items_inv = new ItemsInv(main);
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent event) {
		
		org.bukkit.entity.Player p = event.getPlayer();
	    for (org.bukkit.entity.Player op : Bukkit.getOnlinePlayers()) {
	      Tab.sendTablist(op, main, false);
	    }
	    
	    if(main.contains(main.Ingame, p))
	    	main.Connected.add(main.findPlayer(p));
	    else {
	    	Player player = new Player(p, main);
	    	main.Connected.add(player);
	    }
		
		// Check du moment de la connexion
		if(main.isState(State.CONFIG)) {
			event.setJoinMessage("§a[UHC] §b"+ p.getName()+" vient de se connecter ! §a(" + main.Connected.size() + "/" + main.maxPlayer + ")");
			p.setGameMode(GameMode.ADVENTURE);
			p.setMaxHealth(20); // Cas du LGB
			p.setHealth(20); // 20 = full life
			p.setFoodLevel(20); // 20 = full food
			p.setLevel(0); p.setExp(0);
			p.getInventory().clear();
			items_inv.clearArmor(p);
		}else if(main.contains(main.Ingame, p))
			event.setJoinMessage("§a[UHC] §b"+ p.getName()+" vient de se reconnecter !");
		else
			event.setJoinMessage("");
		
		//**********************************************************************
		// Scoreboard 
		
		Scoreboard sb = new Scoreboard(p);
		sb.sendLine();
		sb.set();
		main.updateScoreBoard();
		
	}
	
	//***********************************************************************
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		org.bukkit.entity.Player p = event.getPlayer();
		String name = p.getName();
		for(int i = 0; i < main.Connected.size(); i++) {
			if(main.Connected.get(i).getName() == name)
				main.Connected.remove(i);
		}
		if(!main.game_started)
			event.setQuitMessage("§a[UHC] §b"+ p.getName()+" vient de se déconnecter ! §a(" + main.Connected.size() + "/" + main.maxPlayer + ")");
		else if(main.contains(main.Ingame, p))
			event.setQuitMessage("§a[UHC] §b"+ p.getName()+" vient de se déconnecter ! Il peut encore se reconnecter.");
		else
			event.setQuitMessage("");
		
		//Scoreboard 
		main.updateScoreBoard();
	}
	
}
