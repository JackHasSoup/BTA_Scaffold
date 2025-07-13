package jackhassoup.scaffold;

import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ScaffoldConfig {
    public static final Toml properties = new Toml("Scaffold TOML Config");
    public static TomlConfigHandler cfg;

    public static int blockIDs = 7000;
    public static int itemIDs = 24000;

    static {
        properties.addCategory("scaffold").addEntry("cfgVersion", 1);

        properties.addCategory("Block IDs");
        properties.addEntry("Block IDs.startingID", blockIDs);

        properties.addCategory("Item IDs");
        properties.addEntry("Item IDs.startingID", itemIDs);

        cfg = new TomlConfigHandler(ScaffoldMod.MOD_ID, properties);
    }
}
