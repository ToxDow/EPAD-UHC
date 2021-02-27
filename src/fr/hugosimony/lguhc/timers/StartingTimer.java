package fr.hugosimony.lguhc.timers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.hugosimony.lguhc.Main;
import fr.hugosimony.lguhc.State;
import fr.hugosimony.lguhc.effects.Effects;
import fr.hugosimony.lguhc.player.Player;

public class StartingTimer extends BukkitRunnable {

	//Check tp
	static int count = 0;
	
	// Timer
	private int timer = 11;

	// Counstructeur
	private Main main;
	private String message;
	static Effects effects;
	public StartingTimer(Main main, String message) {
		this.main = main;
		this.message = message;
		effects = new Effects(main);
	}
	
	@Override
	public void run() {
		
		if(!main.isState(State.STARTING))
			this.cancel();
		else if(timer==0) {
			Bukkit.broadcastMessage("§a[UHC] §9" + message + " commence !");
			// Son de démarrage
			for(fr.hugosimony.lguhc.player.Player player : main.Ingame) {
				player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.LEVEL_UP, 50, 20);
			}
			if(message == "La téléportation des joueurs") {
				// Setter de Ingame
				for(Player p : main.Connected)
					main.Ingame.add(p);
				for(Player player : main.Ingame) {
					player.getPlayer().closeInventory();
					player.getPlayer().getInventory().clear();
					player.getPlayer().getEquipment().clear();
					player.getPlayer().setExp(0);
					player.getPlayer().setLevel(0);
					player.setKiller(player.getPlayer());
					player.setMaitre(new Player(player.getPlayer(), main));
				}
				main.updateScoreBoard();
				this.cancel();
				Teleportations(main);
			}
			else if(message == "La partie") {
				
				main.role_time_s = main.role_time*60;
				main.pvp_time_s = main.pvp_time*60;
				main.border_time_s = main.border_time*60;
				main.game_started = true;
				TeleportationsEnded(main);
				for(Player player : main.Ingame) {
					org.bukkit.entity.Player p = player.getPlayer();
					p.setGameMode(GameMode.SURVIVAL);
					p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
					p.removePotionEffect(PotionEffectType.SPEED);	
					p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
					p.removePotionEffect(PotionEffectType.NIGHT_VISION);
					p.removePotionEffect(PotionEffectType.INVISIBILITY);
					p.removePotionEffect(PotionEffectType.WEAKNESS);
					p.removePotionEffect(PotionEffectType.SLOW);
					p.removePotionEffect(PotionEffectType.JUMP);
					// Starter
					p.getInventory().addItem(new ItemStack(Material.BOOK));
					p.getInventory().addItem(new ItemStack(Material.WATER_BUCKET));
					if(!main.noFood)
						p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF,64));
				}
				GlobalTimer task = new GlobalTimer(main);
				task.runTaskTimer(main, 0, 20);
			}
		}else {
			if (timer == 10 && message == "La téléportation des joueurs") {
				for(Player player : main.Connected)
					player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.SUCCESSFUL_HIT, 50, 20);
			}
			if((timer<=5 || timer==10)) {
				for(Player player : main.Ingame)
					player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.SUCCESSFUL_HIT, 50, 20);
				if(timer == 1)
					Bukkit.broadcastMessage("§a[UHC] §9" + message + " commence dans 1 seconde.");
				else
					Bukkit.broadcastMessage("§a[UHC] §9" + message + " commence dans " + timer + " secondes.");
			}
		}
		timer--;
	}
	
	//*****************************************************************************
	
	static void Teleportations(Main main) {
		int current = 0; 
		int time = 30; //temps (en tics) entre chaque tp
		int distance = main.border_size/4; //distance entre chaque joueurs
		int x = main.border_size - main.border_size/5;
		int z = x;
		for(Player p: main.Ingame) {
			org.bukkit.entity.Player player = p.getPlayer();
			createPlateforms(current, main, player, x, 200, z);
			current+=time;
			if(x-distance>=distance-main.border_size)
				x-=distance;
			else {
				x = main.border_size - main.border_size/5;
				z-=distance*2;
			}
		}
	}
	
	static void createPlateforms(int current, Main main, org.bukkit.entity.Player player, int x, int y, int z) {
		Bukkit.getScheduler().runTaskLater(main, () -> {
			for(int i = x-3; i <= x+3; i++){
				for(int j = z-3; j <= z+3; j++)
					player.getWorld().getBlockAt(i, 200, j).setType(Material.BARRIER);
			}
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			Bukkit.broadcastMessage("§a[UHC] §9Téléportation de §6" + player.getName() + "§9.");
			player.teleport(new Location(main.world, x, 205, z));
			player.addPotionEffect(effects.slowness);
			player.addPotionEffect(effects.jump_boost);
			count++;
			if(count == main.Ingame.size()) {
				// Lancement du deuxième startingTimer
				StartingTimer task = new StartingTimer(main, "La partie");
				task.runTaskTimer(main, 0, 20);
			}
		},current);
	}
	
	//*****************************************************************************
	
	static void TeleportationsEnded(Main main) {
		int distance = main.border_size/5; //distance entre chaque joueurs
		int x = main.border_size - main.border_size/5;
		int z = x;
		for(Player player: main.Ingame) {
			DeletePlateforms(main, player.getPlayer(), x, 200, z);
			x-=distance;
		}
	}
	
	static void DeletePlateforms(Main main, org.bukkit.entity.Player player, int x, int y, int z) {
		for(int i = x-3; i <= x+3; i++){
			for(int j = z-3; j <= z+3; j++)
				player.getWorld().getBlockAt(i, 200, j).setType(Material.AIR);
		}
	}
	
}
