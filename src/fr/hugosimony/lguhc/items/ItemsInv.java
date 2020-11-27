package fr.hugosimony.lguhc.items;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import fr.hugosimony.lguhc.Main;
import fr.hugosimony.lguhc.State;

public class ItemsInv {
	
	// Constructeur
	Main main;
	public ItemsInv(Main main) {
		this.main = main;
		InitializeItems();
	}
	
	// Global
	public ItemStack config = new ItemStack(Material.NETHER_STAR);
	public ItemStack contours = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
	public ItemStack contours_role = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
	public ItemStack scenarios = new ItemStack(Material.BOOK);
	public ItemStack uhc = new ItemStack(Material.GOLDEN_APPLE);
	public ItemStack roles = new ItemStack(Material.BOOK_AND_QUILL);
	public ItemStack valider = new ItemStack(Material.SLIME_BALL);
	public ItemStack plus = new ItemStack(Material.EMERALD_BLOCK);
	public ItemStack moins = new ItemStack(Material.REDSTONE_BLOCK);
	public ItemStack plus1 = new ItemStack(Material.EMERALD);
	public ItemStack moins1 = new ItemStack(Material.REDSTONE);
	public ItemStack plus5 = new ItemStack(Material.EMERALD_ORE);
	public ItemStack moins5 = new ItemStack(Material.REDSTONE_ORE);
	public ItemStack plus10 = new ItemStack(Material.EMERALD_BLOCK);
	public ItemStack moins10 = new ItemStack(Material.REDSTONE_BLOCK);
	public ItemStack moins10s = new ItemStack(Material.REDSTONE);
	public ItemStack plus10s = new ItemStack(Material.EMERALD);
	public ItemStack moins50s = new ItemStack(Material.REDSTONE_ORE);
	public ItemStack plus50s = new ItemStack(Material.EMERALD_ORE);
	public ItemStack moins100s = new ItemStack(Material.REDSTONE_BLOCK);
	public ItemStack plus100s = new ItemStack(Material.EMERALD_BLOCK);
	
	// Démarrer/Stopper
	public ItemStack start = new ItemStack(Material.STAINED_GLASS, 1, (short) 5);
	public ItemStack starting = new ItemStack(Material.STAINED_GLASS, 1, (short) 1);
	
	// Scénarios
	public ItemStack noFood = new ItemStack(Material.COOKED_BEEF);
	public ItemStack noFoodOff = new ItemStack(Material.COOKED_BEEF);
	public ItemStack noFall = new ItemStack(Material.FEATHER);
	public ItemStack noFallOff = new ItemStack(Material.FEATHER);
	public ItemStack noFire = new ItemStack(Material.BLAZE_POWDER);
	public ItemStack noFireOff = new ItemStack(Material.BLAZE_POWDER);
	public ItemStack diamondLimit = new ItemStack(Material.DIAMOND);
	public ItemStack noDiamondLimit = new ItemStack(Material.DIAMOND);
	public ItemStack diamond = new ItemStack(Material.DIAMOND);
	
	// UHC
	public ItemStack pvp = new ItemStack(Material.IRON_SWORD);
	public ItemStack border = new ItemStack(Material.BARRIER);
	public ItemStack borderSize = new ItemStack(Material.EMPTY_MAP);
	public ItemStack border_speed = new ItemStack(Material.SUGAR);
	public ItemStack max_player = new ItemStack(Material.SKULL_ITEM);
	public ItemStack day_night = new ItemStack(Material.WATCH);
	public ItemStack role_time = new ItemStack(Material.BOOK_AND_QUILL);
	
	// Rôles
	public ItemStack ancien = new ItemStack(Material.LOG);
	public ItemStack assassin = new ItemStack(Material.DIAMOND_SWORD);
	public ItemStack chasseur = new ItemStack(Material.BOW);
	public ItemStack cupidon = new ItemStack(Material.ARROW);
	public ItemStack detective = new ItemStack(Material.BOOK_AND_QUILL);
	public ItemStack enfant_sauvage = new ItemStack(Material.SAPLING, 1, (short) 3);
	public ItemStack ipdl = new ItemStack(Material.LEASH);
	public ItemStack lg = new ItemStack(Material.BONE);
	public ItemStack lg_amne = new ItemStack(Material.PAINTING);
	public ItemStack lg_ano = new ItemStack(Material.WHEAT);
	public ItemStack lgb = new ItemStack(Material.FIREBALL);
	public ItemStack parano = new ItemStack(Material.PUMPKIN);
	public ItemStack pf = new ItemStack(Material.TNT);
	public ItemStack renard = new ItemStack(Material.COOKED_RABBIT);
	public ItemStack salva = new ItemStack(Material.SPECKLED_MELON);
	public ItemStack soeur = new ItemStack(Material.DIODE);
	public ItemStack sorciere = new ItemStack(Material.BREWING_STAND_ITEM);
	public ItemStack sv = new ItemStack(Material.BREAD);
	public ItemStack voleur = new ItemStack(Material.NAME_TAG);
	public ItemStack voyante = new ItemStack(Material.EYE_OF_ENDER);
	public ItemStack vpl = new ItemStack(Material.INK_SACK);
	
