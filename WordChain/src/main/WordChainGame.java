package main;

public class WordChainGame {
    private GameLogic gameLogic;
    private GameUI gameUI;

    public WordChainGame(int totalPlayers) {
        gameLogic = new GameLogic(totalPlayers);
        gameUI = new GameUI(this);
    }

    public String playWord(String word) {
        String message = gameLogic.playWord(word);
        gameLogic.nextPlayer();
        return message;
    }

    public void resetGame() {
        gameLogic.resetGame();
        gameUI.displayMessage("Game reset. Start again!");
    }

    public static void main(String[] args) {
        new WordChainGame(2); // Change the number of players as needed
    }
}
