package org.nevetime.helper4Helpers;

import org.bukkit.plugin.java.JavaPlugin;

public final class Helper4Helpers extends JavaPlugin {

    public Helper4Helpers instance = this;

    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Helper4Helpers getInstance() {
        return instance;
    }
}
