package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameLogic {
    private List<Player> players; // Changed to List for indexed access
    private Set<String> validWords;
    private String lastWord;
    private int currentPlayerIndex;
    private int totalPlayers;

    public GameLogic(int totalPlayers) {
        this.totalPlayers = totalPlayers;
        players = new ArrayList<>(); // Initialize players as ArrayList
        validWords = loadDictionary(); // Load predefined words
        for (int i = 0; i < totalPlayers; i++) {
            players.add(new Player("Player " + (i + 1)));
        }
        currentPlayerIndex = 0;
    }

    private Set<String> loadDictionary() {
        Set<String> words = new HashSet<>();
        // Add a hardcoded list of valid words
        String[] predefinedWords = {
            "apple", "banana", "cherry", "date", "elderberry", 
            "fig", "grape", "honeydew", "kiwi", "lemon",
            "mango", "nectarine", "orange", "papaya", "quince",
            "raspberry", "strawberry", "tangerine", "ugli", "vanilla",
            "watermelon"
        };
        for (String word : predefinedWords) {
            words.add(word);
        }
        return words;
    }

    public String playWord(String word) {
        word = word.toLowerCase();
        Player currentPlayer = getCurrentPlayer();
        if (isValidWord(currentPlayer, word)) {
            lastWord = word;
            return currentPlayer.getName() + " played: " + word;
        } else {
            return currentPlayer.getName() + " loses! Invalid word.";
        }
    }

    private boolean isValidWord(Player player, String word) {
        if (word.isEmpty() || !validWords.contains(word) || !player.playWord(word)) {
            return false;
        }
        if (lastWord != null && word.charAt(0) != lastWord.charAt(lastWord.length() - 1)) {
            return false;
        }
        return true;
    }

    private Player getCurrentPlayer() {
        return players.get(currentPlayerIndex); // Accessing player by index
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % totalPlayers;
    }

    public void resetGame() {
        for (Player player : players) {
            player.resetWords(); // Ensure Player class has resetWords() method
        }
        lastWord = null;
        currentPlayerIndex = 0;
    }
}
