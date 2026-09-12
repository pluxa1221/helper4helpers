package org.nevetime.helper4Helpers.managers;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.nevetime.helper4Helpers.Helper4Helpers;
import org.nevetime.helper4Helpers.model.Question;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class QuestionManager {
    private MiniMessage miniMessage = MiniMessage.miniMessage();

    private Helper4Helpers plugin = Helper4Helpers.getInstance();
    private ConfigManager config = plugin.getConfigManager();

    private HashMap<UUID, Question> incomingQuestions = new HashMap<>();

    private int questionId = 1;

    public QuestionManager() {}

    public UUID getUuidByIntId(int intId) {
        for (HashMap.Entry<UUID, Question> entry : incomingQuestions.entrySet()) {
            if (entry.getValue().getId() == intId) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Set<UUID> getIncomingQuestionIds() {
        return incomingQuestions.keySet();
    }

    public Question getQuestion(UUID questionId) {
        return incomingQuestions.get(questionId);
    }

    public UUID ask(Player askedPlayer, String question) {
        UUID uuid = UUID.randomUUID();

        Question quest = new Question(
                questionId,
                askedPlayer,
                question
        );

        questionId++;

        incomingQuestions.put(uuid, quest);

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

        if (!incomingQuestions.get(question).getAskedPlayer().isOnline()) {
            player.sendMessage(config.getMessage("player.offline"));
            return;
        }

        player.sendMessage(config.getMessage("answer.delivered"));
        incomingQuestions.get(question).getAskedPlayer().sendMessage(miniMessage.deserialize(config.getPrefix() + " " + config.getRawMessage("answer.received") + answer));

        incomingQuestions.remove(question);
    }
}
