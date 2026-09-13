/*
 * Helper4Helpers — Ticket management system for Minecraft.
 * Copyright (C) 2026 Nevetime
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License...
 */

package org.nevetime.helper4Helpers.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.nevetime.helper4Helpers.Helper4Helpers;
import org.nevetime.helper4Helpers.managers.ConfigManager;
import org.nevetime.helper4Helpers.managers.QuestionManager;

public class AskCommand implements CommandExecutor {

    private final Helper4Helpers plugin = Helper4Helpers.getInstance();

    private final QuestionManager questionManager = plugin.getQuestionManager();
    private final ConfigManager config = plugin.getConfigManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(config.getMessage("command.only_players"));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("helper4helpers.ask")) {
            player.sendMessage(config.getMessage("player.no_permission"));
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(config.getMessage("question.usage"));
            return true;
        }

        String question = String.join(" ", args);
        questionManager.ask(player, question);

        player.sendMessage(config.getMessage("question.delivered"));

        return true;
    }
}
