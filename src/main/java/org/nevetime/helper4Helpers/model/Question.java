package org.nevetime.helper4Helpers.model;

import org.bukkit.entity.Player;

public class Question {
    private final int id;
    private final Player askedPlayer;
    private final String text;

    public Question(int id, Player askedPlayer, String text) {
        this.id = id;
        this.askedPlayer = askedPlayer;
        this.text = text;
    }

    public int getId() { return id; }
    public Player getAskedPlayer() { return askedPlayer; }
    public String getText() { return text; }
}
