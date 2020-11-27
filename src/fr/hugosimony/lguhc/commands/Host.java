package fr.hugosimony.lguhc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.hugosimony.lguhc.Main;
import fr.hugosimony.lguhc.State;
import fr.hugosimony.lguhc.items.ItemsInv;

public class Host implements CommandExecutor {

	// Constructeur
	private Main main;
	private ItemsInv items_inv;
	public Host(Main main) {
		this.main = main;
		items_inv = new ItemsInv(main);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		// Vérifie que la commande ne vient pas de la console.
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.isOp()) {
				
				//**********************************************************************
				
				// /host
				if(cmd.getName().equalsIgnoreCase("host")) {
					if(main.isState(State.CONFIG)) {
						if(player.getInventory().contains(items_inv.config))
							player.sendMessage("§c[Erreur] Vous avez déjà reçu votre item de configuration !");
						else{
							player.getInventory().setItem(4, items_inv.config);
							player.sendMessage("§a[UHC] Vous avez reçu votre item de configuration !");
						}
					}else
						player.sendMessage("§c[Erreur] La partie a démarré !");
				}
				
				//**********************************************************************
				
				// /say
				else if(cmd.getName().equalsIgnoreCase("say")) {
					if(args.length == 0)
						player.sendMessage("§c[Erreur] La commande est §6/say (message) §9!");
					else {
						String message = "§6[Host] " + player.getName() + " : §9";
						for(String s: args)
							message+=s+" ";
						Bukkit.broadcastMessage("§9§m----------------------------------------------");
						Bukkit.broadcastMessage(message);
						Bukkit.broadcastMessage("§9§m----------------------------------------------");
					}
				}
				
				//**********************************************************************
				
			}else
				player.sendMessage("§c[Erreur] Vous n'avez pas les droits suffisants pour cette commande !");
		}
		return true; //Default
	}

}
