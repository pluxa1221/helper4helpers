/*
 * Helper4Helpers — Ticket management system for Minecraft.
 * Copyright (C) 2026 Nevetime
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License...
 .*/

package org.nevetime.helper4Helpers.commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.nevetime.helper4Helpers.Helper4Helpers;
import org.nevetime.helper4Helpers.managers.ConfigManager;
import org.nevetime.helper4Helpers.managers.QuestionManager;
import org.nevetime.helper4Helpers.model.Question;

import java.util.*;

public class AnswerCommand implements CommandExecutor, TabCompleter {

    private final Helper4Helpers plugin = Helper4Helpers.getInstance();
    private final QuestionManager questionManager = plugin.getQuestionManager();
    private final ConfigManager config = plugin.getConfigManager();
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(config.getMessage("command.only_players"));
            return true;
        }

        Player helper = (Player) sender;

        if (!helper.hasPermission("helper4helpers.answer")) {
            helper.sendMessage(config.getMessage("player.no_permission"));
            return true;
        }

        Set<UUID> activeUuids = questionManager.getIncomingQuestionIds();

        if (args.length == 0) {
            if (activeUuids.isEmpty()) {
                helper.sendMessage(config.getMessage("question.list_empty"));
                return true;
            }

            helper.sendMessage(miniMessage.deserialize("<gold><bold>=== Активные Вопросы ===</bold></gold>"));

            for (UUID uuid : activeUuids) {
                Question question = questionManager.getQuestion(uuid);

                int intId = question.getId();

                Player askedPlayer = question.getAskedPlayer();
                String pName = (askedPlayer != null) ? askedPlayer.getName() : "Offline";

                String questionText = question.getText();

                String listRow = String.format("<click:suggest_command:\"/answer %d \"><gold>%d.</gold> <white>%s</white></click>",
                        intId, intId, questionText);

                helper.sendMessage(miniMessage.deserialize(listRow));
            }
            return true;
        }

        if (args.length < 2) {
            helper.sendMessage(config.getMessage("answer.usage"));
            return true;
        }

        int targetIntId;
        try {
            targetIntId = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            helper.sendMessage(config.getMessage("question.invalid_id", "Invalid ID"));
            return true;
        }

        UUID realQuestionUuid = questionManager.getUuidByIntId(targetIntId);
        if (realQuestionUuid == null) {
            helper.sendMessage(config.getMessage("question.not_found"));
            return true;
        }

        String answer = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        questionManager.answer(helper, realQuestionUuid, answer);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            String currentInput = args[0];
            for (UUID uuid : questionManager.getIncomingQuestionIds()) {
                String idStr = String.valueOf(questionManager.getQuestion(uuid).getId());
                if (idStr.startsWith(currentInput)) {
                    completions.add(idStr);
                }
            }
            return completions;
        }

        return new ArrayList<>();
    }
}
