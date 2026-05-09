package it.unicam.cs.mpgc.rpg119163;

import it.unicam.cs.mpgc.rpg119163.model.Game;
import it.unicam.cs.mpgc.rpg119163.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg119163.model.characters.Player;
import it.unicam.cs.mpgc.rpg119163.model.items.HealingPotion;
import it.unicam.cs.mpgc.rpg119163.model.items.Weapon;
import it.unicam.cs.mpgc.rpg119163.persistence.GameRepository;
import it.unicam.cs.mpgc.rpg119163.view.MainWindow;

public class Main {

    public static void main(String[] args) {

        Player player = new Player("Hero", 100, 20, 5);

        Enemy goblin = new Enemy(
                "Goblin",
                50,
                10,
                3,
                25,
                8
        );

        player.addItem(new Weapon(
                "Iron Sword",
                "iron sword",
                10,
                10
        ));

        Game game = new Game(player, goblin);

        new MainWindow(game);
    }
}