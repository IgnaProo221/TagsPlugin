package ignaproo.tagsplugin.plugin.Data;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldedit.world.World;
import jdk.dynalink.Operation;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

public class Schematic {

    private final Clipboard clipboard;

    public Schematic(Clipboard clipboard) {
        this.clipboard = clipboard;
    }

    public Clipboard getClipboard() {
        return clipboard;
    }

    public void paste(org.bukkit.Location target) {
        org.bukkit.World bukkitWorld = Bukkit.getWorld("world");
        World world = BukkitAdapter.adapt(bukkitWorld);
        Location pasteLocation = BukkitAdapter.adapt(target);

        EditSession session = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1);

        ForwardExtentCopy operation = (ForwardExtentCopy) new ClipboardHolder(clipboard)
                .createPaste(session)
                .to(pasteLocation.toVector().toBlockPoint())
                .ignoreAirBlocks(true)
                .build();

        try {
            Operations.complete(operation);

            session.flushSession();
        } catch (WorldEditException exception) {
            exception.printStackTrace();
        }
    }

    public static Optional<Schematic> load(File file) {
        ClipboardFormat format = ClipboardFormats.findByFile(file);
        if (format == null) {
            return Optional.empty();
        }

        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            return Optional.of(new Schematic(reader.read()));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return Optional.empty();
    }
}
