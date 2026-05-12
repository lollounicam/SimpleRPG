package it.unicam.cs.mpgc.rpg119163.model;

import it.unicam.cs.mpgc.rpg119163.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg119163.model.characters.Player;
import it.unicam.cs.mpgc.rpg119163.model.items.HealingPotion;
import it.unicam.cs.mpgc.rpg119163.model.items.Weapon;

import java.util.List;

public class GameFactory {
    public Game createNewGame(final String playerName) {
        final Player player = this.createPlayer(playerName);
        final Enemy enemy = this.createEnemy();

        return new Game(player, enemy);
    }

    private Player createPlayer(final String playerName) {
        final Player player = new Player(playerName, 100, 20, 5);

        this.createStarterItems(player);

        return player;
    }

    private Enemy createEnemy() {
        final List<Enemy> enemies = List.of(
                new Enemy("Goblin", 50, 10, 3, 25, 8),
                new Enemy("Orc", 80, 15, 5, 40, 12),
                new Enemy("Skeleton", 60, 12, 4, 30, 10)
        );

        return enemies.get((int) (Math.random() * enemies.size()));
    }


    private void createStarterItems(final Player player) {
        player.addItem(
                new HealingPotion("Potion", "Heals 50 HP", 10, 50)
        );
        player.addItem(
                new Weapon("Iron Sword", "A basic iron sword", 50, 10)
        );
    }


}
