package main;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private String name;
    private Set<String> playedWords;

    public Player(String name) {
        this.name = name;
        this.playedWords = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public boolean playWord(String word) {
        if (!playedWords.contains(word)) {
            playedWords.add(word);
            return true;
        }
        return false;
    }

    public void resetWords() {
        playedWords.clear();
    }
}