	// Claim
	public ItemStack power3 = new ItemStack(Material.ENCHANTED_BOOK);
	public ItemStack protect3 = new ItemStack(Material.ENCHANTED_BOOK);
	public ItemStack sharp3 = new ItemStack(Material.ENCHANTED_BOOK);
	public ItemStack bow = new ItemStack(Material.BOW);
	public ItemStack power2_punch1 = new ItemStack(Material.ENCHANTED_BOOK);
	public ItemStack arrows = new ItemStack(Material.ARROW, 32);
	public ItemStack health_potion = new ItemStack(Material.POTION, 3, (short)16453);
	public ItemStack regen_potion = new ItemStack(Material.POTION, 1, (short)16385);
	public ItemStack damage_potion = new ItemStack(Material.POTION, 3, (short)16460);	
	public ItemStack biblio = new ItemStack(Material.BOOKSHELF, 5);
	public ItemStack obsi = new ItemStack(Material.OBSIDIAN, 4);
	
	//**********************************************************************
	
	public void InitializeItems() {
		
		//Item de config
		ItemMeta configM = config.getItemMeta();
		configM.setDisplayName("§6§oConfiguration");
		config.setItemMeta(configM);
		
		//Vitre de contours d'inventaire
		ItemMeta contoursM = contours.getItemMeta();
		contoursM.setDisplayName("§e§k42");
		contours.setItemMeta(contoursM);
		ItemMeta contours_roleM = contours_role.getItemMeta();
		contours_roleM.setDisplayName("§e§k24");
		contours_role.setItemMeta(contours_roleM);
		
		//Scénarios
		ItemMeta scenariosM = scenarios.getItemMeta();
		scenariosM.setDisplayName("§9Scénarios");
		scenarios.setItemMeta(scenariosM);
				
		//UHC
		ItemMeta uhcM = uhc.getItemMeta();
		uhcM.setDisplayName("§2UHC");
		uhc.setItemMeta(uhcM);
		
		//Rôles
		ItemMeta rolesM = roles.getItemMeta();
		rolesM.setDisplayName("§4Rôles");
		roles.setItemMeta(rolesM);
		
		//Vitre de start
		ItemMeta startM = start.getItemMeta();
		startM.setDisplayName("§aDémarrer");
		startM.addEnchant(Enchantment.DURABILITY, 1, true);
		startM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		start.setItemMeta(startM);
		//Vitre de stop
		ItemMeta startingM = starting.getItemMeta();
		startingM.setDisplayName("§6Démarrage en cours");
		startingM.addEnchant(Enchantment.DURABILITY, 1, true);
		startingM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		starting.setItemMeta(startingM);
		
		// Valider
		ItemMeta validerM = valider.getItemMeta();
		validerM.setDisplayName("§2Valider");
		validerM.addEnchant(Enchantment.DURABILITY, 1, true);
		validerM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		valider.setItemMeta(validerM);
		
		// Plus
		ItemMeta plusM = plus.getItemMeta();
		plusM.setDisplayName("§a+");
		plus.setItemMeta(plusM);
		// Moins
		ItemMeta moinsM = moins.getItemMeta();
		moinsM.setDisplayName("§c-");
		moins.setItemMeta(moinsM);
		// Plus
		ItemMeta plus1M = plus1.getItemMeta();
		plus1M.setDisplayName("§a+1");
		plus1.setItemMeta(plus1M);
		// Moins
		ItemMeta moins1M = moins1.getItemMeta();
		moins1M.setDisplayName("§c-1");
		moins1.setItemMeta(moins1M);
		// Plus
		ItemMeta plus5M = plus5.getItemMeta();
		plus5M.setDisplayName("§a+5");
		plus5.setItemMeta(plus5M);
		// Moins
		ItemMeta moins5M = moins5.getItemMeta();
		moins5M.setDisplayName("§c-5");
		moins5.setItemMeta(moins5M);
		// Plus
		ItemMeta plus10M = plus10.getItemMeta();
		plus10M.setDisplayName("§a+10");
		plus10.setItemMeta(plus10M);
		// Moins
		ItemMeta moins10M = moins10.getItemMeta();
		moins10M.setDisplayName("§c-10");
		moins10.setItemMeta(moins10M);
		// Plus
		ItemMeta plus10sM = plus10s.getItemMeta();
		plus10sM.setDisplayName("§a+10");
		plus10s.setItemMeta(plus10sM);
		// Moins
		ItemMeta moins10sM = moins10s.getItemMeta();
		moins10sM.setDisplayName("§c-10");
		moins10s.setItemMeta(moins10sM);
		// Plus
		ItemMeta plus50sM = plus50s.getItemMeta();
		plus50sM.setDisplayName("§a+50");
		plus50s.setItemMeta(plus50sM);
		// Moins
		ItemMeta moins50sM = moins50s.getItemMeta();
		moins50sM.setDisplayName("§c-50");
		moins50s.setItemMeta(moins50sM);
		// Plus
		ItemMeta plus100sM = plus100s.getItemMeta();
		plus100sM.setDisplayName("§a+100");
		plus100s.setItemMeta(plus100sM);
		// Moins
		ItemMeta moins100sM = moins100s.getItemMeta();
		moins100sM.setDisplayName("§c-100");
		moins100s.setItemMeta(moins100sM);
		
		//**********************************************************************
		// Scénarios
		
		// NoFood
		ItemMeta noFoodM = noFood.getItemMeta();
		noFoodM.setDisplayName("§9No food");
		noFoodM.setLore(Arrays.asList("§aActivé\n(Les joueurs ne perdront pas de nourriture !)"));
		noFoodM.addEnchant(Enchantment.DURABILITY, 1, true);
		noFoodM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		noFood.setItemMeta(noFoodM);
		// NoFood off
		ItemMeta noFoodOffM = noFoodOff.getItemMeta();
		noFoodOffM.setDisplayName("§9No food");
		noFoodOffM.setLore(Arrays.asList("§cDésactivé"));
		noFoodOff.setItemMeta(noFoodOffM);
		
		// NoFall
		ItemMeta noFallM = noFall.getItemMeta();
		noFallM.setDisplayName("§9No fall");
		noFallM.setLore(Arrays.asList("§aActivé\n(Les joueurs ne prendront pas de dégats de chute !)"));
		noFallM.addEnchant(Enchantment.DURABILITY, 1, true);
		noFallM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		noFall.setItemMeta(noFallM);
		// NoFall off
		ItemMeta noFallOffM = noFallOff.getItemMeta();
		noFallOffM.setDisplayName("§9No fall");
		noFallOffM.setLore(Arrays.asList("§cDésactivé"));
		noFallOff.setItemMeta(noFallOffM);
		
		// NoFire
		ItemMeta noFireM = noFire.getItemMeta();
		noFireM.setDisplayName("§9No fire");
		noFireM.setLore(Arrays.asList("§aActivé\n(Les joueurs ne prendront pas de dégats de feu !)"));
		noFireM.addEnchant(Enchantment.DURABILITY, 1, true);
		noFireM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		noFire.setItemMeta(noFireM);
		// NoFire off
		ItemMeta noFireOffM = noFireOff.getItemMeta();
		noFireOffM.setDisplayName("§9No fire");
		noFireOffM.setLore(Arrays.asList("§cDésactivé"));
		noFireOff.setItemMeta(noFireOffM);
		
		// DiamondLimit
		ItemMeta diamondLimitM = diamondLimit.getItemMeta();
		diamondLimitM.setDisplayName("§9Diamond Limit");
		diamondLimitM.setLore(Arrays.asList("§aActivé\n(Les joueurs ne pourront miner que " + main.diamondLimitMax + " diamants !"));
		diamondLimitM.addEnchant(Enchantment.DURABILITY, 1, true);
		diamondLimitM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		diamondLimit.setItemMeta(diamondLimitM);
		// NoDiamondLimit
		ItemMeta noDiamondLimitM = noDiamondLimit.getItemMeta();
		noDiamondLimitM.setDisplayName("§9Diamond Limit");
		noDiamondLimitM.setLore(Arrays.asList("§cDésactivé"));
		noDiamondLimit.setItemMeta(noDiamondLimitM);
		// Diamond
		ItemMeta diamondM = diamond.getItemMeta();
		diamondM.setDisplayName("§9Diamond Limit");
		diamondM.setLore(Arrays.asList("§aActivé : " + main.diamondLimitMax));
		diamond.setItemMeta(diamondM);
		
		//**********************************************************************
		// UHC
		// PVP
		ItemMeta pvpM = pvp.getItemMeta();
		pvpM.setDisplayName("§4PVP");
		pvpM.setLore(Arrays.asList("§9Activé à " + main.pvp_time + " minute(s)"));
		pvp.setItemMeta(pvpM);
		// Bordure
		ItemMeta borderM = border.getItemMeta();
		borderM.setDisplayName("§8Bordure");
		borderM.setLore(Arrays.asList("§9Activée à " + main.border_time + " minute(s)"));
		border.setItemMeta(borderM);
		// Taille de la bordure
		ItemMeta borderSizeM = borderSize.getItemMeta();
		borderSizeM.setDisplayName("§9Taille de la bordure");
		borderSizeM.setLore(Arrays.asList("§9Actuellement : " + main.border_size + "/" + main.border_size));
		borderSize.setItemMeta(borderSizeM);
		// Vitesse de la bordure
		ItemMeta border_speedM = border_speed.getItemMeta();
		border_speedM.setDisplayName("§9Vitesse de la bordure");
		border_speedM.setLore(Arrays.asList("§9" + main.border_speed + " bloc(s) par seconde"));
		border_speed.setItemMeta(border_speedM);
		// Nombre de joueur max
		ItemMeta max_playerM = max_player.getItemMeta();
		max_playerM.setDisplayName("§eNombre de Joueurs Maximum");
		max_playerM.setLore(Arrays.asList("§9Actuellement " + main.maxPlayer + " joueur(s) maximum"));
		max_player.setItemMeta(max_playerM);
		// Cycle Jour/Nuit
		ItemMeta day_nightM = day_night.getItemMeta();
		day_nightM.setDisplayName("§aCycle Jour/Nuit");
		day_nightM.setLore(Arrays.asList("§9Actuellement : " + main.cycle_jn + " minutes pour un jour ou une nuit"));
		day_night.setItemMeta(day_nightM);
		// Rôles
		ItemMeta role_timeM = role_time.getItemMeta();
		role_timeM.setDisplayName("§4Rôles");
		role_timeM.setLore(Arrays.asList("§9Assignation à " + main.role_time + " minute(s)"));
		role_time.setItemMeta(role_timeM);
		
		//**********************************************************************
		// Rôles
		
		//Ancien
		ItemMeta ancienM = ancien.getItemMeta();
		ancienM.setDisplayName("§9Ancien");
		ancien.setItemMeta(ancienM);
		//Assassin
		ItemMeta assassinM = assassin.getItemMeta();
		assassinM.setDisplayName("§6Assassin");
		assassin.setItemMeta(assassinM);
		//Chasseur
		ItemMeta chasseurM = chasseur.getItemMeta();
		chasseurM.setDisplayName("§9Chasseur");
		chasseur.setItemMeta(chasseurM);
		//Cupidon
		ItemMeta cupidonM = cupidon.getItemMeta();
		cupidonM.setDisplayName("§dCupidon");
		cupidon.setItemMeta(cupidonM);
		//Détective
		ItemMeta detectiveM = detective.getItemMeta();
		detectiveM.setDisplayName("§9Détective");
		detective.setItemMeta(detectiveM);
		//Enfant Sauvage
		ItemMeta enfant_sauvageM = enfant_sauvage.getItemMeta();
		enfant_sauvageM.setDisplayName("§2Enfant Sauvage");
		enfant_sauvage.setItemMeta(enfant_sauvageM);
		//IPDL
		ItemMeta ipdlM = ipdl.getItemMeta();
		ipdlM.setDisplayName("§cIPDL");
		ipdl.setItemMeta(ipdlM);
		//LG
		ItemMeta lgM = lg.getItemMeta();
		lgM.setDisplayName("§cLoup Garou");
		lg.setItemMeta(lgM);
		//LG Amnésique
		ItemMeta lg_amneM = lg_amne.getItemMeta();
		lg_amneM.setDisplayName("§cLoup Garou Amnésique");
		lg_amne.setItemMeta(lg_amneM);
		//LG Anonyme
		ItemMeta lg_anoM = lg_ano.getItemMeta();
		lg_anoM.setDisplayName("§cLoup Garou Anonyme");
		lg_ano.setItemMeta(lg_anoM);
		//LGB
		ItemMeta lgbM = lgb.getItemMeta();
		lgbM.setDisplayName("§4Loup Garou Blanc");
		lgb.setItemMeta(lgbM);
		//Parano
		ItemMeta paranoM = parano.getItemMeta();
		paranoM.setDisplayName("§9Parano");
		parano.setItemMeta(paranoM);
		//PF
		ItemMeta pfM = pf.getItemMeta();
		pfM.setDisplayName("§9Petite Fille");
		pf.setItemMeta(pfM);
		//Renard
		ItemMeta renardM = renard.getItemMeta();
		renardM.setDisplayName("§9Renard");
		renard.setItemMeta(renardM);
		//Salva
		ItemMeta salvaM = salva.getItemMeta();
		salvaM.setDisplayName("§9Salvateur");
		salva.setItemMeta(salvaM);
		//Soeur
		ItemMeta soeurM = soeur.getItemMeta();
		soeurM.setDisplayName("§9Soeur");
		soeur.setItemMeta(soeurM);
		//Sorcière
		ItemMeta sorciereM = sorciere.getItemMeta();
		sorciereM.setDisplayName("§9Sorcière");
		sorciere.setItemMeta(sorciereM);
		//SV
		ItemMeta svM = sv.getItemMeta();
		svM.setDisplayName("§9Simple Villageois");
		sv.setItemMeta(svM);
		//Voleur
		ItemMeta voleurM = voleur.getItemMeta();
		voleurM.setDisplayName("§6Voleur");
		voleur.setItemMeta(voleurM);
		//Voyante
		ItemMeta voyanteM = voyante.getItemMeta();
		voyanteM.setDisplayName("§9Voyante");
		voyante.setItemMeta(voyanteM);
		//VPL
		ItemMeta vplM = vpl.getItemMeta();
		vplM.setDisplayName("§cVilan Petit Loup");
		vpl.setItemMeta(vplM);
		
		//**********************************************************************
		// Claim
		
		//Assassin
		power3 = addBookEnchantment(power3, Enchantment.ARROW_DAMAGE, 3);
		protect3 = addBookEnchantment(protect3, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		sharp3 = addBookEnchantment(sharp3, Enchantment.DAMAGE_ALL, 3);
		//Chasseur/Cupidon
		power2_punch1 = addBookEnchantment(power2_punch1, Enchantment.ARROW_DAMAGE, 2);
		power2_punch1 = addBookEnchantment(power2_punch1, Enchantment.ARROW_KNOCKBACK, 1);
		
	}
	
	//**********************************************************************
	
	public Inventory InvConfig(Player player) {
		int size = 45;
		Inventory inv_config = Bukkit.createInventory(player, size, "§6§oConfiguration");
		//************************************
		inv_config.setItem((size+1)/2-3, scenarios);
		inv_config.setItem((size+1)/2-1, uhc);
		inv_config.setItem((size+1)/2+1, roles);
		//************************************
		//Contours
		for(int i = 0; i < 9; i ++)
			inv_config.setItem(i, contours);
		for(int i = 8; i < size; i+=9)
			inv_config.setItem(i, contours);
		for(int i = 9; i < size; i+=9)
			inv_config.setItem(i, contours);
		for(int i = size-9; i < size; i ++)
			inv_config.setItem(i, contours);
		//************************************
		if(main.isState(State.STARTING))
			inv_config.setItem(size-5, starting);
		else
			inv_config.setItem(size-5, start);
		return inv_config;
	}
	
	//**********************************************************************

	public Inventory InvScenarios(Player player) {
		int size = 45;
		Inventory inv_scenarios = Bukkit.createInventory(player, size, "§9§oScénarios");
		
		if(main.noFood) inv_scenarios.setItem(10, noFood); 				else inv_scenarios.setItem(10, noFoodOff);
		if(main.noFall) inv_scenarios.setItem(11, noFall);		 		else inv_scenarios.setItem(11, noFallOff);
		if(main.noFire) inv_scenarios.setItem(12, noFire); 				else inv_scenarios.setItem(12, noFireOff);
		if(main.diamondLimit) inv_scenarios.setItem(13, diamondLimit); 	else inv_scenarios.setItem(13, noDiamondLimit);
		//************************************
		//Contours
		for(int i = 0; i < 9; i ++)
			inv_scenarios.setItem(i, contours);
		for(int i = 8; i < size; i+=9)
			inv_scenarios.setItem(i, contours);
		for(int i = 9; i < size; i+=9)
			inv_scenarios.setItem(i, contours);
		for(int i = size-9; i < size; i ++)
			inv_scenarios.setItem(i, contours);
		//************************************
		inv_scenarios.setItem(size-5, valider);
		return inv_scenarios;
	}
	
	//**********************************************************************
	
	public Inventory InvDiamondLimit(Player player) {
		int size = 45;
		Inventory inv_diamond_limit = Bukkit.createInventory(player, size, "§9§oDiamond Limit");
		
		inv_diamond_limit.setItem((size+1)/2-3, moins);
		inv_diamond_limit.setItem((size+1)/2-1, diamond);
		inv_diamond_limit.setItem((size+1)/2+1, plus);
		//************************************
		//Contours
		for(int i = 0; i < 9; i ++)
			inv_diamond_limit.setItem(i, contours);
		for(int i = 8; i < size; i+=9)
			inv_diamond_limit.setItem(i, contours);
		for(int i = 9; i < size; i+=9)
			inv_diamond_limit.setItem(i, contours);
		for(int i = size-9; i < size; i ++)
			inv_diamond_limit.setItem(i, contours);
		//************************************
		inv_diamond_limit.setItem(size-5, valider);
		return inv_diamond_limit;
	}
	
	public ItemStack ActualizeDiamondLimit(int i) {
		if(main.diamondLimitMax + i >= 0)
			main.diamondLimitMax += i;
		ItemMeta diamondM = diamond.getItemMeta();
		diamondM.setDisplayName("§9Diamond Limit");
		diamondM.setLore(Arrays.asList("§aActivé : " + main.diamondLimitMax));
		diamond.setItemMeta(diamondM);
		ItemMeta diamondLimitM = diamondLimit.getItemMeta();
		diamondLimitM.setLore(Arrays.asList("§aActivé\n(Les joueurs ne pourront miner que " + main.diamondLimitMax + " diamants !"));
		diamondLimit.setItemMeta(diamondLimitM);
		return diamond;
	}
	
	//**********************************************************************
	
	public Inventory InvUHC(Player player) {
		int size = 45;
		Inventory inv_uhc = Bukkit.createInventory(player, size, "§2§oUHC");
		inv_uhc.setItem((size+1)/2-3, pvp);
		inv_uhc.setItem((size+1)/2-2, border);
		inv_uhc.setItem((size+1)/2-1, max_player);
		inv_uhc.setItem((size+1)/2, day_night);
		inv_uhc.setItem((size+1)/2+1, role_time);
		//************************************
		//Contours
		for(int i = 0; i < 9; i ++)
			inv_uhc.setItem(i, contours);
		for(int i = 8; i < size; i+=9)
			inv_uhc.setItem(i, contours);
		for(int i = 9; i < size; i+=9)
			inv_uhc.setItem(i, contours);
		for(int i = size-9; i < size; i ++)
			inv_uhc.setItem(i, contours);
		//************************************
		inv_uhc.setItem(size-5, valider);
		return inv_uhc;
	}
	
	//**********************************************************************
	
	public Inventory InvPVP(Player player) {
		int size = 45;
		Inventory inv_uhc = Bukkit.createInventory(player, size, "§4§oPVP");
		inv_uhc.setItem((size+1)/2-4, moins10);
		inv_uhc.setItem((size+1)/2-3, moins5);
		inv_uhc.setItem((size+1)/2-2, moins1);
		inv_uhc.setItem((size+1)/2-1, pvp);
		inv_uhc.setItem((size+1)/2, plus1);
		inv_uhc.setItem((size+1)/2+1, plus5);
		inv_uhc.setItem((size+1)/2+2, plus10);
		//************************************
		//Contours
		for(int i = 0; i < 9; i ++)
			inv_uhc.setItem(i, contours);
		for(int i = 8; i < size; i+=9)
			inv_uhc.setItem(i, contours);
		for(int i = 9; i < size; i+=9)
			inv_uhc.setItem(i, contours);
		for(int i = size-9; i < size; i ++)
			inv_uhc.setItem(i, contours);
		//************************************
		inv_uhc.setItem(size-5, valider);
		return inv_uhc;
	}
	
	public ItemStack ActualizePVPTime(int i) {
		if(main.pvp_time + i >= 1)
			main.pvp_time += i;
		main.pvp_time_s = main.pvp_time*60;
		ItemMeta pvpM = pvp.getItemMeta();
		pvpM.setLore(Arrays.asList("§9Activé à " + main.pvp_time + " minute(s)"));
		pvp.setItemMeta(pvpM);
		return pvp;
	}
	
	//**********************************************************************
	
	public Inventory InvBorder(Player player) {
		int size = 45;
		Inventory inv_border = Bukkit.createInventory(player, size, "§8§oBordure");
		inv_border.setItem((size+1)/2-13, moins10);
		inv_border.setItem((size+1)/2-12, moins5);
		inv_border.setItem((size+1)/2-11, moins1);
		inv_border.setItem((size+1)/2-10, border);
		inv_border.setItem((size+1)/2-9, plus1);
		inv_border.setItem((size+1)/2-8, plus5);
		inv_border.setItem((size+1)/2-7, plus10);
		inv_border.setItem((size+1)/2-4, moins100s);
		inv_border.setItem((size+1)/2-3, moins50s);
		inv_border.setItem((size+1)/2-2, moins10s);
		inv_border.setItem((size+1)/2-1, borderSize);
		inv_border.setItem((size+1)/2, plus10s);
		inv_border.setItem((size+1)/2+1, plus50s);
		inv_border.setItem((size+1)/2+2, plus100s);
		inv_border.setItem((size+1)/2+6, moins);
		inv_border.setItem((size+1)/2+8, border_speed);
		inv_border.setItem((size+1)/2+10, plus);
		//************************************
		//Contours
		for(int i = 0; i < 9; i ++)
			inv_border.setItem(i, contours);
		for(int i = 8; i < size; i+=9)
			inv_border.setItem(i, contours);
		for(int i = 9; i < size; i+=9)
			inv_border.setItem(i, contours);
		for(int i = size-9; i < size; i ++)
			inv_border.setItem(i, contours);
		//************************************
		inv_border.setItem(size-5, valider);
		return inv_border;
	}
	
	public ItemStack ActualizeBorderTime(int i) {
		if(main.border_time + i >= 1)
			main.border_time += i;
		main.border_time_s = main.border_time*60;
		ItemMeta borderM = border.getItemMeta();
		borderM.setLore(Arrays.asList("§9Activée à " + main.border_time + " minute(s)"));
		border.setItemMeta(borderM);
		return border;
	}
	
	public ItemStack ActualizeBorderSize(int i) {
		if(main.border_size + i >= 300)
			main.border_size += i;
		ItemMeta borderSizeM = borderSize.getItemMeta();
		borderSizeM.setLore(Arrays.asList("§9Actuellement : " + main.border_size + "/" + main.border_size));
		borderSize.setItemMeta(borderSizeM);
		return borderSize;
	}
	
	public ItemStack ActualizeBorderSpeed(double i) {
		if(main.border_speed + i >= 0.5)
			main.border_speed += i;
		ItemMeta border_speedM = border_speed.getItemMeta();
		border_speedM.setLore(Arrays.asList("§9" + main.border_speed + " bloc(s) par seconde"));
		border_speed.setItemMeta(border_speedM);
		return border_speed;
	}
	
	//**********************************************************************
	
	public Inventory InvMaxPlayer(Player player) {
		int size = 45;
		Inventory inv_max_player = Bukkit.createInventory(player, size, "§e§oJoueurs Maximum");
		inv_max_player.setItem((size+1)/2-3, moins);
		inv_max_player.setItem((size+1)/2-1, max_player);
		inv_max_player.setItem((size+1)/2+1, plus);
		//************************************
		//Contours
		for(int i = 0; i < 9; i ++)
			inv_max_player.setItem(i, contours);
		for(int i = 8; i < size; i+=9)
			inv_max_player.setItem(i, contours);
		for(int i = 9; i < size; i+=9)
			inv_max_player.setItem(i, contours);
		for(int i = size-9; i < size; i ++)
			inv_max_player.setItem(i, contours);
		//************************************
		inv_max_player.setItem(size-5, valider);
		return inv_max_player;
	}
	
	public ItemStack ActualizeMaxPlayer(int i) {
		if(main.maxPlayer + i >= 5)
			main.maxPlayer += i;
		ItemMeta max_playerM = max_player.getItemMeta();
		max_playerM.setLore(Arrays.asList("§9Actuellement " + main.maxPlayer + " joueur(s) maximum"));
		max_player.setItemMeta(max_playerM);
		return max_player;
	}
	
	//**********************************************************************
	
	public Inventory InvDayNight(Player player) {
		int size = 45;
		Inventory inv_day_night = Bukkit.createInventory(player, size, "§a§oCycle Jour/Nuit");
		inv_day_night.setItem((size+1)/2-3, moins);
		inv_day_night.setItem((size+1)/2-1, day_night);
		inv_day_night.setItem((size+1)/2+1, plus);
		//************************************
		//Contours
		for(int i = 0; i < 9; i ++)
			inv_day_night.setItem(i, contours);
		for(int i = 8; i < size; i+=9)
			inv_day_night.setItem(i, contours);
		for(int i = 9; i < size; i+=9)
			inv_day_night.setItem(i, contours);
		for(int i = size-9; i < size; i ++)
			inv_day_night.setItem(i, contours);
		//************************************
		inv_day_night.setItem(size-5, valider);
		return inv_day_night;
	}
	
	public ItemStack ActualizeDayNight(int i) {
		if(main.cycle_jn + i >= 3)
			main.cycle_jn += i;
		ItemMeta day_nightM = day_night.getItemMeta();
		day_nightM.setLore(Arrays.asList("§9Actuellement : " + main.cycle_jn + " minutes pour un jour ou une nuit"));
		day_night.setItemMeta(day_nightM);
		return day_night;
	}
	
	//**********************************************************************
	
	public Inventory InvRolesTime(Player player) {
		int size = 45;
		Inventory inv_uhc = Bukkit.createInventory(player, size, "§4§oTemps des rôles");
		inv_uhc.setItem((size+1)/2-4, moins10);
		inv_uhc.setItem((size+1)/2-3, moins5);
		inv_uhc.setItem((size+1)/2-2, moins1);
		inv_uhc.setItem((size+1)/2-1, role_time);
		inv_uhc.setItem((size+1)/2, plus1);
		inv_uhc.setItem((size+1)/2+1, plus5);
		inv_uhc.setItem((size+1)/2+2, plus10);
		//************************************
		//Contours
		for(int i = 0; i < 9; i ++)
			inv_uhc.setItem(i, contours);
		for(int i = 8; i < size; i+=9)
			inv_uhc.setItem(i, contours);
		for(int i = 9; i < size; i+=9)
			inv_uhc.setItem(i, contours);
		for(int i = size-9; i < size; i ++)
			inv_uhc.setItem(i, contours);
		//************************************
		inv_uhc.setItem(size-5, valider);
		return inv_uhc;
	}
	
	public ItemStack ActualizeRolesTime(int i) {
		if(main.role_time + i >= 1)
			main.role_time += i;
		main.role_time_s = main.role_time*60;
		ItemMeta role_timeM = role_time.getItemMeta();
		role_timeM.setLore(Arrays.asList("§9Assignation à " + main.role_time + " minute(s)"));
		role_time.setItemMeta(role_timeM);
		return role_time;
	}
	
	//**********************************************************************
	
	public Inventory InvAllRoles(Player player) {
		reloadRoles();
		int size = 45;
		Inventory inv_roles = Bukkit.createInventory(player, size, "§4§oRôles");
		//************************************
		inv_roles.setItem(10, ancien);
		inv_roles.setItem(11, assassin);
		inv_roles.setItem(12, chasseur);
		inv_roles.setItem(13, cupidon);
		inv_roles.setItem(14, detective);
		inv_roles.setItem(15, enfant_sauvage);
		inv_roles.setItem(16, ipdl);
		inv_roles.setItem(19, lg);
		inv_roles.setItem(20, lg_amne);
		inv_roles.setItem(21, lg_ano);
		inv_roles.setItem(22, lgb);
		inv_roles.setItem(23, parano);
		inv_roles.setItem(24, pf);
		inv_roles.setItem(25, renard);
		inv_roles.setItem(28, salva);
		inv_roles.setItem(29, soeur);
		inv_roles.setItem(30, sorciere);
		inv_roles.setItem(31, sv);
		inv_roles.setItem(32, voleur);
		inv_roles.setItem(33, voyante);
		inv_roles.setItem(34, vpl);
		//************************************
		//Contours
		for(int i = 0; i < 9; i ++)
			inv_roles.setItem(i, contours);
		for(int i = 8; i < size; i+=9)
			inv_roles.setItem(i, contours);
		for(int i = 9; i < size; i+=9)
			inv_roles.setItem(i, contours);
		for(int i = size-9; i < size; i ++)
			inv_roles.setItem(i, contours);
		//************************************
		inv_roles.setItem(size-5, valider);
		return inv_roles;
	}
	
	//**********************************************************************
	
	public Inventory InvRole(Player player, ItemStack item) {
		int size = 45;
		Inventory inv_roles = Bukkit.createInventory(player, size, item.getItemMeta().getDisplayName());
		inv_roles.setItem((size+1)/2-3, moins);
		inv_roles.setItem((size+1)/2-1, item);
		inv_roles.setItem((size+1)/2+1, plus);
		//************************************
		//Contours
		for(int i = 0; i < 9; i ++)
			inv_roles.setItem(i, contours);
		for(int i = 8; i < size; i+=9)
			inv_roles.setItem(i, contours);
		for(int i = 9; i < size; i+=9)
			inv_roles.setItem(i, contours);
		for(int i = size-9; i < size; i ++)
			inv_roles.setItem(i, contours);
		//************************************
		inv_roles.setItem(size-5, valider);
		inv_roles.setItem(4, contours_role);
		return inv_roles;
	}
	
	//**********************************************************************
	
	public void reloadRoles() {
		//Ancien
		ItemMeta ancienM = ancien.getItemMeta();
		if(main.ancienC != 0)
			ancienM.setLore(Arrays.asList("§aActivé (" + main.ancienC + ")"));
		else
			ancienM.setLore(Arrays.asList("§cDésactivé"));
		ancien.setItemMeta(ancienM);
		//Assassin
		ItemMeta assassinM = assassin.getItemMeta();
		if(main.assassinC != 0)
			assassinM.setLore(Arrays.asList("§aActivé (" + main.assassinC + ")"));
		else
			assassinM.setLore(Arrays.asList("§cDésactivé"));
		assassin.setItemMeta(assassinM);
		//Chasseur
		ItemMeta chasseurM = chasseur.getItemMeta();
		if(main.chasseurC != 0)
			chasseurM.setLore(Arrays.asList("§aActivé (" + main.chasseurC + ")"));
		else
			chasseurM.setLore(Arrays.asList("§cDésactivé"));
		chasseur.setItemMeta(chasseurM);
		//Cupidon
		ItemMeta cupidonM = cupidon.getItemMeta();
		if(main.cupidonC != 0)
			cupidonM.setLore(Arrays.asList("§aActivé (" + main.cupidonC + ")"));
		else
			cupidonM.setLore(Arrays.asList("§cDésactivé"));
		cupidon.setItemMeta(cupidonM);
		//Détective
		ItemMeta detectiveM = detective.getItemMeta();
		if(main.detectiveC != 0)
			detectiveM.setLore(Arrays.asList("§aActivé (" + main.detectiveC + ")"));
		else
			detectiveM.setLore(Arrays.asList("§cDésactivé"));
		detective.setItemMeta(detectiveM);
		//Enfant Sauvage
		ItemMeta enfant_sauvageM = enfant_sauvage.getItemMeta();
		if(main.enfant_sauvageC != 0)
			enfant_sauvageM.setLore(Arrays.asList("§aActivé (" + main.enfant_sauvageC + ")"));
		else
			enfant_sauvageM.setLore(Arrays.asList("§cDésactivé"));
		enfant_sauvage.setItemMeta(enfant_sauvageM);
		//IPDL
		ItemMeta ipdlM = ipdl.getItemMeta();
		if(main.ipdlC != 0)
			ipdlM.setLore(Arrays.asList("§aActivé (" + main.ipdlC + ")"));
		else
			ipdlM.setLore(Arrays.asList("§cDésactivé"));
		ipdl.setItemMeta(ipdlM);
		//LG
		ItemMeta lgM = lg.getItemMeta();
		if(main.lgC != 0)
			lgM.setLore(Arrays.asList("§aActivé (" + main.lgC + ")"));
		else
			lgM.setLore(Arrays.asList("§cDésactivé"));
		lg.setItemMeta(lgM);
		//LG Amnésique
		ItemMeta lg_amneM = lg_amne.getItemMeta();
		if(main.lg_amneC != 0)
			lg_amneM.setLore(Arrays.asList("§aActivé (" + main.lg_amneC + ")"));
		else
			lg_amneM.setLore(Arrays.asList("§cDésactivé"));
		lg_amne.setItemMeta(lg_amneM);
		//LG Anonyme
		ItemMeta lg_anoM = lg_ano.getItemMeta();
		if(main.lg_anoC != 0)
			lg_anoM.setLore(Arrays.asList("§aActivé (" + main.lg_anoC + ")"));
		else
			lg_anoM.setLore(Arrays.asList("§cDésactivé"));
		lg_ano.setItemMeta(lg_anoM);
		//LGB
		ItemMeta lgbM = lgb.getItemMeta();
		if(main.lgbC != 0)
			lgbM.setLore(Arrays.asList("§aActivé (" + main.lgbC + ")"));
		else
			lgbM.setLore(Arrays.asList("§cDésactivé"));
		lgb.setItemMeta(lgbM);
		//Parano
		ItemMeta paranoM = parano.getItemMeta();
		if(main.paranoC != 0)
			paranoM.setLore(Arrays.asList("§aActivé (" + main.paranoC + ")"));
		else
			paranoM.setLore(Arrays.asList("§cDésactivé"));
		parano.setItemMeta(paranoM);
		//PF
		ItemMeta pfM = pf.getItemMeta();
		if(main.pfC != 0)
			pfM.setLore(Arrays.asList("§aActivé (" + main.pfC + ")"));
		else
			pfM.setLore(Arrays.asList("§cDésactivé"));
		pf.setItemMeta(pfM);
		//Renard
		ItemMeta renardM = renard.getItemMeta();
		if(main.renardC != 0)
			renardM.setLore(Arrays.asList("§aActivé (" + main.renardC + ")"));
		else
			renardM.setLore(Arrays.asList("§cDésactivé"));
		renard.setItemMeta(renardM);
		//Salva
		ItemMeta salvaM = salva.getItemMeta();
		if(main.salvaC != 0)
			salvaM.setLore(Arrays.asList("§aActivé (" + main.salvaC + ")"));
		else
			salvaM.setLore(Arrays.asList("§cDésactivé"));
		salva.setItemMeta(salvaM);
		//Soeur
		ItemMeta soeurM = soeur.getItemMeta();
		if(main.soeurC != 0)
			soeurM.setLore(Arrays.asList("§aActivé (" + main.soeurC + ")"));
		else
			soeurM.setLore(Arrays.asList("§cDésactivé"));
		soeur.setItemMeta(soeurM);
		//Sorcière
		ItemMeta sorciereM = sorciere.getItemMeta();
		if(main.sorciereC != 0)
			sorciereM.setLore(Arrays.asList("§aActivé (" + main.sorciereC + ")"));
		else
			sorciereM.setLore(Arrays.asList("§cDésactivé"));
		sorciere.setItemMeta(sorciereM);
		//SV
		ItemMeta svM = sv.getItemMeta();
		if(main.svC != 0)
			svM.setLore(Arrays.asList("§aActivé (" + main.svC + ")"));
		else
			svM.setLore(Arrays.asList("§cDésactivé"));
		sv.setItemMeta(svM);
		//Voleur
		ItemMeta voleurM = voleur.getItemMeta();
		if(main.voleurC != 0)
			voleurM.setLore(Arrays.asList("§aActivé (" + main.voleurC + ")"));
		else
			voleurM.setLore(Arrays.asList("§cDésactivé"));
		voleur.setItemMeta(voleurM);
		//Voyante
		ItemMeta voyanteM = voyante.getItemMeta();
		if(main.voyanteC != 0)
			voyanteM.setLore(Arrays.asList("§aActivé (" + main.voyanteC + ")"));
		else
			voyanteM.setLore(Arrays.asList("§cDésactivé"));
		voyante.setItemMeta(voyanteM);
		//VPL
		ItemMeta vplM = vpl.getItemMeta();
		if(main.vplC != 0)
			vplM.setLore(Arrays.asList("§aActivé (" + main.vplC + ")"));
		else
			vplM.setLore(Arrays.asList("§cDésactivé"));
		vpl.setItemMeta(vplM);
			
	}
	
	//**********************************************************************
	
	public void clearArmor(Player player) {
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setBoots(null);
	}
	
	//**********************************************************************
	
	public ItemStack addBookEnchantment(ItemStack item, Enchantment enchant, int level){
		
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchant, level, true);
        item.setItemMeta(meta);
        return item;
	}
	
}
