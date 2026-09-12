package org.nevetime.helper4Helpers;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Helper4Helpers extends JavaPlugin {

    private static Helper4Helpers instance;

    private LuckPerms luckperms;

    @Override
    public void onEnable() {
        // Plugin startup logic
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

    public LuckPerms getLuckPerms() {
        return luckperms;
    }

    public static Helper4Helpers getInstance() {
        return instance;
    }
}
