package fr.hugosimony.lguhc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.hugosimony.lguhc.commands.Host;
import fr.hugosimony.lguhc.commands.Roles;
import fr.hugosimony.lguhc.effects.Effects;
import fr.hugosimony.lguhc.listeners.Config;
import fr.hugosimony.lguhc.listeners.Connexion;
import fr.hugosimony.lguhc.listeners.Damage;
import fr.hugosimony.lguhc.listeners.Global;
import fr.hugosimony.lguhc.player.Player;
import fr.hugosimony.lguhc.player.Role;


public class Main extends JavaPlugin {

	// Global
	public boolean game_started;
	public boolean role_selected;
	
	// Scoreboard
	public static Main instance;
	//public Map<org.bukkit.entity.Player, ScoreboardSign> boards = new HashMap<>();
	public HashMap<org.bukkit.entity.Player, Scoreboard> scoreboard = new HashMap<>();
	public static Main getInstance() {
		return instance;
	}
	
	// Joueurs
	public ArrayList<Player> Connected = new ArrayList<Player>();
	public ArrayList<Player> Ingame = new ArrayList<Player>();
	public int maxPlayer = 25;
	public Player player_vote = null;
	
	// UHC
	public int pvp_time = 21;
	public int pvp_time_s = 1260;
	public int border_time = 90;
	public int border_time_s = 5400;
	public int border_size = 1000;
	public double border_speed = 1.0;
	public int role_time = 1;			// 20
	public int role_time_s = 60;		// 1200
	public int cycle_jn = 5;
	public int global_timer = 0;
	public World world;
	public WorldBorder border;
	
	// Rôles
	public Role[] RoleIngame;

	// Compo par défaut
	public int ancienC = 1;   	 		// 1
	public int assassinC = 0; 			// 1
	public int chasseurC = 0;			// 1
	public int cupidonC = 0;			// 1
	public int detectiveC = 0;			// 1
	public int enfant_sauvageC = 0;		// 1
	public int ipdlC = 0;				// 1
	public int lgC = 0;					// 3
	public int lg_amneC = 0;			// 1
	public int lg_anoC = 0;				// 0
	public int lgbC = 0;				// 1
	public int paranoC = 0;				// 1
	public int pfC = 0;					// 1
	public int renardC = 0;				// 1
	public int salvaC = 0;				// 1
	public int soeurC = 0;				// 0
	public int sorciereC = 0;			// 1
	public int svC = 0;					// 1
	public int voleurC = 0;				// 0
	public int voyanteC = 0;			// 1
	public int vplC = 0;				// 1
	
	// Effects
	Effects effects = new Effects(this);
	
	// Scénarios
	public boolean noFood = false;
	public boolean noFall = false;
	public boolean noFire = false;
	public boolean diamondLimit = true;
	public int diamondLimitMax = 17;
	
	//**********************************************************************
	@Override
	public void onEnable() {
		System.out.println("Plugin Loup Garou UHC started");
		
		instance = this;
		super.onEnable();
		game_started = false;
		role_selected = false;
		pvp = false;
		setState(State.CONFIG);
		
		world = Bukkit.getWorld("world");
		border = world.getWorldBorder();
		border.setCenter(0,0);
		border.setSize(border_size*2);
		
		// Listeners
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Global(this), this);
		pm.registerEvents(new Connexion(this), this);
		pm.registerEvents(new Config(this), this);
		pm.registerEvents(new Damage(this), this);
		
		// Commandes :
		
		Host host = new Host(this);
		getCommand("host").setExecutor(host);
		getCommand("say").setExecutor(host);
		
