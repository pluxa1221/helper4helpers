package org.nevetime.helper4Helpers.managers;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.nevetime.helper4Helpers.Helper4Helpers;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class QuestionManager {
    private MiniMessage miniMessage = MiniMessage.miniMessage();

    private Helper4Helpers plugin = Helper4Helpers.getInstance();
    private ConfigManager config = plugin.getConfigManager();

    private HashMap<UUID, Player> askedPlayers = new HashMap<>();
    private HashMap<UUID, String> incomingQuestions = new HashMap<>();
    private HashMap<String, String> answeredQuestions = new HashMap<>();

    public QuestionManager() {}

    public Set<UUID> getIncomingQuestionIds() {
        return incomingQuestions.keySet();
    }

    public Player getAskedPlayer(UUID questionId) {
        return askedPlayers.get(questionId);
    }

    public String getQuestionText(UUID questionId) {
        return incomingQuestions.get(questionId);
    }

    public UUID ask(Player askedPlayer, String question) {
        UUID uuid = UUID.randomUUID();

        incomingQuestions.put(uuid, question);
        askedPlayers.put(uuid, askedPlayer);

        for (Player player : plugin.getServer().getOnlinePlayers()) {
            if (player.hasPermission("helper4helpers.answer")) player.sendMessage(config.getMessage("question.new"));
        }

        return uuid;
    }

    public void answer(Player player, UUID question, String answer) {
        if (!incomingQuestions.containsKey(question)) {
            player.sendMessage(config.getMessage("question.not_found"));
            return;
        }

        if (answer.isEmpty()) {
            player.sendMessage(config.getMessage("answer.empty"));
            return;
        }

        if (!askedPlayers.get(question).isOnline()) {
            player.sendMessage(config.getMessage("player.offline"));
            return;
        }

        player.sendMessage(config.getMessage("answer.delivered"));
        askedPlayers.get(question).sendMessage(miniMessage.deserialize(config.getPrefix() + " " + config.getMessage("answer.received") + answer));

        answeredQuestions.put(
                incomingQuestions.get(question),
                answer
        );

        incomingQuestions.remove(question);
        askedPlayers.remove(question);
    }
}
