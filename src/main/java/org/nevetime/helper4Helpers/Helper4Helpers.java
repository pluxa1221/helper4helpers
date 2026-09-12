package org.nevetime.helper4Helpers;

import org.bukkit.plugin.java.JavaPlugin;
import org.nevetime.helper4Helpers.commands.AnswerCommand;
import org.nevetime.helper4Helpers.commands.AskCommand;
import org.nevetime.helper4Helpers.managers.ConfigManager;
import org.nevetime.helper4Helpers.managers.QuestionManager;

public final class Helper4Helpers extends JavaPlugin {

    private static Helper4Helpers instance;

    private ConfigManager configManager;
    private QuestionManager questionManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        saveConfig();

        configManager = new ConfigManager(this);
        questionManager = new QuestionManager();

        getCommand("ask").setExecutor(new AskCommand());

        getCommand("answer").setExecutor(new AnswerCommand());
        getCommand("answer").setTabCompleter(new AnswerCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ConfigManager getConfigManager() { return configManager; }
    public QuestionManager getQuestionManager() { return questionManager; }

    public static Helper4Helpers getInstance() {
        return instance;
    }
}
