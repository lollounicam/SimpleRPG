package it.unicam.cs.mpgc.rpg119163;

import it.unicam.cs.mpgc.rpg119163.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg119163.model.Game;
import it.unicam.cs.mpgc.rpg119163.model.characters.Player;
import it.unicam.cs.mpgc.rpg119163.model.items.HealingPotion;

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

        player.takeDamage(40);
        System.out.println(player.getCurrentHealth());

        player.heal(20);
        System.out.println(player.getCurrentHealth());

        player.heal(999);
        System.out.println(player.getCurrentHealth());

        player.heal(100);
        System.out.println(player.getCurrentHealth());

        HealingPotion potion = new HealingPotion("Potion", "Heals 50 HP", 10, 50);
        player.addItem(potion);

        System.out.println(player.getInventory());

        player.takeDamage(40);

        System.out.println(player.getCurrentHealth());

        player.useItem(potion);

        System.out.println(player.getCurrentHealth());

        System.out.println(player.getInventory());
    }
}
