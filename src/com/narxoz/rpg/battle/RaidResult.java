package com.narxoz.rpg.battle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RaidResult {
    private String winner;
    private int rounds;
    private final List<String> log = new ArrayList<>();

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void addLog(String message) {
        log.add(message);
    }

    public String getWinner() {
        return winner;
    }

    public int getRounds() {
        return rounds;
    }

    public List<String> getLog() {
        return Collections.unmodifiableList(log);
    }
}