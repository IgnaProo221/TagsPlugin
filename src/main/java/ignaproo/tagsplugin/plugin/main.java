package ignaproo.tagsplugin.plugin;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.DataException;
import ignaproo.tagsplugin.plugin.Data.Schematic;
import ignaproo.tagsplugin.plugin.Voids.Methods;
import jdk.dynalink.Operation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Vector;

public final class main extends JavaPlugin {
    private static main instance;


    @Override
    public void onEnable() {
        instance = this;
        Methods.createCapsule();
        File schematicFile = new File("plugins/WorldEdit/schematics", "ring.schem");
        Optional<Schematic> optionalSchematic = Schematic.load(schematicFile);

        if (optionalSchematic.isPresent()) {
            Schematic schematic = optionalSchematic.get();

            Location pasteLocation = new Location(getServer().getWorld("world"), -97, 221, 99);
            schematic.paste(pasteLocation);
        } else {
            getLogger().warning("No se pudo cargar la schematic 'ring.schem'.");

        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static main getInstance() {
        return instance;
    }
}
