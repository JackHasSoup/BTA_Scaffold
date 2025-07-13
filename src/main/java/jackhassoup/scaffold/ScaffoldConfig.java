package jackhassoup.scaffold;

import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

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
