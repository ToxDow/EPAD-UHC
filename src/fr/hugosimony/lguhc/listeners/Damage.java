package fr.hugosimony.lguhc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.hugosimony.lguhc.Main;
import fr.hugosimony.lguhc.State;
import fr.hugosimony.lguhc.player.Role;
import fr.hugosimony.lguhc.timers.DyingTimer;

public class Damage implements Listener {
	
	// Constructeur
	private Main main;
	public Damage(Main main) {
		this.main = main;
	}
	
	//**********************************************************************
	
	// Mort d'un joueur
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		event.setDeathMessage("");
		if(!main.isState(State.END)) {
			Player player = event.getEntity();
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 50, 20);
			}
			fr.hugosimony.lguhc.player.Player p = main.findPlayer(player);
			p.setDead(true);
			if(main.role_selected) {
				Bukkit.broadcastMessage("§9§m----------------------------------------------\n"
					+ "§4[LG] §6" + event.getEntity().getName() + " §9est mort ! Il était §6" + main.getRoleString(main.getRole(main.Ingame, player)) + "§9 !\n"
					+ "§9§m----------------------------------------------");
				if(p.isInfect())
					Bukkit.broadcastMessage("§4[LG] §9Il était de plus §4infecté §9!\n"
					+ "§9§m----------------------------------------------");
				if(p.isCouple() && !p.getCouple().isDead()) {
					Bukkit.broadcastMessage("§4[LG] §9Il était de plus en §dcouple §9avec §6" + p.getCouple().getName() + "§9 qui,\n pris d'un chagrin d'amour, décide de se suicider !\n"
						+ "§9§m----------------------------------------------");
					p.getCouple().setDying(false);
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					Bukkit.dispatchCommand(console, "kill " + p.getCouple().getName());
				}
			}
			else
				Bukkit.broadcastMessage("§9§m----------------------------------------------\n"
					+ "§4[LG] §6" + event.getEntity().getName() + " §9est mort ! Il n'avait pas de rôle !\n"
					+ "§9§m----------------------------------------------");
			
			if(p.getRole() == Role.CHASSEUR) {
				player.sendMessage("§4[LG] §9Vous êtes mort mais dans votre dernier souffle, vous pouvez tirer sur quelqu'un ! "
						+ "Pour ce faire, utilisez la commande §6/tir (pseudo) §9! Attention, vous n'avez qu'une minute pour vous décider !");
				Bukkit.getScheduler().runTaskLater(main, () -> {
					if(main.Ingame.contains(p)) {
						Bukkit.broadcastMessage("§4[LG] §9Le chasseur n'a pas effectué son tir final !");
						main.Ingame.remove(p);
						main.updateScoreBoard();
					}
				},1200);
			}else {
				main.Ingame.remove(p);
				main.updateScoreBoard();
			}
			for(fr.hugosimony.lguhc.player.Player player_ : main.Ingame) {
				if(player_.getMaitre() == p) {
					player_.setLoup(true);
					player_.setMechant(true);
					player_.getPlayer().sendMessage("§4[LG] §9Vous vous êtes §6transformé §9! Vous devez désormais gagner avec le clan des loups !");
					player_.setTransformed(true);
					main.newLoup(player_);
				}
			}
			main.verifWin();
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		if(!main.isState(State.END))
			event.getPlayer().setGameMode(GameMode.SPECTATOR);
	}

	//**********************************************************************
	
	// Dégat d'entity
	@EventHandler
	public void OnDamageByEntity(EntityDamageByEntityEvent event) {
		Entity victim = event.getEntity();
		Entity killer_entity = event.getDamager();
		if(victim instanceof Player) {
			Player player = (Player) victim;
			
			if(main.isState(State.CONFIG) || main.isState(State.STARTING) || main.isState(State.INVINCIBILITY))
				event.setCancelled(true); // Pas de dégats pris dans la phase de préparation ou de lancement ou d'invincibilité
			else {
				if(killer_entity instanceof Player || (killer_entity instanceof Arrow && ((Arrow) killer_entity).getShooter() instanceof Player)) {
					if(!main.isPvpOn())
						event.setCancelled(true);
					else {
						fr.hugosimony.lguhc.player.Player p = main.findPlayer(player);
						if(p.getRole() == Role.LG_AMNESIQUE && !p.isTransformed() && killer_entity instanceof Player) {
							Player killer = (Player) killer_entity;
							if(main.findPlayer(killer).isLoup()){
								p.setTransformed(true);
								p.setLoup(true);
								p.setMechant(true);
								player.sendMessage("§4[LG] §9Hein ?! D'un coup vous retrouvez la mémoire ! Vous êtes §6Loup Garou Amnésique §9! "
										+ "Dépéchez vous de le faire comprendre à vos nouveaux coéquipiers ! Faîtes §6/role §9 pour les découvrir !");
								main.newLoup(p);
							}
						}
						if(player.getHealth() <= event.getDamage()) {
							main.findPlayer(player).setDying(true);
							main.findPlayer(player).death_location = player.getLocation();
							DyingTimer task = new DyingTimer(main, main.findPlayer(player));
							task.runTaskTimer(main, 0, 20);
							event.setDamage(0);
							player.setHealth(20);
							player.setGameMode(GameMode.SPECTATOR);
							if(killer_entity instanceof Player) {
								Player killer = (Player) killer_entity;
								main.setKiller(main.Ingame, player, killer);
							}else {
								Player killer = (Player)((Arrow) killer_entity).getShooter();
								main.setKiller(main.Ingame, player, killer);
							}
						}
					}
				}else if(player.getHealth() <= event.getDamage()) {
					// Mort par un mob
					main.findPlayer(player).setDying(true);
					main.findPlayer(player).death_location = player.getLocation();
					DyingTimer task = new DyingTimer(main, main.findPlayer(player));
					task.runTaskTimer(main, 0, 20);
					event.setDamage(0);
					player.setHealth(20);
					player.setGameMode(GameMode.SPECTATOR);
				}
			}
		}
	}
	
	//**********************************************************************
	
	// N'importe quel dégat
	@EventHandler
	public void OnDamage(EntityDamageEvent event) {
		Entity victim = event.getEntity();
		DamageCause cause = event.getCause();
		
		if(victim instanceof Player) {
			Player player = (Player) victim;
			
			if(main.isState(State.CONFIG) || main.isState(State.STARTING) || main.isState(State.INVINCIBILITY))
				event.setCancelled(true); // Pas de dégats pris dans la phase de préparation ou de lancement ou d'invincibilité
			else {
				if((((cause.equals(DamageCause.FIRE) || cause.equals(DamageCause.FIRE_TICK) || cause.equals(DamageCause.LAVA)) && main.noFire) || (cause.equals(DamageCause.FALL)) && main.noFall))
					event.setCancelled(true); // Explicite 
				if(!cause.equals(DamageCause.ENTITY_ATTACK) && !cause.equals(DamageCause.PROJECTILE)) {
					if(player.getHealth() <= event.getDamage()) {
						// Check si le player est mort
						if(!cause.equals(DamageCause.VOID)) {
							main.findPlayer(player).setDying(true);
							main.findPlayer(player).death_location = player.getLocation();
							DyingTimer task = new DyingTimer(main, main.findPlayer(player));
							task.runTaskTimer(main, 0, 20);
							event.setDamage(0);
							player.setHealth(20);
							player.setGameMode(GameMode.SPECTATOR);
						}
					}
				}
			}
		}
	}
}
