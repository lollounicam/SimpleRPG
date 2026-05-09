package it.unicam.cs.mpgc.rpg119163.view;

import it.unicam.cs.mpgc.rpg119163.model.Game;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final Game game;

    private final JLabel playerHealthLabel;
    private final JLabel enemyHealthLabel;

    private final JProgressBar playerHealthBar;
    private final JProgressBar enemyHealthBar;

    private final JButton attackButton;

    private final JLabel messageLabel;

    public MainWindow(final Game game) {

        this.playerHealthLabel = new JLabel();
        this.enemyHealthLabel = new JLabel();
        this.messageLabel = new JLabel("Welcome to SimpleRPG!");
        this.playerHealthBar = new JProgressBar();
        this.enemyHealthBar = new JProgressBar();
        this.game = game;

        this.playerHealthBar.setMinimum(0);
        this.enemyHealthBar.setMinimum(0);

        this.playerHealthBar.setStringPainted(true);
        this.enemyHealthBar.setStringPainted(true);

        setTitle("SimpleRPG");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        this.attackButton = new JButton("Attack");

        add(this.playerHealthLabel);
        add(this.enemyHealthLabel);

        add(this.playerHealthBar);
        add(this.enemyHealthBar);

        add(this.attackButton);
        add(this.messageLabel);


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
