package it.unicam.cs.mpgc.rpg119163.view;

import it.unicam.cs.mpgc.rpg119163.model.Game;
import it.unicam.cs.mpgc.rpg119163.model.items.Equippable;
import it.unicam.cs.mpgc.rpg119163.model.items.Item;
import it.unicam.cs.mpgc.rpg119163.model.items.Usable;
import it.unicam.cs.mpgc.rpg119163.persistence.GameRepository;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class MainWindow extends JFrame {

    private Game game;
    private final GameRepository gameRepository;

    private final JLabel messageLabel;
    private final JLabel playerHealthLabel;
    private final JLabel enemyHealthLabel;
    private final JLabel playerStatsLabel;
    private final JLabel playerSecondaryStatsLabel;

    private final JProgressBar playerHealthBar;
    private final JProgressBar enemyHealthBar;

    private final JList<String> inventoryList;

    private final JButton attackButton;
    private final JButton saveButton;
    private final JButton loadButton;
    private final JButton useItemButton;
    private final JButton equipItemButton;

    public MainWindow(final Game game) {
        this.game = game;
        this.gameRepository = new GameRepository();

        this.messageLabel = new JLabel("Welcome to SimpleRPG!");
        this.playerHealthLabel = new JLabel();
        this.enemyHealthLabel = new JLabel();
        this.playerStatsLabel = new JLabel();
        this.playerSecondaryStatsLabel = new JLabel();

        this.playerHealthBar = new JProgressBar();
        this.enemyHealthBar = new JProgressBar();

        this.inventoryList = new JList<>();

        this.attackButton = new JButton("Attack");
        this.saveButton = new JButton("Save");
        this.loadButton = new JButton("Load");
        this.useItemButton = new JButton("Use Item");
        this.equipItemButton = new JButton("Equip Item");

        this.configureWindow();
        this.configureComponents();
        this.buildLayout();
        this.configureActions();
        this.updateView();

        setVisible(true);
    }

    private void configureWindow() {
        setTitle("SimpleRPG");
        setSize(900, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void configureComponents() {
        final Font messageFont = new Font("Arial", Font.BOLD, 18);
        final Font normalFont = new Font("Arial", Font.PLAIN, 15);
        final Font buttonFont = new Font("Arial", Font.BOLD, 14);

        this.messageLabel.setFont(messageFont);
        this.playerHealthLabel.setFont(normalFont);
        this.enemyHealthLabel.setFont(normalFont);
        this.playerStatsLabel.setFont(normalFont);
        this.playerSecondaryStatsLabel.setFont(normalFont);
        this.inventoryList.setFont(normalFont);

        this.attackButton.setFont(buttonFont);
        this.saveButton.setFont(buttonFont);
        this.loadButton.setFont(buttonFont);
        this.useItemButton.setFont(buttonFont);
        this.equipItemButton.setFont(buttonFont);

        this.playerHealthBar.setMinimum(0);
        this.enemyHealthBar.setMinimum(0);
        this.playerHealthBar.setStringPainted(true);
        this.enemyHealthBar.setStringPainted(true);
    }

    private void buildLayout() {
        final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(this.messageLabel);

        final JPanel playerPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        playerPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Player"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                )
        );
        playerPanel.add(this.playerHealthLabel);
        playerPanel.add(this.playerHealthBar);
        playerPanel.add(this.playerStatsLabel);
        playerPanel.add(this.playerSecondaryStatsLabel);

        final JPanel enemyPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        enemyPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Enemy"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                )
        );
        enemyPanel.add(this.enemyHealthLabel);
        enemyPanel.add(this.enemyHealthBar);

        final JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(playerPanel);
        centerPanel.add(enemyPanel);

        final JScrollPane inventoryScrollPane = new JScrollPane(this.inventoryList);
        inventoryScrollPane.setPreferredSize(new Dimension(170, 0));

        final JPanel inventoryPanel = new JPanel(new BorderLayout());
        inventoryPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Inventory"),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                )
        );
        inventoryPanel.add(inventoryScrollPane, BorderLayout.CENTER);

        final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 8));
        bottomPanel.add(this.attackButton);
        bottomPanel.add(this.useItemButton);
        bottomPanel.add(this.equipItemButton);
        bottomPanel.add(this.saveButton);
        bottomPanel.add(this.loadButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(inventoryPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void configureActions() {
        this.attackButton.addActionListener(
                e -> {
                    this.handleAttack();
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
                        this.refreshGameState();
                        this.messageLabel.setText("Game loaded successfully.");
                    } catch (IllegalStateException exception) {
                        this.messageLabel.setText("Unable to load the game.");
                    }
                }
        );

        this.useItemButton.addActionListener(
                e -> this.useSelectedItem()
        );

        this.equipItemButton.addActionListener(
                e -> this.equipSelectedItem()
        );
    }

    private void updateView() {
        this.updateCharacterLabels();
        this.updateHealthBars();
        this.updateInventoryList();
    }

    private void updateCharacterLabels() {
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

        this.playerStatsLabel.setText(
                "Level: " + this.game.getPlayer().getLevel()
                        + " | XP: " + this.game.getPlayer().getExperience()
                        + " | Gold: " + this.game.getPlayer().getGold()
        );

        this.playerSecondaryStatsLabel.setText(
                "ATK: " + this.game.getPlayer().getAttack()
                        + " | DEF: " + this.game.getPlayer().getDefense()
        );
    }

    private void updateHealthBars() {
        this.playerHealthBar.setMaximum(
                (int) this.game.getPlayer().getMaxHealth()
        );
        this.playerHealthBar.setValue(
                (int) this.game.getPlayer().getCurrentHealth()
        );
        this.playerHealthBar.setString(
                (int) this.game.getPlayer().getCurrentHealth()
                        + " / "
                        + (int) this.game.getPlayer().getMaxHealth()
        );

        this.enemyHealthBar.setMaximum(
                (int) this.game.getCurrentEnemy().getMaxHealth()
        );
        this.enemyHealthBar.setValue(
                (int) this.game.getCurrentEnemy().getCurrentHealth()
        );
        this.enemyHealthBar.setString(
                (int) this.game.getCurrentEnemy().getCurrentHealth()
                        + " / "
                        + (int) this.game.getCurrentEnemy().getMaxHealth()
        );
    }

    private void updateInventoryList() {
        final DefaultListModel<String> inventoryModel = new DefaultListModel<>();

        for (Item item : this.game.getPlayer().getInventory()) {
            inventoryModel.addElement(item.toString());
        }

        this.inventoryList.setModel(inventoryModel);
    }

    private void updateCombatMessage() {
        if (!this.game.getCurrentEnemy().isAlive()) {
            this.messageLabel.setText(
                    this.game.getCurrentEnemy().getName()
                            + " defeated!"
            );
            this.attackButton.setEnabled(false);
        } else if (!this.game.getPlayer().isAlive()) {
            this.messageLabel.setText(
                    this.game.getPlayer().getName()
                            + " defeated!"
            );
            this.attackButton.setEnabled(false);
        } else {
            this.messageLabel.setText("Turn completed.");
        }
    }

    private void updateAttackButtonState() {
        this.attackButton.setEnabled(
                this.game.getPlayer().isAlive()
                        && this.game.getCurrentEnemy().isAlive()
        );
    }

    private void useSelectedItem() {
        final Item selectedItem = this.getSelectedInventoryItem();

        if (selectedItem == null) {
            return;
        }

        if (!(selectedItem instanceof Usable)) {
            this.messageLabel.setText("Selected item cannot be used.");
            return;
        }

        this.game.getPlayer().useItem(selectedItem);
        this.refreshGameState();
        this.messageLabel.setText(selectedItem.getName() + " used.");
    }

    private void equipSelectedItem() {
        final Item selectedItem = this.getSelectedInventoryItem();

        if (selectedItem == null) {
            return;
        }

        if (!(selectedItem instanceof Equippable equippableItem)) {
            this.messageLabel.setText("Selected item cannot be equipped.");
            return;
        }

        equippableItem.equip(this.game.getPlayer());
        this.refreshGameState();
        this.messageLabel.setText(selectedItem.getName() + " equipped.");
    }

    private Item getSelectedInventoryItem() {
        final int selectedIndex = this.inventoryList.getSelectedIndex();

        if (selectedIndex == -1) {
            this.messageLabel.setText("No item selected.");
            return null;
        }

        return this.game.getPlayer()
                .getInventory()
                .get(selectedIndex);
    }

    private void handleAttack() {
        this.game.nextTurn();
        this.refreshGameState();
    }

    private void refreshGameState() {
        this.updateView();
        this.updateCombatMessage();
        this.updateAttackButtonState();
    }
}
