package fr.hugosimony.lguhc.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.hugosimony.lguhc.Main;
import fr.hugosimony.lguhc.State;
import fr.hugosimony.lguhc.items.ItemsInv;
import fr.hugosimony.lguhc.player.Role;

public class Roles implements CommandExecutor{

	// Constructeur
	private Main main;
	private ItemsInv items_inv;
	public Roles(Main main) {
		this.main = main;
		items_inv = new ItemsInv(main);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		// Vérifie que la commande ne vient pas de la console.
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			
			if(cmd.getName().equalsIgnoreCase("roles")) {
				if(main.Ingame.get(0).getRole() != Role.NONE) {
					player.sendMessage("§9§m----------------------------------------------");
					player.sendMessage("§9§l   Liste des roles :");
					if(main.countRole(Role.ANCIEN) != 0)
						player.sendMessage("§9 - Ancien : " + main.countRole(Role.ANCIEN));
					if(main.countRole(Role.ASSASSIN) != 0)
						player.sendMessage("§9 - Assassin : " + main.countRole(Role.ASSASSIN));
					if(main.countRole(Role.CHASSEUR) != 0)
						player.sendMessage("§9 - Chasseur : " + main.countRole(Role.CHASSEUR));
					if(main.countRole(Role.CUPIDON) != 0)
						player.sendMessage("§9 - Cupidon : " + main.countRole(Role.CUPIDON));
					if(main.countRole(Role.DETECTIVE) != 0)
						player.sendMessage("§9 - Détective : " + main.countRole(Role.DETECTIVE));
					if(main.countRole(Role.ENFANT_SAUVAGE) != 0)
						player.sendMessage("§9 - Enfant Sauvage : " + main.countRole(Role.ENFANT_SAUVAGE));
					if(main.countRole(Role.IPDL) != 0)
						player.sendMessage("§9 - Infect Père Des Loups : " + main.countRole(Role.IPDL));
					if(main.countRole(Role.LG) != 0)
						player.sendMessage("§9 - Loup Garou : " + main.countRole(Role.LG));
					if(main.countRole(Role.LG_AMNESIQUE) != 0)
						player.sendMessage("§9 - Loup Garou Amnésique : " + main.countRole(Role.LG_AMNESIQUE));
					if(main.countRole(Role.LG_ANONYME) != 0)
						player.sendMessage("§9 - Loup Garou Anonyme : " + main.countRole(Role.LG_ANONYME));
					if(main.countRole(Role.LGB) != 0)
						player.sendMessage("§9 - Loup Garou Blanc : " + main.countRole(Role.LGB));
					if(main.countRole(Role.PARANO) != 0)
						player.sendMessage("§9 - Parano : " + main.countRole(Role.PARANO));
					if(main.countRole(Role.PF) != 0)
						player.sendMessage("§9 - Petite Fille : " + main.countRole(Role.PF));
					if(main.countRole(Role.RENARD) != 0)
						player.sendMessage("§9 - Renard : " + main.countRole(Role.RENARD));
					if(main.countRole(Role.SALVA) != 0)
						player.sendMessage("§9 - Salvateur : " + main.countRole(Role.SALVA));
					if(main.countRole(Role.SOEUR) != 0)
						player.sendMessage("§9 - Soeur : " + main.countRole(Role.SOEUR));
					if(main.countRole(Role.SORCIERE) != 0)
						player.sendMessage("§9 - Sorcière : " + main.countRole(Role.SORCIERE));
					if(main.countRole(Role.SV) != 0)
						player.sendMessage("§9 - Simple Villageois : " + main.countRole(Role.SV));
					if(main.countRole(Role.VOLEUR) != 0)
						player.sendMessage("§9 - Voleur : " + main.countRole(Role.VOLEUR));
					if(main.countRole(Role.VOYANTE) != 0)
						player.sendMessage("§9 - Voyante : " + main.countRole(Role.VOYANTE));
					if(main.countRole(Role.VPL) != 0)
						player.sendMessage("§9 - Vilain Petit Loup : " + main.countRole(Role.VPL));
					player.sendMessage("§9§m----------------------------------------------");
				}else 
					player.sendMessage("§4[LG] §cLes rôles n'ont pas encore été distribués !");
			}
			
			//**********************************************************************
			
			else if(main.isState(State.DAY) || main.isState(State.NIGHT) || main.isState(State.INVINCIBILITY)) {
				fr.hugosimony.lguhc.player.Player p = main.findPlayer(player);
				
				if(main.Ingame.contains(p) || !p.isDying()) {
					if(p.getRole() != Role.NONE) {
						
					//**********************************************************************
					// /role
					
					if(cmd.getName().equalsIgnoreCase("role"))
						main.printRole(false, p);
					
					//**********************************************************************
					// /claim
					
					else if(cmd.getName().equalsIgnoreCase("claim")) {
						if(args.length == 0) {
							if((p.getRole() == Role.ASSASSIN || p.getRole() == Role.CHASSEUR || p.getRole() == Role.CUPIDON || p.getRole() == Role.SALVA 
									|| p.getRole() == Role.SORCIERE || p.getRole() == Role.VOYANTE || p.getRole() == Role.CUPIDON)) {
								if(!p.hasClaimed()) {
									player.sendMessage("§4[LG] §aVoici votre kit !");
									if(p.getRole() == Role.ASSASSIN) {
										player.getInventory().addItem(items_inv.power3);
										player.getInventory().addItem(items_inv.protect3);
										player.getInventory().addItem(items_inv.sharp3);
									}
									else if(p.getRole() == Role.CHASSEUR || p.getRole() == Role.CUPIDON) {
										player.getInventory().addItem(items_inv.bow);
										player.getInventory().addItem(items_inv.power2_punch1);
										player.getInventory().addItem(items_inv.arrows);
									}
									else if(p.getRole() == Role.SALVA)
										player.getInventory().addItem(items_inv.health_potion);
									else if(p.getRole() == Role.SORCIERE) {
										player.getInventory().addItem(items_inv.health_potion);
										player.getInventory().addItem(items_inv.regen_potion);
										player.getInventory().addItem(items_inv.damage_potion);
									}
									else if(p.getRole() == Role.VOYANTE) {
										player.getInventory().addItem(items_inv.biblio);
										player.getInventory().addItem(items_inv.obsi);
									}
									p.setClaimed(true);
								}else 
									player.sendMessage("§4[LG] §cVous avez déjà récupéré votre kit !");
							}else 
								player.sendMessage("§4[LG] §cVotre rôle n'a pas d'items a claim !");
						}else
							player.sendMessage("§c[Erreur] La commande est §6/claim §c!");
					}
					
					//**********************************************************************
					// /tir 
					
					else if(cmd.getName().equalsIgnoreCase("tir")) {
						if(p.getRole() == Role.CHASSEUR) {
							if(p.isDead()) {
								if(args.length == 1) {
									fr.hugosimony.lguhc.player.Player victim = main.findPlayerFromUsername(args[0]);
									if(main.Ingame.contains(victim)) {
										if(!player.getName().equalsIgnoreCase(args[0])){
											victim.getPlayer().setHealth(0.1);
											Bukkit.broadcastMessage("§4[LG] §9Le chasseur a effectué son tir final !");
											main.updateScoreBoard();
											main.Ingame.remove(p);
										}else
											player.sendMessage("§4[LG] §cVous ne pouvez pas vous tirer dessus !");
									}else
										player.sendMessage("§4[LG] §cCe joueur n'est pas dans la partie !");
								}else
									player.sendMessage("§c[Erreur] La commande est §6/tir (pseudo) §c!");
							}else
								player.sendMessage("§4[LG] §cVous n'êtes pas mort !");
						}else
							player.sendMessage("§4[LG] §cVous n'êtes pas le Chasseur !");
					}
					
					//**********************************************************************
					// /couple
					
					else if(cmd.getName().equalsIgnoreCase("couple")) {
						if(p.getRole() == Role.CUPIDON) {
							if(p.canDoCouple()) {
								if(args.length == 2) {
									fr.hugosimony.lguhc.player.Player couple1 = main.findPlayerFromUsername(args[0]);
									fr.hugosimony.lguhc.player.Player couple2 = main.findPlayerFromUsername(args[1]);
									if(main.Ingame.contains(couple1) && main.Ingame.contains(couple2)) {
										if(!player.getName().equalsIgnoreCase(args[0]) && !player.getName().equalsIgnoreCase(args[1])) {
											if(!args[0].equalsIgnoreCase(args[1])) {
												couple1.setCouple(true);
												couple1.getPlayer().sendMessage("§4[LG] §9Vous êtes en §dcouple §9avec §6" + couple2.getName() + "§9 !");
												couple1.getPlayer().sendMessage("§9Votre objectif est désormais de gagner avec votre amoureux, peu importe votre rôle, ainsi "
														+ "qu'avec votre Cupidon dont vous ne connaissez pas l'idendité ! Attention, si votre amoureux meurt, vous vous "
														+ "suiciderez par amour ! §9§lBonne chance !");
												couple2.setCouple(true);
												couple2.getPlayer().sendMessage("§4[LG] §9Vous êtes en §dcouple §9avec §6" + couple1.getName() + "§9 !");
												couple2.getPlayer().sendMessage("§9Votre objectif est désormais de gagner avec votre amoureux, peu importe votre rôle, ainsi "
														+ "qu'avec votre Cupidon dont vous ne connaissez pas l'idendité ! Attention, si votre amoureux meurt, vous vous "
														+ "suiciderez par amour ! §9§lBonne chance !");
												player.sendMessage("§4[LG] §9Vous avez créé le couple entre §6" + couple1.getName() + "§9 et §6" + couple2.getName() +"§9 !");
												p.setCanDoCouple(false);
											}else
												player.sendMessage("§4[LG] §cVous ne pouvez pas mettre la même personne en couple !");
										}else
											player.sendMessage("§4[LG] §cVous ne pouvez pas vous mettre en couple !");
									}else
										player.sendMessage("§4[LG] §cAu moins un de ces deux joueurs n'est pas dans la partie !");
								}else
									player.sendMessage("§c[Erreur] La commande est §6/couple (pseudo) (pseudo) §c!");
							}else
								player.sendMessage("§4[LG] §cVous ne pouvez plus créer de couple !");
						}else
							player.sendMessage("§4[LG] §cVous n'êtes pas le Cupidon !");
					}
					
					//**********************************************************************
					// /infect
					
					else if(cmd.getName().equalsIgnoreCase("infect")) {
						if(p.getRole() == Role.IPDL) {
							if(p.canInfect()) {
								if(args.length == 1) {
									fr.hugosimony.lguhc.player.Player victim = main.findPlayerFromUsername(args[0]);
									if(main.Ingame.contains(victim)) {
										if(!player.getName().equalsIgnoreCase(args[0])){
											if(!victim.isLoup() && victim.isInfectRespawnable()){
												victim.setInfectRespawnable(false);
												victim.setDying(false);
												victim.setLoup(true);
												victim.setMechant(true);
												victim.setInfect(true);
												p.setCanInfect(false);
												main.newLoup(victim);
												//TODO Gérer le cas où le joueur s'est déconnecté
												main.respawn(victim);
												victim.getPlayer().sendMessage("§4[LG] §9Vous vous êtes fait infecté ! Vous devez désormais gagner avec le clan des loups !");
												player.sendMessage("§4[LG] §9Vous avez bien infecté §6" + victim.getName() + "§9 !");
											}else
												player.sendMessage("§4[LG] §cCe joueur ne peut pas être infecté !");
										}else
											player.sendMessage("§4[LG] §cVous ne pouvez pas vous infecter !");
									}else
										player.sendMessage("§4[LG] §cCe joueur n'est pas dans la partie !");
								}else
									player.sendMessage("§c[Erreur] La commande est §6/infect (pseudo) §c!");
							}else
								player.sendMessage("§4[LG] §cVous avez déjà infecté quelqu'un !");
						}else
							player.sendMessage("§4[LG] §cVous n'êtes pas l'Infect Père Des Loups !");
					}
					
					//**********************************************************************
					// /sauver
					
					else if(cmd.getName().equalsIgnoreCase("sauver")) {
						if(p.getRole() == Role.SORCIERE) {
							if(p.canRevive()) {
								if(args.length == 1) {
									fr.hugosimony.lguhc.player.Player victim = main.findPlayerFromUsername(args[0]);
									if(main.Ingame.contains(victim)) {
										if(!player.getName().equalsIgnoreCase(args[0])){
											if(victim.isSosoRespawnable()){
												victim.setSosoRespawnable(false);
												victim.setDying(false);
												p.setRevive(false);
												//TODO Gérer le cas où le joueur s'est déconnecté
												main.respawn(victim);
												victim.getPlayer().sendMessage("§4[LG] §9Vous vous êtes fait sauver par la sorcière ! Vous revenez in extremis à la vie !");
												player.sendMessage("§4[LG] §9Vous avez bien sauvé §6" + victim.getName() + "§9 !");
											}else
												player.sendMessage("§4[LG] §cCe joueur ne peut pas être sauvé !");
										}else
											player.sendMessage("§4[LG] §cVous ne pouvez pas vous sauver !");
									}else
										player.sendMessage("§4[LG] §cCe joueur n'est pas dans la partie !");
								}else
									player.sendMessage("§c[Erreur] La commande est §6/sauver (pseudo) §c!");
							}else
								player.sendMessage("§4[LG] §cVous avez déjà sauvé quelqu'un !");
						}else
							player.sendMessage("§4[LG] §cVous n'êtes pas la Sorcière !");
					}
					
					//**********************************************************************
					// /maitre
					
					else if(cmd.getName().equalsIgnoreCase("maitre")) {
						if(p.getRole() == Role.ENFANT_SAUVAGE) {
							if(p.canChoseMaitre()) {
								if(args.length == 1) {
									fr.hugosimony.lguhc.player.Player maitre = main.findPlayerFromUsername(args[0]);
									if(main.Ingame.contains(maitre)) {
										if(!player.getName().equalsIgnoreCase(args[0])){
											p.setMaitre(maitre);
											p.setCanChoseMaitre(false);
											player.sendMessage("§4[LG] §9Vous avez bien choisi §6"+ maitre.getName() + "§9 en tant que maître !");
										}else
											player.sendMessage("§4[LG] §cVous ne pouvez pas vous choisir en tant que maître !");
									}else
										player.sendMessage("§4[LG] §cCe joueur n'est pas dans la partie !");
								}else
									player.sendMessage("§c[Erreur] La commande est §6/maitre (pseudo) §c!");
							}else
								player.sendMessage("§4[LG] §cVous ne pouvez plus choisir de maître !");
						}else
							player.sendMessage("§4[LG] §cVous n'êtes pas l'Enfant Sauvage !");
					}
					
					//**********************************************************************
					// /flairer
					
					else if(cmd.getName().equalsIgnoreCase("flairer")) {
						if(p.getRole() == Role.RENARD) {
							if(args.length == 1) {
								if(p.getFlairs() != 0) {
									fr.hugosimony.lguhc.player.Player flaire = main.findPlayerFromUsername(args[0]);
									if(main.Ingame.contains(flaire)) {
										if(!player.getName().equalsIgnoreCase(args[0])){
											Location flair_location = flaire.getPlayer().getLocation();
											Location renard_location = player.getLocation();
											double flair_x = flair_location.getX();		double flair_z = flair_location.getZ();
											double renard_x = renard_location.getX();	double renard_z = renard_location.getZ();
											if((flair_x-renard_x > -2 && flair_x-renard_x < 2 && flair_z-renard_z > -2 && flair_z-renard_z < 2)
													|| (renard_x-flair_x > -2 && renard_x-flair_x < 2 && renard_z-flair_z > -2  && -flair_z < 2)
													|| (renard_x-flair_x > -2 && renard_x-flair_x < 2 && flair_z-renard_z > -2  && flair_z-renard_z < 2)
													|| (flair_x-renard_x > -2 && flair_x-renard_x < 2 && renard_z-flair_z > -2  && renard_z-flair_z < 2)) {
												if(flaire.isMechant()) 
													player.sendMessage("§4[LG] §cLe joueur flairé §lappartient§r§c au camp des Loups Garous !");
												else
													player.sendMessage("§4[LG] §aLe joueur flairé §ln'appartient pas§r§a au camp des Loups Garous !");
												p.setFlairs(p.getFlairs()-1);
											}else
												player.sendMessage("§4[LG] §cLe joueur n'est pas assez proche !");
										}else
											player.sendMessage("§4[LG] §cVous ne pouvez pas vous flairer vous même !");
									}else
										player.sendMessage("§4[LG] §cCe joueur n'est pas dans la partie !");
								}else
									player.sendMessage("§4[LG] §cVous ne pouvez plus flairer personne !");
							}else
								player.sendMessage("§c[Erreur] La commande est §6/flairer (pseudo) §c!");
						}else
							player.sendMessage("§4[LG] §cVous n'êtes pas le Renard !");
					}
					
					//**********************************************************************
					// /proteger
					
					else if(cmd.getName().equalsIgnoreCase("proteger")) {
						if(p.getRole() == Role.SALVA) {
							if(args.length == 1) {
								if(p.canProtect()) {
									fr.hugosimony.lguhc.player.Player protect = main.findPlayerFromUsername(args[0]);
									if(main.Ingame.contains(protect)) {
										System.out.println(protect.isProtecte());
										if(!protect.isProtecte()){
											player.sendMessage("§4[LG] §9Vous avez bien protégé §6" + protect.getName() + "§9 !");
											for(fr.hugosimony.lguhc.player.Player player_: main.Ingame) {
												if(player_.isProtecte())
													player_.setProtecte(false);
											}
											protect.setProtecte(true);
											p.setCanProtect(false);
										}else
											player.sendMessage("§4[LG] §cVous ne pouvez pas protéger le même joueur deux fois de suite !");
									}else
										player.sendMessage("§4[LG] §cCe joueur n'est pas dans la partie !");
								}else
									player.sendMessage("§4[LG] §cVous ne pouvez pas/plus protéger quelqu'un pour l'instant !");
							}else
								player.sendMessage("§c[Erreur] La commande est §6/proteger (pseudo) §c!");
						}else
							player.sendMessage("§4[LG] §cVous n'êtes pas le Salvateur !");
					}
					
					//**********************************************************************
					// /voir
					
					else if(cmd.getName().equalsIgnoreCase("voir")) {
						if(p.getRole() == Role.VOYANTE) {
							if(args.length == 1) {
								if(p.canVoir()) {
									fr.hugosimony.lguhc.player.Player vu = main.findPlayerFromUsername(args[0]);
									if(main.Ingame.contains(vu)) {
										if(!player.getName().equalsIgnoreCase(args[0])){
											if(vu.getRole() == Role.LG_ANONYME)
												player.sendMessage("§4[LG] §9Le joueur observé est §6Simple villageois §9 !");
											else
												player.sendMessage("§4[LG] §9Le joueur observé est §6" + main.getRoleString(vu.getRole()) + "§9 !");
											p.setCanVoir(false);
										}else
											player.sendMessage("§4[LG] §cVous connaissez déjà votre rôle !");
									}else
										player.sendMessage("§4[LG] §cCe joueur n'est pas dans la partie !");
								}else
									player.sendMessage("§4[LG] §cVous ne pouvez pas/plus voir quelqu'un pour l'instant !");
							}else
								player.sendMessage("§c[Erreur] La commande est §6/voir (pseudo) §c!");
						}else
							player.sendMessage("§4[LG] §cVous n'êtes pas la Voyante !");
					}
					
					//**********************************************************************
					// /vote
					
					else if(cmd.getName().equalsIgnoreCase("vote")) {
						if(args.length == 1) {
							if(p.canVote()) {
								fr.hugosimony.lguhc.player.Player vote = main.findPlayerFromUsername(args[0]);
								if(main.Ingame.contains(vote)) {
									if(!player.getName().equalsIgnoreCase(args[0])){
										if(main.player_vote == null || (!main.player_vote.getName().equalsIgnoreCase(args[0]))) {
											player.sendMessage("§4[LG] §9Votre vote contre §6" + vote.getName() + "§9 a bien été comptabilisé !");
											vote.setVote(vote.getVote()+1);
											p.setCanVote(false);
										}else
											player.sendMessage("§4[LG] §cVous ne pouvez pas voter contre quelqu'un qui a pris les votes la veille !");
									}else
										player.sendMessage("§4[LG] §cVous ne pouvez pas voter contre vous !");
								}else
									player.sendMessage("§4[LG] §cCe joueur n'est pas dans la partie !");
							}else
								player.sendMessage("§4[LG] §cVous ne pouvez pas/plus voter pour l'instant !");
						}else
							player.sendMessage("§c[Erreur] La commande est §6/vote (pseudo) §c!");
					}
					
					//**********************************************************************
					// /lgt
					
					else if(cmd.getName().equalsIgnoreCase("lgt")) {
						if(p.isLoup()) {
							if(main.isState(State.NIGHT)) {
								if(args.length == 0)
									player.sendMessage("§c[Erreur] La commande est §6/lgt (message) §9!");
								else {
									String message = "";
									for(String s: args)
										message+=s+" ";
									for(fr.hugosimony.lguhc.player.Player player_: main.Ingame) {
										if(player_.isLoup())
											player_.getPlayer().sendMessage("§4[Loups] §c" + player.getName() + " : " +  message);
										else if(player_.getRole() == Role.PF)
											player_.getPlayer().sendMessage("§4[Loups] §c******* : " + message);
									}
								}
							}else 
								player.sendMessage("§4[LG] §cVous ne pouvez parler dans ce chat que la nuit !");
						}else 
							player.sendMessage("§4[LG] §cVous n'appartenez pas au camp des loups !");
					}
					
					//**********************************************************************
					
					}else 
						player.sendMessage("§4[LG] §cVous n'avez pas encore de rôle !");
				}else
					player.sendMessage("§c[Erreur] Vous n'êtes pas/plus dans la partie !");
			}else
				player.sendMessage("§c[Erreur] La partie n'a pas encore démarré ou est terminée !");
			
			//**********************************************************************
		}
		return true; //Default
	}

}
