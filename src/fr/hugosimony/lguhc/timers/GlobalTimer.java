package fr.hugosimony.lguhc.timers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.hugosimony.lguhc.State;
import fr.hugosimony.lguhc.effects.Effects;
import fr.hugosimony.lguhc.player.Role;
import fr.hugosimony.lguhc.Main;

public class GlobalTimer extends BukkitRunnable {

	// Timer
	private int timer = 0;	
	private int invicibility_time = 30;
	private int day_time;
	private int role_time; 
	private int pvp_time;
	private int vote_time = 120;
	private int border_time;
	
	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	
	// Jour
	private int nb_day = 1;
	private String command_day = "time set day";
	// Nuit
	private int nb_night = 1;
	private String command_night = "time set night";
	
	// Constructeur
	Main main;
	Effects effects;
	public GlobalTimer(Main main) {
		this.main = main;
		pvp_time = main.pvp_time*60;
		role_time = main.role_time*60;
		day_time = main.cycle_jn*120;
		border_time = main.border_time*60;
		effects = new Effects(main);
	}
	
	@Override
	public void run() {
		
		// Jour
		if(timer%day_time == 0) {
			Bukkit.dispatchCommand(console, command_day);
			Bukkit.broadcastMessage("§a§m----------------------§r§a Jour " + nb_day + " §m----------------------");
			main.setState(State.DAY);
			if(nb_day == 1) {
				Bukkit.broadcastMessage("§a[UHC] §9Vous êtes invincibles pour les 30 prochaines secondes, profitez en !");
				String command2 = "gamerule naturalRegeneration false";
				Bukkit.dispatchCommand(console, command2);
				main.setState(State.INVINCIBILITY);
			}
			Location parano = null;
			fr.hugosimony.lguhc.player.Player paranop = null;
			boolean paranok = false;
			for(fr.hugosimony.lguhc.player.Player player: main.Ingame) {
				if(player.isLoup())
					player.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
				if(player.getRole() == Role.VPL)
					player.getPlayer().removePotionEffect(PotionEffectType.SPEED);
				if(player.getRole() == Role.PF) {
					player.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
					player.getPlayer().removePotionEffect(PotionEffectType.WEAKNESS);
				}
				if(player.getRole() == Role.ASSASSIN)
					player.getPlayer().addPotionEffect(effects.strengh);
				if(player.getRole() == Role.PARANO && !player.isDying()) {
					paranok = true;
					paranop = player;
					parano = player.getPlayer().getLocation();
				}
				if(player.getRole() == Role.VOYANTE) {
					player.getPlayer().sendMessage("§4[LG] §9Le jour se lève ! Vous pouvez observer pendant §l60 secondes§r§9  le rôle d'un villageois grâce à la commande §6/voir (pseudo) §9!");
					player.setCanVoir(true);
				}
				if(player.getRole() == Role.SALVA) {
					player.getPlayer().sendMessage("§4[LG] §9Le jour se lève ! Vous avez §l60 secondes§r§9  pour protéger un joueur pendant ce jour et cette nuit grâce à la commande §6/proteger (pseudo) §9!");
					player.setCanProtect(true);
				}
				
			}
			if(paranok) {
				for(fr.hugosimony.lguhc.player.Player player: main.Ingame) {
					Location location = player.getPlayer().getLocation();
					if(player.isMechant()) {
						double location_x = location.getX();	double location_z = location.getZ();
						double parano_x = parano.getX();		double parano_z = parano.getZ();
						if((location_x-parano_x > -25 && location_x-parano_x < 25 && location_z-parano_z > -25 && location_z-parano_z < 25)
								|| (parano_x-location_x > -25 && parano_x-location_x < 25 && parano_z-location_z > -25  && -location_z < 25)
								|| (parano_x-location_x > -25 && parano_x-location_x < 25 && location_z-parano_z > -25  && location_z-parano_z < 25)
								|| (location_x-parano_x > -25 && location_x-parano_x < 25 && parano_z-location_z > -25  && parano_z-location_z < 25)) {
							if(player != paranop)
								Bukkit.broadcastMessage("   §c§lAAAAAAARGHHHHHHHHHH");
						}
					}
				}
			}
			
			for(fr.hugosimony.lguhc.player.Player player_: main.Ingame) {
				if(player_.isProtecte() && player_.getRole() != Role.ANCIEN)
					player_.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			}
			
			for(fr.hugosimony.lguhc.player.Player player: main.Ingame) {
				player.setCanVote(true);
				player.setVote(0);
			}
			if(main.player_vote != null)
				main.player_vote.getPlayer().setMaxHealth(main.player_vote.getPlayer().getMaxHealth()*2);
			
			nb_day++;
		}
		
		//**********************************************************************
		
		// Fin des commandes
		if(timer%(day_time+60) == 0 && nb_day > 2) {
			for(fr.hugosimony.lguhc.player.Player player: main.Ingame) {
				if(player.getRole() == Role.VOYANTE) {
					player.getPlayer().sendMessage("§4[LG] §9Le jour s'est levé ! Vous ne pouvez plus observer de rôle !");
					player.setCanVoir(false);
				}
				if(player.getRole() == Role.SALVA) {
					player.getPlayer().sendMessage("§4[LG] §9Le jour s'est levé ! Vous ne pouvez plus protéger de joueur !");
					player.setCanProtect(false);
				}
				if(player.isProtecte()) {
					player.getPlayer().sendMessage("§4[LG] §9Le salvateur a décidé de vous protéger pour ce jour et cette nuit !");
					if(player.getRole() != Role.ANCIEN)
						player.getPlayer().addPotionEffect(effects.resistance);
				}
			}
		}
		
		//**********************************************************************
		
		// Vote
		if(timer%(day_time+vote_time) == 0 && nb_day > 2) 
			Bukkit.broadcastMessage("§4[LG] §9Le jour est levé ! C'est le moment de voter contre le joueur que vous voulez grâce à la commande §6/vote (pseudo) §9!");
		if(timer%(day_time+vote_time+60) == 0&& nb_day > 2) {
			Bukkit.broadcastMessage("§4[LG] §9La période de vote est terminée !");
			int more_vote = 0;
			fr.hugosimony.lguhc.player.Player vote = null;
			boolean draw = false;
			for(fr.hugosimony.lguhc.player.Player player: main.Ingame) {
				if(player.getVote() == more_vote)
					draw = true;
				else if(player.getVote() > more_vote) {
					more_vote = player.getVote();
					vote = player;
					draw = false;
				}
				player.setCanVote(false);
			}
			if(draw || main.Ingame.size() == 0) {
				Bukkit.broadcastMessage("§4[LG] §9Le village ne s'est pas mis d'accord, une §légalité §r§9a eu lieu !");
				main.player_vote = null;
			}else {
				Bukkit.broadcastMessage("§4[LG] §9Le joueur ayant pris le plus de vote est §6" + vote.getName() + "§9 avec §l" + more_vote + " vote(s) §r§9!");
				vote.getPlayer().setMaxHealth(vote.getPlayer().getMaxHealth()/2);
				main.player_vote = vote;
			}
			
		}
		
		
		//**********************************************************************
		
		// Nuit
		if((timer+(day_time/2))%day_time == 0) {
			Bukkit.dispatchCommand(console, command_night);
			Bukkit.broadcastMessage("§c§m----------------------§r§c Nuit " + nb_night + " §m----------------------");
			main.setState(State.NIGHT);
			Location pf = null;
			Player pfp = null;
			boolean pfok = false;
			for(fr.hugosimony.lguhc.player.Player player: main.Ingame) {
				if(player.getRole() == Role.ASSASSIN)
					player.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
				if(player.getRole() == Role.PF && !player.isDying()) {
					player.getPlayer().addPotionEffect(effects.invisibility);
					player.getPlayer().addPotionEffect(effects.weakness);
					pfok = true;
					pf = player.getPlayer().getLocation();
					pfp = player.getPlayer();
				}
				if(player.isLoup())
					player.getPlayer().addPotionEffect(effects.strengh);
				if(player.getRole() == Role.VPL)
					player.getPlayer().addPotionEffect(effects.speed);
				
			}
			if(pfok) {
				ArrayList<String> plist = new ArrayList<String>();
				for(fr.hugosimony.lguhc.player.Player player: main.Ingame) {
					Location location = player.getPlayer().getLocation();
					double location_x = location.getX();	double location_z = location.getZ();
					double pf_x = pf.getX();				double pf_z = pf.getZ();
					if((location_x-pf_x > -50 && location_x-pf_x < 50 && location_z-pf_z > -50 && location_z-pf_z < 50)
							|| (pf_x-location_x > -50 && pf_x-location_x < 50 && pf_z-location_z > -50  && -location_z < 50)
							|| (pf_x-location_x > -50 && pf_x-location_x < 50 && location_z-pf_z > -50  && location_z-pf_z < 50)
							|| (location_x-pf_x > -50 && location_x-pf_x < 50 && pf_z-location_z > -50  && pf_z-location_z < 50)) {
						if(player != main.findPlayer(pfp))
							plist.add(player.getName());
					}
				}
				pfp.sendMessage("§4[LG] §9Voici les joueurs se trouvant dans les 100 blocs autour de vous :");
				String players = "§9 ";
				for(String s : plist)
					players += s + "   ";
				pfp.sendMessage(players);
			}
			nb_night++;
		}
		
		//**********************************************************************
		
		// Assignation des rôles
		if(timer >= role_time-5 && timer <= role_time) {
			if(timer != role_time)
				Bukkit.broadcastMessage("§4[LG] §9Assignation des rôles dans " + (role_time-timer) + " seconde(s).");
			else {
				Bukkit.broadcastMessage("§4[LG] §9Assignation des rôles !");
				main.assignRole();
				main.role_selected = true;
			}
		}
		
		//**********************************************************************
		
		// Fin de l'invincibilité
		if(timer >= invicibility_time-5 && timer <= invicibility_time) {
			if(timer != invicibility_time)
				Bukkit.broadcastMessage("§a[UHC] §9Fin de l'invincibilité dans "+ (invicibility_time-timer) + " seconde(s).");
			else {
				Bukkit.broadcastMessage("§a[UHC] §9Fin de l'invincibilité !");
				main.setState(State.DAY);
			}
		}
		
		//**********************************************************************
		
		// Début du PVP
		if(timer >= pvp_time-5 && timer <= pvp_time) {
			if(timer != pvp_time)
				Bukkit.broadcastMessage("§a[UHC] §9PVP activé dans "+ (pvp_time-timer) + " seconde(s).");
			else {
				Bukkit.broadcastMessage("§a[UHC] §9PVP activé !");
				main.setPvp(true);
			}
		}
		main.updateScoreBoard();
		
		//**********************************************************************
		
		// Début de la réduction de la bordure
		if(timer >= border_time-5 && timer <= border_time) {
			if(timer != border_time)
				Bukkit.broadcastMessage("§a[UHC] §9Réduction de la bordure dans "+ (border_time-timer) + " seconde(s).");
			else {
				Bukkit.broadcastMessage("§a[UHC] §9Réduction de la bordure !");
				Bukkit.getScheduler().runTaskTimer(main, () -> {
					if(main.border.getSize() > 200)
						main.border.setSize(main.border.getSize()-((main.border_speed/20.0)*2.0));
				},0, 1);
			}
		}
		main.updateScoreBoard();

		//**********************************************************************
		if(timer > day_time*30 || main.isState(State.END))
			this.cancel();
		//**********************************************************************
		timer++;
		main.global_timer = timer;
		main.role_time_s--;
		main.pvp_time_s--;
		main.border_time_s--;
	}
}
