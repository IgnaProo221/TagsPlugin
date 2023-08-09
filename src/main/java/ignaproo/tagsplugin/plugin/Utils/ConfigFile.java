package ignaproo.tagsplugin.plugin.Utils;

import ignaproo.tagsplugin.plugin.Data.CreateConfig;
import ignaproo.tagsplugin.plugin.main;

public class ConfigFile {
    private static final CreateConfig config = new CreateConfig(main.getInstance(), "config.yml");

    public static void setConfigValue(String id, String value) {
        config.setConfig(id, value);
    }

    public static void reloadConfig() {
        config.reloadConfig();
    }

    public static String getConfigValue(String id, String defaultValue) {
        if (config.getConfig().getString(id) == null) {
            config.setConfig(id, defaultValue);
        }
        return config.getConfig().getString(id);
    }
}
