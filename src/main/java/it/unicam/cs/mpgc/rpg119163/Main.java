package it.unicam.cs.mpgc.rpg119163;

import it.unicam.cs.mpgc.rpg119163.model.Enemy;
import it.unicam.cs.mpgc.rpg119163.model.Game;
import it.unicam.cs.mpgc.rpg119163.model.Player;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Hero", 100, 20, 5);
        Enemy goblin = new Enemy("Goblin", 10, 10, 3, 25, 8);
        Enemy boss = new Enemy("Boss", 10, 10, 3, 150, 20);

          Game game = new Game(player, boss);
//        game.nextTurn();
//        System.out.println("XP: " + player.getExperience());
//        System.out.println("Gold: " + player.getGold());

        System.out.println("--------------------------");

        game.nextTurn();

        System.out.println(player);
        System.out.println("XP: " + player.getExperience());
        System.out.println("Level: " + player.getLevel());
    }
}
