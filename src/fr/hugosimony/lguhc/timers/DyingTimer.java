package fr.hugosimony.lguhc.timers;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import fr.hugosimony.lguhc.Main;
import fr.hugosimony.lguhc.effects.Effects;
import fr.hugosimony.lguhc.player.Player;
import fr.hugosimony.lguhc.player.Role;

public class DyingTimer extends BukkitRunnable{

	// Timer
	private int timer = 0;	
	private int ancien_time = 1;
	private int infect_time = 2;
	private int soso_time = 9;
	private int death_time = 16;
	
	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	
	// Constructeur
	Main main;
	Player player;
	Effects effects;
	public DyingTimer(Main main, Player player) {
		this.main = main;
		this.player = player;
		effects = new Effects(main);
	}
	
	@Override
	public void run() {
		
		//**********************************************************************
		
		// Message de pré mort
		if(timer ==  0)
			player.getPlayer().sendMessage("§4[LG] §9Vous êtes mort mais ne partez pas ! Vous pouvez encore vous faire réanimer si vous êtes Ancien ou "
				+ "si l'Infect Père Des Loups ou la Sorcière le décide !");
		
		//**********************************************************************
		
		// Ancien
		if(timer == ancien_time && player.getRole() == Role.ANCIEN && !player.hasAncienRespawn()) {
			player.setAncienRespawn(true);
			player.setDying(false);
			main.respawn(player);
			player.getPlayer().sendMessage("§4[LG] §9Votre expérience vous permet de trouver un passage secret et de ne pas mourrir ! Désormais, "
					+ "vous n'avez plus de seconde chance !");
		}
		
		//**********************************************************************
		
		// Infect
		if(timer == infect_time && player.isDying() && main.findPlayer(player.getKiller()).isLoup()) {
			player.setInfectRespawnable(true);
			for(Player player_: main.Ingame) {
				if(player_.getRole() == Role.IPDL && player_.canInfect())
					player_.getPlayer().sendMessage("§4[LG] §6" + player.getName() + "§9 a été tué par un loup ! Vous pouvez le faire revenir "
							+ "à la vie dans le camp des loups grâce à la commande §6/infect (pseudo) §9! Attention, vous n'avez que 6 secondes "
							+ "pour vous décider !");
			}
		}
		
		//**********************************************************************
		
		// Sorcière
		if(timer == soso_time && player.isDying()) {
			player.setInfectRespawnable(false);
			player.setSosoRespawnable(true);
			for(Player player_: main.Ingame) {
				if(player_.getRole() == Role.SORCIERE && player_.canRevive())
					player_.getPlayer().sendMessage("§4[LG] §6" + player.getName() + "§9 a été tué ! Vous pouvez le faire revenir à la vie "
							+ "grâce à la commande §6/sauver (pseudo) §9! Attention, vous n'avez que 6 secondes pour vous décider !");
			}
		}
		
		//**********************************************************************
		
		// Mort
		if(timer == death_time && player.isDying()) {
			player.setSosoRespawnable(false);
			//Kill le joueur
			player.getPlayer().teleport(player.death_location);
			Bukkit.dispatchCommand(console, "kill " + player.getName());
			player.setDying(false);
		}
		
		//**********************************************************************
		
		if(!player.isDying() || player.isDead()) {
			// Respawn le joueur
			this.cancel();
		}
		
		//**********************************************************************
		if(timer > death_time)
			this.cancel();
		//**********************************************************************
		timer++;
	}
}
