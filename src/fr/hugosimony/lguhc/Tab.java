package fr.hugosimony.lguhc;

import java.lang.reflect.Field;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.hugosimony.lguhc.Main;

public class Tab {
	
	static String head = "  §c§m------------------------§r  \n"
							+ "  §r§4Loup Garou UHC  §r\n";
	static String foot = "\n  §3§oMade by CS_Sauvaj  §r\n"
						+ "  §b§m------------------------§r  ";
	
	// Cette classe est trouvée sur internet, je comprends comment elle marche mais je l'aurais pas faite par moi même (pareil pour la ligne du catch en dessous).
	//****************************************************************************************************************************************************************
	public static Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException {
		return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + nmsClassName);
	}
	//****************************************************************************************************************************************************************
  
	public static void sendTablist(Player player, Main plugin, boolean colors) {
		try {
			Object header = getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{'text': '" + head + "'}" });
			Object footer = getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{'text': '" + foot + "'}" });
			Object packet = getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[] { getNmsClass("IChatBaseComponent") }).newInstance(new Object[] { header });
			Field field = packet.getClass().getDeclaredField("b");
			field.setAccessible(true);
			field.set(packet, footer);
			Object nmsp = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
			Object pcon = nmsp.getClass().getField("playerConnection").get(nmsp);
			pcon.getClass().getMethod("sendPacket", new Class[] { getNmsClass("Packet") }).invoke(pcon, new Object[] { packet });
		}catch (IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|NoSuchMethodException|SecurityException|ClassNotFoundException|InstantiationException|NoSuchFieldException e) {
			e.printStackTrace();
	  	} 
  	}
}