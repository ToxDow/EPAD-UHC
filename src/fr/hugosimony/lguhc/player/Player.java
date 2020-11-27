package fr.hugosimony.lguhc.player;

import org.bukkit.Location;

import fr.hugosimony.lguhc.Main;

public class Player{

	// Constructeur
	private org.bukkit.entity.Player player;
	private Main main;
	public Player(org.bukkit.entity.Player player, Main main) {
		this.main = main;
		this.player = player;
		name = player.getName();
		killer = null;
		role = Role.NONE;
		transformed = false;
		claimed = false;
		dying = false;
		dead = false;
		ancien_respawn = false;
		can_infect = true;
		is_infect_respawnable = false;
		infect = false;
		can_revive = true;
		is_soso_respawnable = false;
		is_loup = false;
		is_mechant = false;
		can_chose_maitre = true;
		maitre = null;
		flairs = 3; // Arbitraire
		can_voir = false;
		can_protect = false;
		protecte = false;
		can_do_couple = true;
		is_couple = false;
		can_vote = false;
		nb_vote = 0;
		diamonds_mined = 0;
	}
	
	// Getter du player
	public org.bukkit.entity.Player getPlayer() {
		return player;
	}
	
	// Rôle du joueur (Getter and Setter)
	private Role role;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	// Username du joueur (Getter)
	private String name;
	public String getName() {
		return name;
	}
	
	// Setter et getter du killer
	private org.bukkit.entity.Player killer;
	public void setKiller(org.bukkit.entity.Player killer) {
		this.killer = killer;
	}
	public org.bukkit.entity.Player getKiller(){
		return killer;
	}
	
	// Setter et getter de claim
	private boolean claimed;
	public boolean hasClaimed() {
		return claimed;
	}
	public void setClaimed(boolean claim) {
		claimed = claim;
	}
	
	// Setter et getter de dying
	private boolean dying;
	public boolean isDying() {
		return dying;
	}
	public void setDying(boolean die) {
		dying = die;
	}
	
	// Setter et getter de dead
	private boolean dead;
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead_) {
		dead = dead_;
	}
	
	// Setter et getter de ancien_respawn
	private boolean ancien_respawn;
	public boolean hasAncienRespawn() {
		return ancien_respawn;
	}
	public void setAncienRespawn(boolean ancien) {
		ancien_respawn = ancien;
	}
	
	// Setter et getter de can_infect
	private boolean can_infect;
	public boolean canInfect() {
		return can_infect;
	}
	public void setCanInfect(boolean infect) {
		can_infect = infect;
	}
	
	// Setter et getter de is_infect_respawnable
	private boolean is_infect_respawnable;
	public boolean isInfectRespawnable() {
		return is_infect_respawnable;
	}
	public void setInfectRespawnable(boolean infect) {
		is_infect_respawnable = infect;
	}
	
	// Setter et getter de is_infect_respawnable
	private boolean infect;
	public boolean isInfect() {
		return infect;
	}
	public void setInfect(boolean infected) {
		infect = infected;
	}
	
	// Setter et getter de can_revive
	private boolean can_revive;
	public boolean canRevive() {
		return can_revive;
	}
	public void setRevive(boolean soso) {
		can_revive = soso;
	}
	
	// Setter et getter de is_soso_respawnable
	private boolean is_soso_respawnable;
	public boolean isSosoRespawnable() {
		return is_soso_respawnable;
	}
	public void setSosoRespawnable(boolean soso) {
		is_soso_respawnable = soso;
	}
	
	// Setter et getter de is_loup
	private boolean is_loup;
	public boolean isLoup() {
		return is_loup;
	}
	public void setLoup(boolean loup) {
		is_loup = loup;
	}
	
	// Setter et getter de is_mechant
	private boolean is_mechant;
	public boolean isMechant() {
		return is_mechant;
	}
	public void setMechant(boolean mechant) {
		is_mechant = mechant;
	}
	
	// Setter et getter de maitre
	private Player maitre;
	public Player getMaitre() {
		return maitre;
	}
	public void setMaitre(Player maitre_) {
		maitre = maitre_;
	}
	
	// Setter et getter de can_chose_maitre
	private boolean can_chose_maitre;
	public boolean canChoseMaitre() {
		return can_chose_maitre;
	}
	public void setCanChoseMaitre(boolean maitre) {
		can_chose_maitre = maitre;
	}
	
	// Setter et getter de l'enfant sauvage				
	private boolean transformed;
	public boolean isTransformed() {
		return transformed;
	}
	public void setTransformed(boolean transform) {
		transformed = transform;
	}
	
	// Setter et getter du renard
	private int flairs;
	public int getFlairs() {
		return flairs;
	}
	public void setFlairs(int flair) {
		flairs = flair;
	}
	
	// Setter et getter de la voyante
	private boolean can_voir;
	public boolean canVoir() {
		return can_voir;
	}
	public void setCanVoir(boolean voir) {
		can_voir = voir;
	}
	
	// Setter et getter du salvateur
	private boolean can_protect;
	public boolean canProtect() {
		return can_protect;
	}
	public void setCanProtect(boolean protect) {
		can_protect = protect;
	}
	
	// Setter et getter du salvaté
	private boolean protecte;
	public boolean isProtecte() {
		return protecte;
	}
	public void setProtecte(boolean protect) {
		protecte = protect;
	}
	
	// Setter et getter du cupidon
	private boolean can_do_couple;
	public boolean canDoCouple() {
		return can_do_couple;
	}
	public void setCanDoCouple(boolean couple) {
		can_do_couple = couple;
	}
	
	// Setter et getter du couple
	private boolean is_couple;
	public boolean isCouple() {
		return is_couple;
	}
	public void setCouple(boolean couple) {
		is_couple = couple;
	}
	
	public Player getCouple() {
		Player couple = new Player(player, main);
		for(Player p : main.Ingame) {
			if(p.isCouple() && p.getName() != name)
				couple = p;
		}
		return couple;
	}
	
	// Setter et getter du can_vote
	private boolean can_vote;
	public boolean canVote() {
		return can_vote;
	}
	public void setCanVote(boolean vote) {
		can_vote = vote;
	}
	
	// Setter et getter du vote
	private int nb_vote;
	public int getVote() {
		return nb_vote;
	}
	public void setVote(int vote) {
		nb_vote = vote;
	}
	
	// Setter et getter du vote
	private int diamonds_mined;
	public int getDiamondsMined() {
		return diamonds_mined;
	}
	public void setDiamondsMined(int diamond) {
		diamonds_mined = diamond;
	}
	
	// Location de mort
	public Location death_location;

}