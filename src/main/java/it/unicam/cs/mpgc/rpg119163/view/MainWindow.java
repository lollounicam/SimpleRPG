package it.unicam.cs.mpgc.rpg119163.view;

import it.unicam.cs.mpgc.rpg119163.model.Game;
import it.unicam.cs.mpgc.rpg119163.model.items.Equippable;
import it.unicam.cs.mpgc.rpg119163.model.items.Item;
import it.unicam.cs.mpgc.rpg119163.model.items.Usable;
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
    private final JLabel playerStatsLabel;

    private final JList<String> inventoryList;

    private final JButton useItemButton;
    private final JButton equipItemButton;

    public MainWindow(final Game game) {

        this.game = game;
        this.gameRepository = new GameRepository();

        this.inventoryList = new JList<>();

        this.useItemButton = new JButton("Use Item");
        this.equipItemButton = new JButton("Equip Item");

        this.configureWindow();

        this.playerHealthLabel = new JLabel();
        this.enemyHealthLabel = new JLabel();
        this.messageLabel = new JLabel("Welcome to SimpleRPG!");
        this.playerStatsLabel = new JLabel();

        this.playerHealthBar = new JProgressBar();
        this.enemyHealthBar = new JProgressBar();

        this.playerHealthBar.setMinimum(0);
        this.enemyHealthBar.setMinimum(0);
        this.playerHealthBar.setStringPainted(true);
        this.enemyHealthBar.setStringPainted(true);

        this.attackButton = new JButton("Attack");
        this.saveButton = new JButton("Save");
        this.loadButton = new JButton("Load");


        this.buildLayout();
        this.updateLabels();
        this.configureActions();

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
                + " / "
                + (int) this.game.getPlayer().getMaxHealth()
        );

        this.enemyHealthBar.setString(
                (int) this.game.getCurrentEnemy().getCurrentHealth()
                + " / "
                + (int) this.game.getCurrentEnemy().getMaxHealth()
        );

        this.playerStatsLabel.setText(
                "Level: " + this.game.getPlayer().getLevel()

                        + " | XP: " + this.game.getPlayer().getExperience()

                        + " | Gold: " + this.game.getPlayer().getGold()

                        + " | ATK: " + this.game.getPlayer().getAttack()

                        + " | DEF: " + this.game.getPlayer().getDefense()
        );

        DefaultListModel<String> inventoryModel =
                new DefaultListModel<>();
        for (Item item : this.game.getPlayer().getInventory()) {
            inventoryModel.addElement(item.getName());
        }

        this.inventoryList.setModel(inventoryModel);
    }

    private void configureWindow() {
        setTitle("SimpleRPG");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void buildLayout() {
        JPanel topPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JScrollPane inventoryScrollPane =
                new JScrollPane(this.inventoryList);
        inventoryScrollPane.setPreferredSize((
                    new Dimension(150, 0)
                ));

        centerPanel.setLayout(new GridLayout(3, 2));
        bottomPanel.setLayout(new FlowLayout());

        topPanel.add(this.messageLabel);

        centerPanel.add(this.playerHealthLabel);
        centerPanel.add(this.playerHealthBar);
        centerPanel.add(this.enemyHealthLabel);
        centerPanel.add(this.enemyHealthBar);
        centerPanel.add(this.playerStatsLabel);

        bottomPanel.add(this.attackButton);
        bottomPanel.add(this.saveButton);
        bottomPanel.add(this.loadButton);
        bottomPanel.add(this.useItemButton);
        bottomPanel.add(this.equipItemButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new BorderLayout());

        JLabel inventoryLabel = new JLabel("Inventory");

        inventoryPanel.add(inventoryLabel, BorderLayout.NORTH);
        inventoryPanel.add(inventoryScrollPane, BorderLayout.CENTER);

        add(inventoryPanel, BorderLayout.EAST);

    }

    private void configureActions() {

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
                        } catch (IllegalStateException exception) {
                            this.messageLabel.setText("Unable to load the game.");
                        }
                    }
            );

            this.useItemButton.addActionListener(
                    e -> {
                        final int selectedIndex =
                                this.inventoryList.getSelectedIndex();
                        if (selectedIndex == -1) {
                            this.messageLabel.setText("No item selected.");
                            return;
                        }
                        Item selectedItem =
                                this.game.getPlayer()
                                        .getInventory()
                                        .get(selectedIndex);
                        if (!(selectedItem instanceof Usable)) {
                            this.messageLabel.setText("Selected item cannot be used.");
                            return;
                        }

                        this.game.getPlayer().useItem(selectedItem);

                        this.updateLabels();
                        this.messageLabel.setText(
                                selectedItem.getName()
                                + " used."
                        );
                    }

            );

        this.equipItemButton.addActionListener(
                e -> {
                    final int selectedIndex =
                            this.inventoryList.getSelectedIndex();

                    if (selectedIndex == -1) {
                        this.messageLabel.setText("No item selected.");
                        return;
                    }

                    Item selectedItem =
                            this.game.getPlayer()
                                    .getInventory()
                                    .get(selectedIndex);

                    if (!(selectedItem instanceof Equippable equippableItem)) {
                        this.messageLabel.setText("Selected item cannot be equipped.");
                        return;
                    }

                    equippableItem.equip(this.game.getPlayer());

                    this.updateLabels();

                    this.messageLabel.setText(
                            selectedItem.getName()
                                    + " equipped."
                    );
                }
                );
        }
    }
