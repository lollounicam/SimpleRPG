package it.unicam.cs.mpgc.rpg119163.view;

import it.unicam.cs.mpgc.rpg119163.model.Game;
import it.unicam.cs.mpgc.rpg119163.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg119163.model.characters.Player;
import it.unicam.cs.mpgc.rpg119163.model.items.HealingPotion;
import it.unicam.cs.mpgc.rpg119163.model.items.Weapon;
import it.unicam.cs.mpgc.rpg119163.persistence.GameRepository;

import javax.swing.*;
import java.awt.*;

public class StartWindow extends JFrame {

    private final GameRepository gameRepository;
    private final JButton newGameButton;
    private final JButton loadGameButton;
    private final JButton exitButton;

    public StartWindow() {
        setTitle("SimpleRPG");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JLabel titleLabel = new JLabel("SimpleRPG");

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 32)
        );

        this.gameRepository = new GameRepository();
        this.newGameButton = new JButton("New Game");
        this.loadGameButton = new JButton("Load Game");
        this.exitButton = new JButton("Exit");

        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout((
                new GridLayout(3, 1, 10, 10)
        ));
        buttonPanel.add(newGameButton);
        buttonPanel.add(loadGameButton);
        buttonPanel.add(exitButton);

        JPanel centerPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );

        centerPanel.add(buttonPanel);

        add(centerPanel, BorderLayout.CENTER);

        this.newGameButton.addActionListener(e -> {
            Player player = new Player("Hero", 100, 20, 5);
            player.addItem(new HealingPotion("Potion", "Heals 50 HP", 10, 50));
            player.addItem(new Weapon("Iron Sword", "A basic iron sword", 50, 10));

            Enemy enemy = new Enemy("Goblin", 50, 10, 3, 25, 8);

            Game game = new Game(player, enemy);

            new MainWindow(game);
            this.dispose();
        });

        this.loadGameButton.addActionListener(e -> {
            try {
                Game game = this.gameRepository.loadGame("savegame.json");
                new MainWindow(game);
                this.dispose();
            } catch (IllegalStateException exception) {
                JOptionPane.showMessageDialog(
                        this,
                        "Unable to load the game.",
                        "Load error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        this.exitButton.addActionListener(e -> System.exit(0));





        setVisible(true);


    }
}