		Roles roles = new Roles(this);
		getCommand("role").setExecutor(roles);
		getCommand("roles").setExecutor(roles);
		getCommand("claim").setExecutor(roles);
		getCommand("tir").setExecutor(roles);
		getCommand("couple").setExecutor(roles);
		getCommand("infect").setExecutor(roles);
		getCommand("sauver").setExecutor(roles);
		getCommand("maitre").setExecutor(roles);
		getCommand("flairer").setExecutor(roles);
		getCommand("proteger").setExecutor(roles);
		getCommand("voir").setExecutor(roles);
		getCommand("vote").setExecutor(roles);
		getCommand("lgt").setExecutor(roles);
	}
	
	@Override
	public void onDisable() {
		System.out.println("Plugin Loup Garou UHC finished");
	}
	
	//**********************************************************************
	
	private State state;
	
	public void setState(State state) {
		this.state = state;
	}
	public boolean isState(State state) {
		return this.state == state;
	}
	
	private boolean pvp;
	public void setPvp(boolean on_off) {
		pvp = on_off;
	}
	public boolean isPvpOn() {
		return pvp;
	}
	
	//**********************************************************************
	
	public boolean contains(ArrayList<Player> list, org.bukkit.entity.Player player) {
		boolean founded = false;
		for(Player p: list) {
			if(p.getName().equalsIgnoreCase(player.getName()))
				founded = true;
		}
		return founded;
	}
	
	//**********************************************************************
	
	public void setKiller(ArrayList<Player> list, org.bukkit.entity.Player player, org.bukkit.entity.Player killer) {
		for(Player p: list) {
			if(p.getName().equalsIgnoreCase(player.getName()))
				p.setKiller(killer);
		}
	}
	
	//**********************************************************************
	
	public Player findPlayer(org.bukkit.entity.Player player) {
		
		Player p = new Player(player, this);
		for(Player player_: Ingame) {
			if(player_.getName().equalsIgnoreCase(player.getName()))
				p = player_;
		}
		return p;
	}
	
	public Player findPlayerFromUsername(String s) {
		
		Player p = new Player(Ingame.get(0).getPlayer(), this); // Initialiser à un joueur différent (grâce au "new")
		for(Player player: Ingame) {
			if(player.getName().equalsIgnoreCase(s))
				p = player;
		}
		return p;
	}
	
	//**********************************************************************
	
	public Role getRole(ArrayList<Player> list, org.bukkit.entity.Player player) {
		Role role = Role.NONE;
		for(Player p: list) {
			if(p.getName().equalsIgnoreCase(player.getName()))
				role = p.getRole();
		}
		return role;
	}
	
	public String getRoleString(Role role) {
		String role_string = "";
		if(role == Role.ANCIEN)
			role_string = "Ancien";
		else if (role == Role.ASSASSIN)
			role_string = "Assassin";
		else if (role == Role.CHASSEUR)
			role_string = "Chasseur";
		else if (role == Role.CUPIDON)
			role_string = "Cupidon";
		else if (role == Role.DETECTIVE)
			role_string = "Détective";
		else if (role == Role.ENFANT_SAUVAGE)
			role_string = "Enfant Sauvage";
		else if (role == Role.IPDL)
			role_string = "Infect Père Des Loups";
		else if (role == Role.LG)
			role_string = "Loup Garou";
		else if (role == Role.LG_AMNESIQUE)
			role_string = "Loup Garou Amnésique";
		else if (role == Role.LG_ANONYME)
			role_string = "Loup Garou Anonyme";
		else if (role == Role.LGB)
			role_string = "Loup Garou Blanc";
		else if (role == Role.PARANO)
			role_string = "Parano";
		else if (role == Role.PF)
			role_string = "Petite Fille";
		else if (role == Role.RENARD)
			role_string = "Renard";
		else if (role == Role.SALVA)
			role_string = "Salvateur";
		else if (role == Role.SOEUR)
			role_string = "Soeur";
		else if (role == Role.SORCIERE)
			role_string = "Sorcière";
		else if (role == Role.SV)
			role_string = "Simple villageois";
		else if (role == Role.VOLEUR)
			role_string = "Voleur";
		else if (role == Role.VOYANTE)
			role_string = "Voyante";
		else if (role == Role.VPL)
			role_string = "Vilan Petit Loup";
		return role_string;
	}

	//**********************************************************************
	
	public void plusMoinsRoles(int x, String name) {
		if(name == "§9Ancien" && !(ancienC == 0 && x == -1))
			ancienC += x;
		else if (name == "§6Assassin" && !(assassinC == 0 && x == -1))
			assassinC += x;
		else if (name == "§9Chasseur" && !(chasseurC == 0 && x == -1))
			chasseurC += x;
		else if (name == "§dCupidon" && !(cupidonC == 0 && x == -1))
			cupidonC += x;
		else if (name == "§9Détective" && !(detectiveC == 0 && x == -1))
			detectiveC += x;
		else if (name == "§2Enfant Sauvage" && !(enfant_sauvageC == 0 && x == -1))
			enfant_sauvageC += x;
		else if (name == "§cIPDL" && !(ipdlC == 0 && x == -1))
			ipdlC += x;
		else if (name == "§cLoup Garou" && !(lgC == 0 && x == -1))
			lgC += x;
		else if (name == "§cLoup Garou Amnésique" && !(lg_amneC == 0 && x == -1))
			lg_amneC += x;
		else if (name == "§cLoup Garou Anonyme" && !(lg_anoC == 0 && x == -1))
			lg_anoC += x;
		else if (name == "§4Loup Garou Blanc" && !(lgbC == 0 && x == -1))
			lgbC += x;
		else if (name == "§9Parano" && !(paranoC == 0 && x == -1))
			paranoC += x;
		else if (name == "§9Petite Fille" && !(pfC == 0 && x == -1))
			pfC += x;
		else if (name == "§9Renard" && !(renardC == 0 && x == -1))
			renardC += x;
		else if (name == "§9Salvateur" && !(salvaC == 0 && x == -1))
			salvaC += x;
		else if (name == "§9Soeur" && !(soeurC == 0 && x == -1))
			soeurC += x;
		else if (name == "§9Sorcière" && !(sorciereC == 0 && x == -1))
			sorciereC += x;
		else if (name == "§9Simple Villageois" && !(svC == 0 && x == -1))
			svC += x;
		else if (name == "§6Voleur" && !(voleurC == 0 && x == -1))
			voleurC += x;
		else if (name == "§9Voyante" && !(voyanteC == 0 && x == -1))
			voyanteC += x;
		else if (name == "§cVilain Petit Loup" && !(vplC == 0 && x == -1))
			vplC += x;
	}
	
	public int countRoles() {
		return ancienC + assassinC + chasseurC + cupidonC + detectiveC + enfant_sauvageC + ipdlC + lgC + lg_amneC 
				+ lg_anoC + lgbC + paranoC + pfC + renardC + salvaC + soeurC*2 + sorciereC + svC + voleurC + voyanteC + vplC;
	}
	
	public int countRole(Role role) {
		int count = 0;
		for(Player player: Ingame) {
			if(player.getRole() == role)
				count++;
		}
		return count;
	}
	
	//**********************************************************************
	
	public void InitializeRoleIngame() {
		for(int i = 0; i < RoleIngame.length; i++)
			RoleIngame[i] = Role.INIT;
	}
	
	public void setRoleIngame() {
		int i = 0;
		for(int j = 0; j < ancienC; j++) {
			RoleIngame[i] = Role.ANCIEN;
			i++;
		}
		for(int j = 0; j < assassinC; j++) {
			RoleIngame[i] = Role.ASSASSIN;
			i++;
		}
		for(int j = 0; j < chasseurC; j++) {
			RoleIngame[i] = Role.CHASSEUR;
			i++;
		}
		for(int j = 0; j < cupidonC; j++) {
			RoleIngame[i] = Role.CUPIDON;
			i++;
		}
		for(int j = 0; j < detectiveC; j++) {
			RoleIngame[i] = Role.DETECTIVE;
			i++;
		}
		for(int j = 0; j < enfant_sauvageC; j++) {
			RoleIngame[i] = Role.ENFANT_SAUVAGE;
			i++;
		}
		for(int j = 0; j < ipdlC; j++) {
			RoleIngame[i] = Role.IPDL;
			i++;
		}
		for(int j = 0; j < lgC; j++) {
			RoleIngame[i] = Role.LG;
			i++;
		}
		for(int j = 0; j < lg_amneC; j++) {
			RoleIngame[i] = Role.LG_AMNESIQUE;
			i++;
		}
		for(int j = 0; j < lg_anoC; j++) {
			RoleIngame[i] = Role.LG_ANONYME;
			i++;
		}
		for(int j = 0; j < lgbC; j++) {
			RoleIngame[i] = Role.LGB;
			i++;
		}
		for(int j = 0; j < paranoC; j++) {
			RoleIngame[i] = Role.PARANO;
			i++;
		}
		for(int j = 0; j < pfC; j++) {
			RoleIngame[i] = Role.PF;
			i++;
		}
		for(int j = 0; j < renardC; j++) {
			RoleIngame[i] = Role.RENARD;
			i++;
		}
		for(int j = 0; j < salvaC; j++) {
			RoleIngame[i] = Role.SALVA;
			i++;
		}
		for(int j = 0; j < soeurC*2; j++) {
			RoleIngame[i] = Role.SOEUR;
			i++;
		}
		for(int j = 0; j < sorciereC; j++) {
			RoleIngame[i] = Role.SORCIERE;
			i++;
		}
		for(int j = 0; j < svC; j++) {
			RoleIngame[i] = Role.SV;
			i++;
		}
		for(int j = 0; j < voleurC; j++) {
			RoleIngame[i] = Role.VOLEUR;
			i++;
		}
		for(int j = 0; j < voyanteC; j++) {
			RoleIngame[i] = Role.VOYANTE;
			i++;
		}
		for(int j = 0; j < vplC; j++) {
			RoleIngame[i] = Role.VPL;
			i++;
		}
	}
	
	//**********************************************************************
	
	public void deleteRandomRole(int count) {
		int role = RoleIngame.length;
		int count_ = count;
		int random;
		while(count_ > 0) {
			random = (int) (Math.random() * role);
			if(RoleIngame[random] == Role.INIT) {
				RoleIngame[random] = Role.NONE;
				count_--;
			}
		}
	}
	
	//**********************************************************************
	
	public void assignRole() {
		int count = Ingame.size();
		int count_role = countRoles();
		RoleIngame = new Role[count];
		InitializeRoleIngame();
		if(count_role > count)
			deleteRandomRole(count_role-count);
		setRoleIngame();
		int roles = count;
		int random;
		while(count > 0) {
			random = (int) (Math.random() * roles);
			if(RoleIngame[random] != Role.NONE) {
				Ingame.get(count-1).setRole(RoleIngame[random]);
				RoleIngame[random] = Role.NONE;
				count--;
			}
		}
		for(Player player : Ingame)
			printRole(true, player);
		for(Player player : Ingame) {
			if(player.isLoup())
				ListeLg(player.getPlayer());
		}
		
	}
	
	//**********************************************************************
	
	public void printRole(boolean first, Player player) {
		
		org.bukkit.entity.Player p = player.getPlayer();
		if(first) {
			if(player.getRole() == Role.ANCIEN)
				p.addPotionEffect(effects.resistance, true);
			else if(player.getRole() == Role.IPDL || player.getRole() == Role.LG || (player.getRole() == Role.LG_AMNESIQUE && player.isTransformed())
					|| player.getRole() == Role.LG_ANONYME || player.getRole() == Role.LGB || player.getRole() == Role.PF)
				p.addPotionEffect(effects.night_vision, true);
			else if (player.getRole() == Role.RENARD) {
				System.out.print("test");
				p.addPotionEffect(effects.speed, true);
				}
			if(player.getRole() == Role.LGB) {
				p.setMaxHealth(30);
				p.setHealth(30);
			}
			if(player.getRole() == Role.CUPIDON) {
				Bukkit.getScheduler().runTaskLater(this, () -> {
					if(Ingame.contains(player) && player.canDoCouple()) {
						p.sendMessage("§4[LG] §cVous n'avez pas formé de couple ! Vous jouerez donc comme un Simple Villageois cette partie !");
						player.setCanDoCouple(false);
					}
				},1200);
			}
			if(player.getRole() == Role.ENFANT_SAUVAGE) {
				Bukkit.getScheduler().runTaskLater(this, () -> {
					if(Ingame.contains(player) && player.canChoseMaitre()) {
						p.sendMessage("§4[LG] §cVous n'avez pas choisi de maître ! Vous jouerez donc comme un Simple Villageois cette partie !");
						player.setCanChoseMaitre(false);
					}
				},1200);
			}
		}
		
		//Ancien 
		if(player.getRole() == Role.ANCIEN) {
			p.sendMessage("§4[LG] §9Vous êtes §6§lAncien :"); 
			p.sendMessage("§9Votre objectif est de gagner avec le clan du village. Vous devez tuer tous les loups garous et le reste des traîtres. "
					+ "Vous bénéficiez de l'effet Resistance I en permanance pour vous aider à effectuer votre tâche. De plus vous avez une deuxième vie "
					+ "obtenue par votre expérience ! Utilisez la précieusement. §9§lBonne chance !");
		}
		
		//Assassin
		else if(player.getRole() == Role.ASSASSIN) {
			p.sendMessage("§4[LG] §9Vous êtes §6§lAssassin :"); 
			p.sendMessage("§9Votre objectif est de gagner seul(e). Vous devez tuer tout le monde. Vous bénéficiez de l'effet Force I le jour pour "
					+ "vous aider à effectuer votre tâche. De plus, vous pouvez récupérer vos livres : Sharpness 3, Protection 3 et Power 3 en effectuant "
					+ "la commande §6/claim. §9§lBonne chance !");
		}
		
		//Chasseur
		else if(player.getRole() == Role.CHASSEUR) {
			p.sendMessage("§4[LG] §9Vous êtes §6§lChasseur :"); 
			p.sendMessage("§9Votre objectif est de gagner avec le clan du village. Vous devez tuer tous les loups garous et le reste des traîtres. "
					+ "Vous pouvez récupérer votre arc, votre livre Power 2 & Punch 2 et vos 32 flêches en effectuant la commande §6/claim §9pour vous aider "
					+ "à effectuer votre tâche. De plus, lors de votre mort, vos talents de chasseur vous permettent d'effectuer un tir final grâce à la "
					+ "commande §6/tir (pseudo) §9§lBonne chance !");
		}
		
		//Cupidon
		else if(player.getRole() == Role.CUPIDON) {
			p.sendMessage("§4[LG] §9Vous êtes §6§lCupidon :"); 
			p.sendMessage("§9Votre objectif est de gagner avec le couple. Vous devez tuer tout le monde hors du couple. Vous pouvez récupérer votre arc, "
					+ "votre livre Power 2 & Punch 2 et vos 32 flèches en effectuant la commande §6/claim §9pour vous aider à effectuer votre tâche. "
					+ "De plus, vous avez §l60 secondes§r§9 pour composer le couple à l'aide de la commande §6/couple (pseudo1) (pseudo2). §9Enfin, si le couple "
					+ "venait à mourir, vous rejoindrez le camp des villageois et devrez tuer tous les loups garous et le reste des traîtres. §9§lBonne chance !");
		}
		
		//Détective
		else if(player.getRole() == Role.DETECTIVE) {
			p.sendMessage("Vous êtes detective, rôle pas encore codé !");
		}
		
		//Enfant Sauvage
		else if(player.getRole() == Role.ENFANT_SAUVAGE) {
			p.sendMessage("§4[LG] §9Vous êtes §6§lEnfant Sauvage :"); 
			p.sendMessage("§9Votre objectif est de gagner avec le clan du village. Vous devez tuer tous les loups garous. Cepandant, vous pouvez à tout moment "
					+ "changer de camp si votre maître meurt. Votre objectif sera alors de gagner avec les loups garous. Vous avez §l60 secondes§r§9 pour choisir votre "
					+ "maître en effectuant la commande §6/maitre (pseudo). §9§lBonne chance !");
			if(player.isTransformed()) {
				p.sendMessage("§4[LG] §9Vous êtes §6§lTransformé §9!"); 
				p.sendMessage("§4[LG] §cLa liste des §4§lLoups Garous §r§cest : ");
			}
		}
		
		//IPDL
		else if(player.getRole() == Role.IPDL) {
			player.setLoup(true);
			player.setMechant(true);
			p.sendMessage("§4[LG] §9Vous êtes §6§lInfect Père Des Loups :");
			p.sendMessage("§9Votre objectif est de gagner avec le clan des loups. Vous devez tuer tout le clan du village et le reste des traîtres. "
					+ "Vous bénéficiez de l'effet Force I la nuit pour vous aider à effectuer votre tâche. De plus, vous pouvez infecter un joueur tué "
					+ "par un loup garou grace à la commande §6/infect (pseudo) §9pour le faire devenir un loup garou infécté. "
					+ "Vous pouvez parler entre loups peandnt la nuit grâce à la commande §6/lgt (message). §9§lBonne chance !");
			p.sendMessage("§4[LG] §cLa liste des §4§lLoups Garous §r§cest : ");
		}
		
		//LG
		else if(player.getRole() == Role.LG) {
			player.setLoup(true);
			player.setMechant(true);
			p.sendMessage("§4[LG] §9Vous êtes §6§lLoup Garou :");
			p.sendMessage("§9Votre objectif est de gagner avec le clan des loups. Vous devez tuer tout le clan du village et le reste des traîtres. "
					+ "Vous bénéficiez de l'effet Force I la nuit pour vous aider à effectuer votre tâche. Vous pouvez parler entre loups grâce à la "
					+ "commande §6/lgt (message). §9§lBonne chance !");
			p.sendMessage("§4[LG] §cLa liste des §4§lLoups Garous §r§cest : ");
		}
		
		//LG Anonyme
		else if(player.getRole() == Role.LG_AMNESIQUE && player.isTransformed()) {
			player.setLoup(true);
			player.setMechant(true);
			p.sendMessage("§4[LG] §9Vous êtes §6§lLoup Garou Amnésique:");
			p.sendMessage("§9Vous avez retrouvé la mémoire ! Votre objectif est de gagner avec le clan des loups. Vous devez tuer tout le clan du "
					+ "village et le reste des traîtres. Vous bénéficiez de l'effet Force I la nuit pour vous aider à effectuer votre tâche. Vous "
					+ "pouvez parler entre loups grâce à la commande §6/lgt (message). §9§lBonne chance !");
			p.sendMessage("§4[LG] §cLa liste des §4§lLoups Garous §r§cest : ");
		}
		
		//LG Anonyme
		else if(player.getRole() == Role.LG_ANONYME) {
			player.setLoup(true);
			p.sendMessage("§4[LG] §9Vous êtes §6§lLoup Garou Anonyme:");
			p.sendMessage("§9Votre objectif est de gagner avec le clan des loups. Vous devez tuer tout le clan du village et le reste des traîtres. "
					+ "Vous bénéficiez de l'effet Force I la nuit pour vous aider à effectuer votre tâche. Vous êtes très discret. Lorsque la voyante "
					+ "ou le renard ou le parano vous espionne, ils vous pensent innocent ! Utilisez cet atout à bon escient. Vous pouvez parler entre "
					+ "loups grâce à la commande §6/lgt (message). §9§lBonne chance !");
			p.sendMessage("§4[LG] §cLa liste des §4§lLoups Garous §r§cest : ");
		}
		
		//LGB
		else if(player.getRole() == Role.LGB) {
			player.setLoup(true);
			player.setMechant(true);
			p.sendMessage("§4[LG] §9Vous êtes §6§lLoup Garou Blanc :"); 
			p.sendMessage("§9Votre objectif est de gagner seul(e). Vous devez tuer tout le monde. Vous bénéficiez de l'effet Force I le nuit et de 5 coeurs "
					+ "suplémentaires pour vous aider à effectuer votre tâche. De plus, vous connaissez l'identité des autres loups garous et ils ne savent "
					+ "pas (pour l'instant) que vous êtes contre eux. Vous pouvez parler entre loups grâce à la commande §6/lgt (message). §9§lBonne chance !");
			p.sendMessage("§4[LG] §cLa liste des §4§lLoups Garous §r§cest : ");
		}
		
		//Parano
		else if(player.getRole() == Role.PARANO) {
			p.sendMessage("§4[LG] §9Vous êtes §6§lParano :"); 
			p.sendMessage("§9Votre objectif est de gagner avec le clan du village. Vous devez tuer tous les loups garous et le reste des traîtres. "
					+ "Chaque matin, vous vous réveillerez avec une sensation particulière. Si un ou des loups garous se trouvent dans les 50 blocs "
					+ "autour de vous, votre instinct de parano vous fera crier un (ou plusieurs si plusieurs loups garous) §c§l\"AAAAAARGHHHHHHHHHH\" "
					+ "§9qui sera/seront entendu(s) par tout le monde. §9§lBonne chance !");
		}
		
		//PF
		else if(player.getRole() == Role.PF) {
			p.addPotionEffect(effects.night_vision);
			p.sendMessage("§4[LG] §9Vous êtes §6§lPetite Fille :"); 
			p.sendMessage("§9Votre objectif est de gagner avec le clan du village. Vous devez tuer tous les loups garous et le reste des traîtres. "
					+ "Toute les nuits, vous bénéficiez de l'effet Invisibilité I pour vous aider à espionner vos ennemis. Si un ou des joueurs se "
					+ "trouvent dans les 100 blocs autour de vous, vous serez au courant desquels. §9§lBonne chance !");
		}
		
		//Renard
		else if(player.getRole() == Role.RENARD) {
			p.addPotionEffect(effects.speed);
			p.sendMessage("§4[LG] §9Vous êtes §6§lRenard :"); 
			p.sendMessage("§9Votre objectif est de gagner avec le clan du village. Vous devez tuer tous les loups garous et le reste des traîtres. "
					+ "Vous bénéficiez de l'effet Speed I en permanance pour vous aider à effectuer votre tâche. De plus, vous pouvez à l'aide de la "
					+ "commande §6/flairer (pseudo) §9savoir si un joueur est dans le clan du village ou dans le clan des loups garous. Il faut pour cela vous "
					+ "placer à maximum 5 blocs de lui. §9§lBonne chance !");					
			
		}
		
		//Salvateur
		else if(player.getRole() == Role.SALVA) {
			p.sendMessage("§4[LG] §9Vous êtes §6§lSalvateur :"); 
			p.sendMessage("§9Votre objectif est de gagner avec le clan du village. Vous devez tuer tous les loups garous et le reste des traîtres. "
					+ "Tous les jours, vous pouvez protéger quelqu'un en lui donner l'effet résistance I à l'aide de la commande §6/proteger (pseudo). "
					+ "§9De plus, vous pouvez récupérer vos trois potion d'instant health en effectuant la commande §6/claim §9pour vous aider à effectuer "
					+ "votre tâche. §9§lBonne chance !");
			
		}
		
		//Soeur
		else if(player.getRole() == Role.SOEUR) {
			p.sendMessage("Vous êtes soeur, rôle pas encore codé");
		}
		
		//Sorcière
		else if(player.getRole() == Role.SORCIERE) {
			p.sendMessage("§4[LG] §9Vous êtes §6§lSorcière :"); 
			p.sendMessage("§9Votre objectif est de gagner avec le clan du village. Vous devez tuer tous les loups garous et le reste des traîtres. "
					+ "Lors de la mort d'un joueur, vous pouvez décider de le sauver et de la ramener à la vie à l'aide de la commande §6/sauver (pseudo). "
					+ "§9ATTENTION : Vous ne pouvez le faire qu'une seule fois ! De plus, vous pouvez récupérer vos trois potions d'instant health, votre "
					+ "potion de régénération et vos trois potions d'instant damage en effectuant la commande §6/claim §9pour vous aider à effectuer votre "
					+ "tâche. §9§lBonne chance !");
				 
		}
		
		//SV
		else if(player.getRole() == Role.SV || (player.getRole() == Role.LG_AMNESIQUE && !player.isTransformed())) {
			p.sendMessage("§4[LG] §9Vous êtes §6§lSimple Villageois :");
			p.sendMessage("§9Votre objectif est de gagner avec le clan du village. Vous devez tuer tous les loups garous et le reste des traîtres. "
					+ "Vous n'avez aucun effet suplémentaire... Sorry :( §9§lBonne chance !");
				
		}
		
		//Voyante
		else if(player.getRole() == Role.VOYANTE) {
			p.sendMessage("§4[LG] §9Vous êtes §6§lVoyante :"); 
			p.sendMessage("§9Votre objectif est de gagner avec le clan du village. Vous devez tuer tous les loups garous et le reste des traîtres. "
					+ "Vous pouvez, à l'aide de votre troisième oeil, tous les matins, observer rôle d'un autre villageois grâce à la "
					+ "commande §6/voir (pseudo). §9De plus vous pouvez récupérer vos 4 obsidiennes et vos 5 bibliothèques en effectuant la "
					+ "commande §6/claim. §9§lBonne chance !");
			
		}
		
		//Voleur
		else if(player.getRole() == Role.VOLEUR) {
			p.sendMessage("Vous êtes voleur, rôle pas encore codé !");
		}
		
		//VPL
		else if(player.getRole() == Role.VPL) {
			player.setLoup(true);
			player.setMechant(true);
			p.sendMessage("§4[LG] §9Vous êtes §6§lVilain Petit Loup :");
			p.sendMessage("§9Votre objectif est de gagner avec le clan des loups. Vous devez tuer tout le clan du village et le reste des traîtres. "
					+ "Vous bénéficiez des effets Force I et Speed I la nuit pour vous aider à effectuer votre tâche. Vous pouvez parler entre loups grâce à la "
					+ "commande §6/lgt (message). §9§lBonne chance !");
			p.sendMessage("§4[LG] §cLa liste des §4§lLoups Garous §r§cest : ");
		}
		
		else if(player.getRole() == Role.NONE) {
			p.sendMessage("§4[LG] §cVous n'avez pas encore de rôle !");
		}
		
		if(player.isInfect()) {
			p.sendMessage("§4[LG] §9Vous êtes cependant §6§lInfecté :"); 
			p.sendMessage("§9Votre objectif est donc réellement de gagner avec le clan des loups. Vous devez tuer tout le clan du village et le reste des traîtres. "
					+ "Vous bénéficiez de l'effet Force I la nuit pour vous aider à effectuer votre tâche. Vous pouvez parler entre loups grâce à la "
					+ "commande §6/lgt (message). §9§lBonne chance !");
			p.sendMessage("§4[LG] §cLa liste des §4§lLoups Garous §r§cest : ");
		}
		
		if(!first && player.isLoup())
			ListeLg(player.getPlayer());
		
		if(player.isCouple()) {
			p.sendMessage("§4[LG] §9Vous êtes en §d§lcouple §r§9avec §6" + player.getCouple().getName() + "§9 !");
			p.sendMessage("§9Votre objectif est de gagner avec votre amoureux, peu importe votre rôle, ainsi "
					+ "qu'avec votre Cupidon dont vous ne connaissez pas l'idendité ! Attention, si votre amoureux meurt, vous vous "
					+ "suiciderez par amour ! §9§lBonne chance !");
		}
	}
	
	//**********************************************************************
	

	public void ListeLg(org.bukkit.entity.Player player) {
		String liste = "§c ";
		for(Player p: Ingame) {
			if(p.isLoup())
				liste += p.getName() + "   ";
		}
		player.sendMessage(liste);
	}
	
	//**********************************************************************
	
	public void newLoup(Player victim) {
		for(Player player: Ingame) {
			if(player.isLoup())
				player.getPlayer().sendMessage("§4[LG] §6" + victim.getName() + "§9 a rejoint le camp des loups !");
		}
	}
	
	//**********************************************************************
	
	public void verifWin() {
		
		//**********************************************************************
		// Win rôles solo
		if(Ingame.size() == 1) {
			if(Ingame.get(0).getRole() == Role.ASSASSIN && !Ingame.get(0).isInfect() && !Ingame.get(0).isCouple()) {
				Bukkit.broadcastMessage("§9§m----------------------------------------------");
				Bukkit.broadcastMessage("§9 La partie est terminée !");
				Bukkit.broadcastMessage("§9 Bravo à §6" + Ingame.get(0).getName() + "§9 pour sa victoire en tant qu'§6Assassin §9!");
				Bukkit.broadcastMessage("§9§m----------------------------------------------");
				setState(State.END);
			}
			else if(Ingame.get(0).getRole() == Role.LGB && !Ingame.get(0).isInfect() && !Ingame.get(0).isCouple()) {
				Bukkit.broadcastMessage("§9§m----------------------------------------------");
				Bukkit.broadcastMessage("§9 La partie est terminée !");
				Bukkit.broadcastMessage("§9 Bravo à §6" + Ingame.get(0).getName() + "§9 pour sa victoire en tant que §4Loup Garou Blanc §9!");
				Bukkit.broadcastMessage("§9§m----------------------------------------------");
				setState(State.END);
			}
		}
		
		//**********************************************************************
		// Win couple
		if(Ingame.size() == 2) {
			if(Ingame.get(0).isCouple() && Ingame.get(1).isCouple()) {
				Bukkit.broadcastMessage("§9§m----------------------------------------------");
				Bukkit.broadcastMessage("§9 La partie est terminée !");
				Bukkit.broadcastMessage("§9 Bravo à §6" + Ingame.get(0).getName() + "§9 et §6" + Ingame.get(1).getName() + "§9 pour leur victoire en §dCouple §9!");
				Bukkit.broadcastMessage("§9§m----------------------------------------------");
				setState(State.END);
			}
		}
		if(Ingame.size() == 3) {
			int count_couple = 0;
			boolean cupi = false;
			for(Player player: Ingame) {
				if(player.isCouple())
					count_couple++;
				if(player.getRole() == Role.CUPIDON)
					cupi = true;
			}
			if(count_couple == 2 && cupi) {
				Bukkit.broadcastMessage("§9§m----------------------------------------------");
				Bukkit.broadcastMessage("§9 La partie est terminée !");
				Bukkit.broadcastMessage("§9 Bravo à §6" + Ingame.get(0).getName() + "§9 et §6" + Ingame.get(1).getName() + "§9 et §6" + Ingame.get(2).getName() + "§9 pour leur victoire en §dCouple §9 avec le §6Cupidon !");
				Bukkit.broadcastMessage("§9§m----------------------------------------------");
				setState(State.END);
			}
		}
		
		if(Ingame.size() != 0) {
			//**********************************************************************
			// Win village
			boolean loup_assassin_couple = false;
			for(Player player: Ingame) {
				if(player.isLoup() || player.getRole() == Role.ASSASSIN || player.isCouple())
					loup_assassin_couple = true;
			}
			if(!loup_assassin_couple) {
				Bukkit.broadcastMessage("§9§m----------------------------------------------");
				Bukkit.broadcastMessage("§9 La partie est terminée !");
				Bukkit.broadcastMessage("§9 Bravo au §aVillage §9qui remporte cette partie !");
				Bukkit.broadcastMessage("§9§m----------------------------------------------");
				setState(State.END);
			}
			//**********************************************************************
			// Win loups 
			boolean loup = true;
			for(Player player: Ingame) {
				if(!player.isLoup() || player.getRole() == Role.LGB || player.isCouple())
					loup = false;
			}
			if(loup) {
				Bukkit.broadcastMessage("§9§m----------------------------------------------");
				Bukkit.broadcastMessage("§9 La partie est terminée !");
				Bukkit.broadcastMessage("§9 Bravo au §cClan des loups §9qui remporte cette partie !");
				Bukkit.broadcastMessage("§9§m----------------------------------------------");
				setState(State.END);
			}
		}else {
			//**********************************************************************
			// Egalité
			Bukkit.broadcastMessage("§9§m----------------------------------------------");
			Bukkit.broadcastMessage("§9 La partie est terminée !");
			Bukkit.broadcastMessage("§9 Tout le monde est mort ! C'est une §6Egalité §9!");
			Bukkit.broadcastMessage("§9§m----------------------------------------------");
		}
	}
	//**********************************************************************
	
	public void respawn(Player player) {
		org.bukkit.entity.Player p = player.getPlayer();
		int size = (int) border.getSize()/2-50;
		int y = 200;
		while(p.getWorld().getBlockAt(size, y, size).getType() == Material.AIR)
			y--;
		p.teleport(new Location(p.getWorld(), size, y+5, size));
		p.setGameMode(GameMode.SURVIVAL);
		if(player.getRole() == Role.LGB)
			p.setHealth(30);
		else {
			if(player.getRole() == Role.ANCIEN)
				p.addPotionEffect(effects.resistance);
			p.setHealth(20);
		}
	}
	
	//**********************************************************************
	
	public void updateScoreBoard() {
		for(Entry<org.bukkit.entity.Player, Scoreboard> s: Main.getInstance().scoreboard.entrySet()) {
			Scoreboard sb = s.getValue();
			sb.refresh();
		}
	}	
	//**********************************************************************
	
}
