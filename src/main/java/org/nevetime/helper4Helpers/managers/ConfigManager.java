package org.nevetime.helper4Helpers.managers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.YamlConfiguration;
import org.nevetime.helper4Helpers.Helper4Helpers;

import java.io.File;

public class ConfigManager {
    private final MiniMessage mm = MiniMessage.miniMessage();

    private final YamlConfiguration config;

    public ConfigManager(Helper4Helpers plugin) {
        config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "config.yml"));
    }

    public String getPrefix() {
        return config.getString("prefix", "<gradient:#18A48D:#299431>вопро</gradient><gradient:#299431:#299431>сы</gradient> <gray>|</gray>");
    }

    public Component getMessage(String key) {
        return applyMiniMessage(getPrefix() + " " + config.getString("messages." + key));
    }

    public Component getMessage(String key, boolean insertPrefix) {
        if (insertPrefix) return applyMiniMessage(getPrefix() + " " + config.getString("messages." + key));
        else return getMessage(key);
    }

    public Component getMessage(String key, String def) {
        return applyMiniMessage(config.getString("messages." + key, def));
    }

    public Component getMessage(String key, boolean insertPrefix, String def) {
        if (insertPrefix) return applyMiniMessage(getPrefix() + " " + config.getString("messages." + key, def));
        else return applyMiniMessage(config.getString("messages." + key, def));
    }

    private Component applyMiniMessage(String message) {
        return mm.deserialize(message);
    }
}
