package it.unicam.cs.mpgc.rpg119163.view;

import it.unicam.cs.mpgc.rpg119163.model.Game;
import it.unicam.cs.mpgc.rpg119163.persistence.GameRepository;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private Game game;
    private final GameRepository gameRepository;

    private final JLabel playerHealthLabel;
    private final JLabel enemyHealthLabel;

    private final JProgressBar playerHealthBar;
    private final JProgressBar enemyHealthBar;

    private final JButton attackButton;
    private final JButton saveButton;
    private final JButton loadButton;

    private final JLabel messageLabel;

    public MainWindow(final Game game) {

        this.playerHealthLabel = new JLabel();
        this.enemyHealthLabel = new JLabel();
        this.messageLabel = new JLabel("Welcome to SimpleRPG!");
        this.playerHealthBar = new JProgressBar();
        this.enemyHealthBar = new JProgressBar();
        this.game = game;
        this.gameRepository = new GameRepository();

        this.playerHealthBar.setMinimum(0);
        this.enemyHealthBar.setMinimum(0);

        this.playerHealthBar.setStringPainted(true);
        this.enemyHealthBar.setStringPainted(true);

        setTitle("SimpleRPG");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.attackButton = new JButton("Attack");
        this.saveButton = new JButton("Save");
        this.loadButton = new JButton("Load");

        JPanel topPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        centerPanel.setLayout(new GridLayout(2, 2));
        bottomPanel.setLayout(new FlowLayout());

        topPanel.add(this.messageLabel);

        centerPanel.add(this.playerHealthLabel);
        centerPanel.add(this.playerHealthBar);
        centerPanel.add(this.enemyHealthLabel);
        centerPanel.add(this.enemyHealthBar);

        bottomPanel.add(this.attackButton);
        bottomPanel.add(this.saveButton);
        bottomPanel.add(this.loadButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        this.updateLabels();

        this.attackButton.addActionListener(
                e -> {
                    this.game.nextTurn();
                    this.updateLabels();

                    if (!this.game.getCurrentEnemy().isAlive()) {
                        this.messageLabel.setText(
                                this.game.getCurrentEnemy().getName()
                                        + " defeated!"
                        );
                        this.attackButton.setEnabled(false);
                    }

                    else if (!this.game.getPlayer().isAlive()) {
                        this.messageLabel.setText(
                            this.game.getPlayer().getName()
                                        + " defeated!"
                    );
                        this.attackButton.setEnabled(false);
                    }

                    else {
                        this.messageLabel.setText("Turn completed.");
                    }
                }
        );

        this.saveButton.addActionListener(
                e -> {
                    try {
                        this.gameRepository.saveGame(this.game, "savegame.json");
                        this.messageLabel.setText("Game saved successfully.");
                    } catch (IllegalStateException exception) {
                        this.messageLabel.setText("Unable to save the game.");
                    }
                }
        );

        this.loadButton.addActionListener(
                e -> {
                    try {
                        this.game = this.gameRepository.loadGame("savegame.json");
                        this.updateLabels();
                        this.messageLabel.setText("Game loaded successfully.");
                        this.attackButton.setEnabled(
                                this.game.getPlayer().isAlive()
                                        && this.game.getCurrentEnemy().isAlive()
                        );
                    }catch (IllegalStateException exception) {
                        this.messageLabel.setText("Unable to load the game.");
                    }
                }
        );

        setVisible(true);
    }

    private void updateLabels() {
        this.playerHealthLabel.setText(
                this.game.getPlayer().getName()
                + " HP: "
                        + this.game.getPlayer().getCurrentHealth()
        );

        this.enemyHealthLabel.setText(
                this.game.getCurrentEnemy().getName()
                + " HP: "
                        + this.game.getCurrentEnemy().getCurrentHealth()
        );

        this.playerHealthBar.setMaximum(
                (int) this.game.getPlayer().getMaxHealth()
        );

        this.playerHealthBar.setValue(
                (int) this.game.getPlayer().getCurrentHealth()
        );

        this.enemyHealthBar.setMaximum(
                (int) this.game.getCurrentEnemy().getMaxHealth()
        );

        this.enemyHealthBar.setValue(
                (int) this.game.getCurrentEnemy().getCurrentHealth()
        );

        this.playerHealthBar.setString(
                (int) this.game.getPlayer().getCurrentHealth()
                + "/"
                + (int) this.game.getPlayer().getMaxHealth()
        );

        this.enemyHealthBar.setString(
                (int) this.game.getCurrentEnemy().getCurrentHealth()
                + "/"
                + (int) this.game.getCurrentEnemy().getMaxHealth()
        );
    }
}
