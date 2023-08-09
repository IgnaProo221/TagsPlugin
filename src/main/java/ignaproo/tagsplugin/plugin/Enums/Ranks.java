package ignaproo.tagsplugin.plugin.Enums;

import ignaproo.tagsplugin.plugin.Data.PlayerData;
import ignaproo.tagsplugin.plugin.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class Ranks {
    private static Team getScoreboardRank(Rangos rank){
        try{
            Team newTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(rank.priority + "-" + rank.name());
            newTeam.setPrefix(Util.c(rank.prefix));
            return newTeam;
        } catch (Exception e){
            return Bukkit.getScoreboardManager().getMainScoreboard().getTeam(rank.priority + "-" + rank.name());
        }
    }

    public static void setRank(Player target){
        Team team = getScoreboardRank(getRank(target));
        team.addEntry(target.getName());
    }

    public static String getPrefix(Player target){
        return Util.c(getRank(target).prefix);
    }

    public static Rangos getRank(Player target){
        String rank = PlayerData.getRank(target);
        for(Rangos r : Rangos.values()){
            if(r.name().equalsIgnoreCase(rank)){
                return r;
            }
        }
        return Rangos.MEMBER;
    }

    public static List<String> listaDeRangos(){
        List<String> rangos = new ArrayList<>();
        for(Rangos r : Rangos.values()){
            rangos.add(r.name());
        }
        return rangos;
    }

    public enum Rangos{

        ADMIN(Util.c("\uFA01 "), "01"),
        MOD(Util.c("\uFA02 "), "02"),
        STAFF(Util.c("&8[<GRADIENT:F24024>Staff</GRADIENT:B06A5F>&8] "), "03"),
        MANAGER(Util.c("\uFA04 "), "04"),
        BUILDER(Util.c("\uFA05 "), "05"),
        LEADER(Util.c("&8[<GRADIENT:2C5EC9>H - Vip</GRADIENT:5F729C>&8] "), "06"),
        REDTEAM(Util.c("&8[<GRADIENT:D95F14>S - Vip</GRADIENT:915733>&8] "), "07"),
        BLUETEAM(Util.c("&8[<GRADIENT:D95F14>S - Vip</GRADIENT:915733>&8] "), "08"),
        MEMBER(Util.c("&8[<GRADIENT:D95F14>S - Vip</GRADIENT:915733>&8] "), "09"),
        DEATH(Util.c("&8[<GRADIENT:CCBA1B>Vip</GRADIENT:B3AB69>&8] "), "10");

        private final String prefix;
        private final String priority;

        Rangos(String prefix, String priority){
            this.prefix = prefix;
            this.priority = priority;
        }
    }
}
