package fr.hugosimony.lguhc;

import java.text.SimpleDateFormat;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

public class Scoreboard implements ScoreboardManager {

	public Player player;
	public org.bukkit.scoreboard.Scoreboard scoreboard;
	public Objective objective;
	public String name = "";
	
	
	public Scoreboard(Player player) {
		this.player = player;
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		if(Main.getInstance().scoreboard.containsKey(player)) return;
		
		//*****************************************************************************
		
		this.name = "sb." + new Random().nextInt(77777);
		this.objective = scoreboard.registerNewObjective(name, "dummy");
		objective.setDisplayName("§4§l  Loup Garou UHC    ");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		
		Main.getInstance().scoreboard.put(player, this);
	}

	public void sendLine() {
		objective.getScore("§1§9§m --------------- ").setScore(9);
		objective.getScore("§6 Timer : ").setScore(8);
		objective.getScore("§e  Rôles : ").setScore(7);
		objective.getScore("§e  Pvp : ").setScore(6);
		objective.getScore("§e  Bordure : ").setScore(5);
		objective.getScore("§c  Taille : ").setScore(4);
		objective.getScore("§2§9§m --------------- ").setScore(3);
		objective.getScore("§e Joueurs Ingame ").setScore(2);
		objective.getScore(" §e§m-§r§e> ").setScore(1);
		objective.getScore("§3§9§m --------------- ").setScore(0);
	}
	
	public void set() {
		player.setScoreboard(scoreboard);
	}
	
	public void refresh() {
		//******************************************************************************************
		// Timer Général
		String time = new SimpleDateFormat("mm:ss").format(Main.getInstance().global_timer * 1000);
		int timeri = Main.getInstance().global_timer;
		if(timeri <= 7200 && timeri > 3599) 
			time = "01:" + time;
		else if(timeri <= 10800 && timeri > 7199)
			time = "02:" + time;
		else if(timeri > 10799 && timeri <= 14400 ) 
			time = "03:" + time;
		else if(timeri > 14399 )
			time = "04:" + time;
		//******************************************************************************************
		// Rôles
		String role = new SimpleDateFormat("mm:ss").format(Main.getInstance().role_time_s * 1000);
		int rolesi = Main.getInstance().role_time_s;
		if(rolesi <= 7200 && rolesi > 3599) 
			role = "01:" + role;
		else if(rolesi <= 10800 && rolesi > 7199)
			role = "02:" + role;
		else if(rolesi > 10799 && rolesi <=14400 ) 
			role = "03:" + role;
		else if(rolesi > 14399 )
			role = "04:" + role;
		//******************************************************************************************
		// PVP
		String pvp = new SimpleDateFormat("mm:ss").format(Main.getInstance().pvp_time_s * 1000);
		int pvpi = Main.getInstance().pvp_time*60;
		if(pvpi <= 7200 && pvpi > 3599) 
			pvp = "01:" + pvp;
		else if(pvpi <= 10800 && pvpi > 7199)
			pvp = "02:" + pvp;
		else if(pvpi > 10799 && pvpi <=14400 ) 
			pvp = "03:" + pvp;
		else if(pvpi > 14399 )
			pvp = "04:" + pvp;
		//******************************************************************************************
		// Bordure
		String bordure = new SimpleDateFormat("mm:ss").format(Main.getInstance().border_time_s * 1000);
		int bordurei = Main.getInstance().border_time_s;
		if(bordurei < 7200 && bordurei >= 3599) 
			bordure = "01:" + bordure;
		else if(bordurei <10800 && bordurei >= 7199)
			bordure = "02:" + bordure;
		else if(bordurei >=10800 && bordurei <14400 ) 
			bordure = "03:" + bordure;
		else if(bordurei >= 14400 )
			bordure = "04:" + bordure;
		for(String line: scoreboard.getEntries()) {
			if(line.contains("§6 Timer :")) {
				scoreboard.resetScores(line);
				objective.getScore("§6 Timer : " + time).setScore(8);
			}
			else if(line.contains("§e  Rôles :")) {
				scoreboard.resetScores(line);
				if(Main.getInstance().role_time_s <= 0)
					objective.getScore("§e  Rôles : §aAssignés").setScore(7);
				else
					objective.getScore("§e  Rôles : " + role).setScore(7);
			}
			else if(line.contains("§e  Pvp :")) {
				scoreboard.resetScores(line);
				if(Main.getInstance().pvp_time_s <= 0)
					objective.getScore("§e  Pvp : §aActivé").setScore(6);
				else
					objective.getScore("§e  Pvp : " + pvp).setScore(6);
			}
			else if(line.contains("§e  Bordure :")) {
				scoreboard.resetScores(line);
				if(Main.getInstance().border_time_s <= 0)
					objective.getScore("§e  Bordure : §aActivée").setScore(5);
				else
					objective.getScore("§e  Bordure : " + bordure).setScore(5);
			}
			else if(line.contains("§c  Taille :")) {
				scoreboard.resetScores(line);
				int size = (int) (Main.getInstance().border.getSize())/2;
				if(size==99) size++;
				objective.getScore("§c  Taille : " + size + "/" + size).setScore(4);
			}
			else if(line.contains(" §e§m-§r§e>")) {
				scoreboard.resetScores(line);
				int ingame = Main.getInstance().Ingame.size();
				int max = Main.getInstance().maxPlayer;
				objective.getScore(" §e§m-§r§e> " + ingame + "/" + max).setScore(1);
			}
		}
	}
	
	@Override
	public org.bukkit.scoreboard.Scoreboard getMainScoreboard() {
		return scoreboard;
	}


	@Override
	public org.bukkit.scoreboard.Scoreboard getNewScoreboard() {
		return null;
	}
	
}
