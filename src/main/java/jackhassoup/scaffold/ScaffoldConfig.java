package jackhassoup.scaffold;

import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

public class ScaffoldConfig {
    public static final Toml properties = new Toml("Scaffold TOML Config");
    public static TomlConfigHandler cfg;

    public static int blockIDs = 7000;
    public static int itemIDs = 24000;

    public static int allPlatformSpan = 4;

    public static int bambooScaffoldSpan = 5;
    public static int paperScaffoldSpan = 4;
    public static int woodScaffoldSpan = 6;
    public static int cobbleScaffoldSpan = 8;
    public static int slateScaffoldSpan = 12;
    public static int ironScaffoldSpan = 16;
    public static int steelScaffoldSpan = 24;

    static {
        properties.addCategory("scaffold").addEntry("cfgVersion", 1);

        properties.addCategory("Block IDs");
        properties.addEntry("Block IDs.startingID", blockIDs);

        properties.addCategory("Item IDs");
        properties.addEntry("Item IDs.startingID", itemIDs);

        properties.addCategory("Scaffold lengths");
        properties.addEntry("Scaffold lengths.bamboo", bambooScaffoldSpan);
        properties.addEntry("Scaffold lengths.paper", paperScaffoldSpan);
        properties.addEntry("Scaffold lengths.wood", woodScaffoldSpan);
        properties.addEntry("Scaffold lengths.cobble", cobbleScaffoldSpan);
        properties.addEntry("Scaffold lengths.slate", slateScaffoldSpan);
        properties.addEntry("Scaffold lengths.iron", ironScaffoldSpan);
        properties.addEntry("Scaffold lengths.steel", steelScaffoldSpan);
        properties.addEntry("Scaffold lengths.all_platforms", allPlatformSpan);

        cfg = new TomlConfigHandler(ScaffoldMod.MOD_ID, properties);
    }
}
