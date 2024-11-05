package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI {
    private JFrame frame;
    private JTextArea displayArea;
    private JTextField inputField;
    private JButton submitButton;
    private WordChainGame game;

    public GameUI(WordChainGame game) {
        this.game = game;
        setupUI();
    }

    private void setupUI() {
        frame = new JFrame("Word Chain Game");
        displayArea = new JTextArea(10, 30);
        inputField = new JTextField(20);
        submitButton = new JButton("Submit");

        displayArea.setEditable(false);
        frame.setLayout(new FlowLayout());
        frame.add(new JScrollPane(displayArea));
        frame.add(inputField);
        frame.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleInput();
            }
        });

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void handleInput() {
        String inputWord = inputField.getText().trim();
        String message = game.playWord(inputWord);
        displayArea.append(message + "\n");
        inputField.setText("");
    }

    public void displayMessage(String message) {
        displayArea.append(message + "\n");
    }
}
