
package it.unicam.cs.mpgc.rpg119163.view;

import it.unicam.cs.mpgc.rpg119163.model.Game;
import it.unicam.cs.mpgc.rpg119163.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg119163.model.characters.Player;
import it.unicam.cs.mpgc.rpg119163.model.items.HealingPotion;
import it.unicam.cs.mpgc.rpg119163.model.items.Weapon;
import it.unicam.cs.mpgc.rpg119163.persistence.GameRepository;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class StartWindow extends JFrame {

    private final GameRepository gameRepository;
    private final JButton newGameButton;
    private final JButton loadGameButton;
    private final JButton exitButton;
    private final JLabel titleLabel;

    public StartWindow() {
        this.gameRepository = new GameRepository();
        this.titleLabel = new JLabel("SimpleRPG!");
        this.newGameButton = new JButton("New Game");
        this.loadGameButton = new JButton("Load Game");
        this.exitButton = new JButton("Exit");

        this.configureWindow();
        this.configureComponents();
        this.buildLayout();
        this.configureActions();

        setVisible(true);
    }

    private void configureWindow() {
        setTitle("SimpleRPG");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void configureComponents() {
        this.titleLabel.setFont(
                new Font("Arial", Font.BOLD, 32)
        );
        this.titleLabel.setHorizontalAlignment(JLabel.CENTER);

        final Font buttonFont = new Font("Arial", Font.BOLD, 16);
        this.newGameButton.setFont(buttonFont);
        this.loadGameButton.setFont(buttonFont);
        this.exitButton.setFont(buttonFont);
    }

    private void buildLayout() {
        final JPanel buttonPanel = new JPanel(
                new GridLayout(3, 1, 10, 10)
        );
        buttonPanel.add(this.newGameButton);
        buttonPanel.add(this.loadGameButton);
        buttonPanel.add(this.exitButton);

        final JPanel centerPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );
        centerPanel.add(buttonPanel);

        add(this.titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void configureActions() {
        this.newGameButton.addActionListener(
                e -> this.startNewGame()
        );

        this.loadGameButton.addActionListener(
                e -> this.loadGame()
        );

        this.exitButton.addActionListener(
                e -> System.exit(0)
        );
    }

    private void startNewGame() {
        final Player player = new Player("Hero", 100, 20, 5);
        player.addItem(new HealingPotion("Potion", "Heals 50 HP", 10, 50));
        player.addItem(new Weapon("Iron Sword", "A basic iron sword", 50, 10));

        final Enemy enemy = new Enemy("Goblin", 50, 10, 3, 25, 8);
        final Game game = new Game(player, enemy);

        new MainWindow(game);
        this.dispose();
    }

    private void loadGame() {
        try {
            final Game game = this.gameRepository.loadGame("savegame.json");
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
    }
}
