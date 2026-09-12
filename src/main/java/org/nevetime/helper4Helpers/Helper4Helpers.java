package org.nevetime.helper4Helpers;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.nevetime.helper4Helpers.managers.ConfigManager;

public final class Helper4Helpers extends JavaPlugin {

    private static Helper4Helpers instance;

    private ConfigManager configManager;

    private LuckPerms luckperms;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveConfig();

        configManager = new ConfigManager(this);

        instance = this;

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckperms = provider.getProvider();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public LuckPerms getLuckPerms() {
        return luckperms;
    }

    public static Helper4Helpers getInstance() {
        return instance;
    }
}
