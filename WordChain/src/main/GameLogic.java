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
        		"apple", "banana", "orange", "grape", "kiwi", "peach", "pear", "strawberry", "blueberry", "mango", "melon", "pineapple", "watermelon", "apricot", "cherry", "plum", "nectarine", "papaya", "fig", "coconut", "date", "raspberry", "blackberry", "lemon", "lime", "tangerine", "pomegranate", "kiwifruit", "cantaloupe", "passionfruit", "guava", "dragonfruit", "dog", "cat", "elephant", "giraffe", "lion", "tiger", "bear", "wolf", "rabbit", "fox", "zebra", "horse", "cow", "sheep", "goat", "chicken", "duck", "eagle", "sparrow", "fish", "shark", "whale", "dolphin", "squirrel", "ant", "bee", "butterfly", "tree", "flower", "grass", "mountain", "river", "ocean", "lake", "sky", "star", "sun", "moon", "cloud", "rain", "snow", "wind", "storm", "thunder", "lightning", "earth", "fire", "water", "air", "rock", "sand", "dirt", "metal", "wood", "glass", "paper", "plastic", "computer", "phone", "table", "chair", "sofa", "bed", "car", "bike", "train", "plane", "bus", "boat", "shoe", "hat", "shirt", "pants", "dress", "jacket", "watch", "ring", "necklace", "earrings", "book", "pen", "pencil", "notebook", "school", "teacher", "student", "home", "family", "friend", "love", "happiness", "sadness", "anger", "peace", "war", "dream", "life", "death", "hope", "faith", "courage", "freedom", "truth", "justice", "wisdom", "knowledge", "power", "music", "art", "dance", "film", "theater", "game", "play", "sport", "food", "drink", "cuisine", "recipe", "meal", "snack", "breakfast", "lunch", "dinner", "dessert", "candy", "chocolate", "sugar", "salt", "pepper", "spice", "herb", "vegetable", "fruit", "grain", "seed", "nut", "library", "bookstore", "grocery", "market", "shopping", "store", "restaurant", "café", "kitchen", "bathroom", "living room", "bedroom", "garden", "backyard", "garage", "roof", "wall", "floor", "window", "door", "fence", "gate", "path", "street", "road", "highway", "bridge", "tunnel", "park", "zoo", "museum", "exhibit", "concert", "festival", "party", "celebration", "holiday", "vacation", "journey", "trip", "travel", "adventure", "exploration", "discovery", "experience", "memory", "moment", "event", "situation", "challenge", "problem", "solution", "question", "answer", "discussion", "debate", "conversation", "dialogue", "communication", "connection", "relationship", "partnership", "team", "group", "community", "society", "culture", "tradition", "custom", "belief", "value", "principle", "idea", "concept", "theory", "hypothesis", "experiment", "research", "study", "analysis", "result", "finding", "conclusion", "interpretation", "understanding", "wisdom", "insight", "knowledge", "information", "data", "fact", "truth", "fiction", "story", "tale", "narrative", "legend", "myth", "fable", "folktale", "poem", "song", "lyrics", "melody", "rhythm", "beat", "sound", "noise", "silence", "quiet", "calm", "chaos", "conflict", "struggle", "battle", "fight", "victory", "defeat", "champion", "hero", "heroine", "villain", "character", "protagonist", "antagonist", "setting", "scene", "location", "environment", "nature", "landscape", "view", "sight", "appearance", "shape", "form", "color", "shade", "light", "dark", "brightness", "shadow", "reflection", "mirror", "image", "artwork", "painting", "drawing", "sculpture", "design", "craft", "skill", "talent", "creativity", "imagination", "inspiration", "innovation", "thought", "plan", "strategy", "goal", "objective", "target", "mission", "purpose", "intention", "desire", "ambition", "aspiration", "wish", "wishful thinking", "health", "wellness", "fitness", "exercise", "yoga", "meditation", "nutrition", "diet", "vitamin", "mineral", "supplement", "doctor", "nurse", "patient", "treatment", "medicine", "hospital", "clinic", "pharmacy", "symptom", "disease", "illness", "sickness", "cure", "remedy", "recovery", "therapist", "psychologist", "counselor", "support", "therapy", "mindfulness", "self-care", "joy", "sorrow", "grief", "fear", "anxiety", "stress", "calmness", "tranquility", "patience", "kindness", "generosity", "gratitude", "forgiveness", "empathy", "sympathy", "compassion", "friendship", "loyalty", "trust", "faithfulness", "honesty", "integrity", "respect", "dignity", "humility", "pride", "confidence", "bravery", "risk", "opportunity", "success", "failure", "mistake", "learning", "growth", "development", "improvement", "progress", "achievement", "goal-setting", "motivation", "determination", "persistence", "perseverance", "endurance", "strength", "ability", "capability", "skillfulness", "expertise", "craftsmanship", "profession", "career", "job", "work", "employment", "business", "industry", "economy", "finance", "investment", "profit", "loss", "expense", "budget", "savings", "debt", "loan", "credit", "insurance", "tax", "income", "wealth", "riches", "poverty", "charity", "donation", "philanthropy", "fundraising", "volunteering", "service", "help", "assistance", "guidance", "coaching", "mentoring", "training", "education", "competence", "opinion", "ethics", "morality", "fairness", "equality", "liberty", "rights", "responsibility", "citizenship", "government", "law", "regulation", "policy", "politics", "election", "campaign", "vote", "democracy", "republic", "monarchy", "dictatorship", "revolution", "protest", "movement", "change", "technology", "science", "discovery", "invention", "statistic", "evidence", "definition", "description", "explanation", "report", "publication", "literature", "non-fiction", "poetry", "drama", "series", "episode", "season", "plot", "theme", "message", "moral", "lesson", "value"
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
