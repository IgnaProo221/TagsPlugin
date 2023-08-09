package ignaproo.tagsplugin.plugin.Voids;

import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.internal.annotation.Selection;
import com.sk89q.worldedit.world.DataException;
import ignaproo.tagsplugin.plugin.Utils.Util;
import ignaproo.tagsplugin.plugin.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class Methods {
    public static void createCapsule() {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.dispatchCommand(console, "fill -8 225 -8 8 225 8 glass");
        Bukkit.dispatchCommand(console, "fill 8 226 -8 -8 229 -8 glass");
        Bukkit.dispatchCommand(console, "fill -8 229 -8 -8 226 8 glass");
        Bukkit.dispatchCommand(console, "fill -8 226 8 8 229 8 glass");
        Bukkit.dispatchCommand(console, "fill 8 229 8 8 226 -8 glass");

    }
}
