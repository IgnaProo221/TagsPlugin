package ignaproo.tagsplugin.plugin.Events;

import ignaproo.tagsplugin.plugin.Data.PlayerData;
import ignaproo.tagsplugin.plugin.Enums.Ranks;
import ignaproo.tagsplugin.plugin.Utils.ConfigFile;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JoinLeaveEvent implements Listener {
    private List<Player> playersConnected = new ArrayList<>();
    private List<Player> redTeam = new ArrayList<>();
    private List<Player> blueTeam = new ArrayList<>();
    @EventHandler
    private void onJoin(PlayerJoinEvent e) {
        Location l1 = new Location(Bukkit.getWorld("world"), 0, 226, 0);
        Player p = e.getPlayer();
        
        p.teleport(l1);
        p.setGameMode(GameMode.ADVENTURE);
        PlayerData.setPlayerData(p, "rank", "member");
        Ranks.setRank(p);
        playersConnected.add(p);
        p.setWhitelisted(true);
        Bukkit.setWhitelist(true);
        for (Player pl : Bukkit.getOnlinePlayers()) {
            int pQ = Bukkit.getOnlinePlayers().size();
            if (pQ <= 9) {
                ConfigFile.setConfigValue("startGame", "false");
                p.setWhitelisted(true);
            }else if (playersConnected.size() >= 10) {
                ItemStack redArmorHelmet = new ItemStack(Material.LEATHER_HELMET);
                LeatherArmorMeta redHelmetMeta = (LeatherArmorMeta) redArmorHelmet.getItemMeta();
                redHelmetMeta.setColor(Color.RED);
                redArmorHelmet.setItemMeta(redHelmetMeta);

                ItemStack redArmorChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta redChestplateMeta = (LeatherArmorMeta) redArmorChestplate.getItemMeta();
                redChestplateMeta.setColor(Color.RED);
                redArmorChestplate.setItemMeta(redChestplateMeta);

                ItemStack redArmorLeggings = new ItemStack(Material.LEATHER_LEGGINGS);
                LeatherArmorMeta redLeggingsMeta = (LeatherArmorMeta) redArmorLeggings.getItemMeta();
                redLeggingsMeta.setColor(Color.RED);
                redArmorLeggings.setItemMeta(redLeggingsMeta);

                ItemStack redArmorBoots = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta redBootsMeta = (LeatherArmorMeta) redArmorBoots.getItemMeta();
                redBootsMeta.setColor(Color.RED);
                redArmorBoots.setItemMeta(redBootsMeta);

                ItemStack blueArmorHelmet = new ItemStack(Material.LEATHER_HELMET);
                LeatherArmorMeta blueHelmetMeta = (LeatherArmorMeta) blueArmorHelmet.getItemMeta();
                blueHelmetMeta.setColor(Color.BLUE);
                blueArmorHelmet.setItemMeta(blueHelmetMeta);

                ItemStack blueArmorChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta blueChestplateMeta = (LeatherArmorMeta) blueArmorChestplate.getItemMeta();
                blueChestplateMeta.setColor(Color.BLUE);
                blueArmorChestplate.setItemMeta(blueChestplateMeta);

                ItemStack blueArmorLeggings = new ItemStack(Material.LEATHER_LEGGINGS);
                LeatherArmorMeta blueLeggingsMeta = (LeatherArmorMeta) blueArmorLeggings.getItemMeta();
                blueLeggingsMeta.setColor(Color.BLUE);
                blueArmorLeggings.setItemMeta(blueLeggingsMeta);

                ItemStack blueArmorBoots = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta blueBootsMeta = (LeatherArmorMeta) blueArmorBoots.getItemMeta();
                blueBootsMeta.setColor(Color.BLUE);
                blueArmorBoots.setItemMeta(blueBootsMeta);
                ConfigFile.setConfigValue("startGame", "true");
                divideTeams();
                for (Player player : redTeam) {
                    player.getInventory().setHelmet(redArmorHelmet);
                    player.getInventory().setChestplate(redArmorChestplate);
                    player.getInventory().setLeggings(redArmorLeggings);
                    player.getInventory().setBoots(redArmorBoots);
                }
                for (Player player : blueTeam) {
                    player.getInventory().setHelmet(blueArmorHelmet);
                    player.getInventory().setChestplate(blueArmorChestplate);
                    player.getInventory().setLeggings(blueArmorLeggings);
                    player.getInventory().setBoots(blueArmorBoots);

                }


            }
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        playersConnected.remove(p);
        redTeam.remove(p);
        blueTeam.remove(p);
    }
    private void divideTeams() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Player jugador = playersConnected.remove(random.nextInt(playersConnected.size()));
            if (i % 2 == 0) {
                redTeam.add(jugador);
                PlayerData.setPlayerData(jugador, "rank", "redteam");
                Ranks.setRank(jugador);
            } else {
                blueTeam.add(jugador);
                PlayerData.setPlayerData(jugador, "rank", "blueteam");
                Ranks.setRank(jugador);
            }
        }
        blueTeam.addAll(playersConnected);
    }
}
