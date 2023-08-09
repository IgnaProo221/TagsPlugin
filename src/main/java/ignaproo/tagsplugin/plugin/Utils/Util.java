package ignaproo.tagsplugin.plugin.Utils;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static String c(String s) {
        return IridiumColorAPI.process(s);
    }

    public static String chatColor(String message) {
        return message.replace("&", "§");
    }


    public static String dateFormat(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String serverHour(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    public static String ticksToHour(String ticks) {
        int tick = Integer.parseInt(ticks);
        int hour = tick / 20 / 60 / 60;
        return hour + "h";
    }

    public static String getPrefix() {
        java.awt.Color c1 = new java.awt.Color(27, 53, 246);
        java.awt.Color c2 = new java.awt.Color(47, 125, 180);
        return ib(c1,c2,"&l[TagsCore] &r");
    }

    public static void sendStringForAllPlayers(String string) {
        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.sendMessage(string);


        }


    }

    public static String getCustomCause(EntityDamageEvent e) {
        String reasonDeath = "Desconocido";
        switch (e.getCause()) {
            case FALL, FALLING_BLOCK -> reasonDeath = "Caída";
            case FIRE, FIRE_TICK -> reasonDeath = "Fuego";
            case LAVA -> reasonDeath = "Lava";
            case DROWNING -> reasonDeath = "Ahogamiento";
            case BLOCK_EXPLOSION -> reasonDeath = "Explosion";
            case VOID -> reasonDeath = "Vacío";
            case LIGHTNING -> reasonDeath = "Rayo";
            case POISON -> reasonDeath = "Veneno";
            case MAGIC -> reasonDeath = "Magia";
            case WITHER -> reasonDeath = "Wither";
            case THORNS -> reasonDeath = "Espinas";
            case DRAGON_BREATH -> reasonDeath = "Aliento de dragón";
            case CONTACT -> reasonDeath = "Contacto";
            case CRAMMING -> reasonDeath = "Entity Cramming";
            case HOT_FLOOR -> reasonDeath = "Piso en llamas";
            case DRYOUT -> reasonDeath = "Secado";
            case STARVATION -> reasonDeath = "Hambre";
            case SUFFOCATION -> reasonDeath = "Asfixia";
            case FLY_INTO_WALL -> reasonDeath = "Colisión";
            case FREEZE -> reasonDeath = "Congelación";
            case ENTITY_ATTACK, ENTITY_SWEEP_ATTACK -> {
                if (e instanceof EntityDamageByEntityEvent) {
                    reasonDeath = "Entidad [" + ((EntityDamageByEntityEvent) e).getDamager().getName() + "]";
                }
            }
            case PROJECTILE -> {
                if (e instanceof EntityDamageByEntityEvent) {
                    Projectile proj = (Projectile) ((EntityDamageByEntityEvent) e).getDamager();
                    if (proj.getShooter() != null) {
                        Entity shooter = (Entity) proj.getShooter();
                        reasonDeath = "Proyectil [" + shooter.getName() + "]";
                    } else {
                        reasonDeath = "Proyectil [" + ((EntityDamageByEntityEvent) e).getDamager().getName() + "]";
                    }
                }
            }
            case ENTITY_EXPLOSION -> {
                if (e instanceof EntityDamageByEntityEvent) {
                    if (((EntityDamageByEntityEvent) e).getDamager() instanceof Fireball ball && ((Fireball) ((EntityDamageByEntityEvent) e).getDamager()).getShooter() != null) {
                        LivingEntity et = (LivingEntity) ball.getShooter();
                        reasonDeath = "Explosión [" + et.getName() + "]";
                    } else {
                        reasonDeath = c("Explosión [" + ((EntityDamageByEntityEvent) e).getDamager().getName() + "]");
                    }
                }
            }
            case SUICIDE -> reasonDeath = "Suicidio";
            default -> reasonDeath = "Desconocido";
        }
        return reasonDeath;
    }

    public static String getLocationString(Location location) {
        String x = (String.valueOf(location.getBlockX()));
        String y = (String.valueOf(location.getBlockY()));
        String z = (String.valueOf(location.getBlockZ()));
        return x + " " + y + " " + z;
    }

    public static String getWorld(Player target) {
        return switch (target.getWorld().getName()) {
            case "world" -> "Overworld";
            case "world_nether" -> "Nether";
            case "world_the_end" -> "The End";
            default -> "Desconocido";
        };
    }
    public static int getRandom(int numMin, int numMax) {
        return (int) Math.floor(Math.random() * (numMax - numMin + 1) + numMin);
    }
    public static String ib(java.awt.Color Colora, java.awt.Color Colorb, String StringToGradient) {
        ChatColor Color2 = ChatColor.of(Colorb);
        ChatColor Color1 = ChatColor.of(Colora);
        int r = Color1.getColor().getRed();
        int g = Color1.getColor().getGreen();
        int b = Color1.getColor().getBlue();
        int r2 = Color2.getColor().getRed();
        int g2 = Color2.getColor().getGreen();
        int b2 = Color2.getColor().getBlue();
        String Hex = String.format("%02X%02X%02X", r, g, b);
        String Hex2 = String.format("%02X%02X%02X", r2, g2, b2);
        String Final = IridiumColorAPI.process("<GRADIENT:" + Hex + ">" + StringToGradient + "</GRADIENT:" + Hex2 + ">");


        return Final;


    }
}
