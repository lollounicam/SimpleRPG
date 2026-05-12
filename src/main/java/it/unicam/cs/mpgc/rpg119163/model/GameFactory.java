package it.unicam.cs.mpgc.rpg119163.model;

import it.unicam.cs.mpgc.rpg119163.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg119163.model.characters.Player;
import it.unicam.cs.mpgc.rpg119163.model.items.HealingPotion;
import it.unicam.cs.mpgc.rpg119163.model.items.Weapon;

public class GameFactory {
    public Game createNewGame(String playerName) {
        final Player player = new Player(playerName, 100, 20, 5);

        player.addItem(
                new HealingPotion("Potion", "Heals 50 HP", 10, 50)
        );
        player.addItem(
                new Weapon("Iron Sword", "A basic iron sword", 50, 10)
        );

        final Enemy enemy = new Enemy("Goblin", 50, 10, 3, 25, 8);

        return new Game(player, enemy);
    }
}
