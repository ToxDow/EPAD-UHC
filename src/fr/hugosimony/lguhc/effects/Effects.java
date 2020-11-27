package fr.hugosimony.lguhc.effects;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.hugosimony.lguhc.Main;

public class Effects {

	// Constructeur
	Main main;
	public Effects(Main main) {
		this.main = main;
		InitializeEffect();
	}
	
	//Global
	public PotionEffect speed;
	public PotionEffect resistance;
	public PotionEffect strengh;
	public PotionEffect weakness;
	public PotionEffect invisibility;
	public PotionEffect night_vision;
	public PotionEffect slowness;
	public PotionEffect jump_boost;
	
	private void InitializeEffect() {
		speed = new PotionEffect(PotionEffectType.SPEED, 999999999, 0, true, false);
		resistance = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 0, true, false);
		strengh = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999999, 0, true, false);
		weakness = new PotionEffect(PotionEffectType.WEAKNESS, 999999999, 0, true, false);
		invisibility = new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 0, true, false);
		night_vision = new PotionEffect(PotionEffectType.NIGHT_VISION, 999999999, 0, true, false);
		slowness = new PotionEffect(PotionEffectType.SLOW, 999999999, 255, true, false);
		jump_boost = new PotionEffect(PotionEffectType.JUMP, 999999999, 500, true, false);
	}
	
}
