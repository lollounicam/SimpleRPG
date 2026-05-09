package it.unicam.cs.mpgc.rpg119163;

import it.unicam.cs.mpgc.rpg119163.model.Game;
import it.unicam.cs.mpgc.rpg119163.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg119163.model.characters.Player;
import it.unicam.cs.mpgc.rpg119163.model.items.HealingPotion;
import it.unicam.cs.mpgc.rpg119163.model.items.Weapon;
import it.unicam.cs.mpgc.rpg119163.persistence.GameRepository;

public class Main {

    public static void main(String[] args) {

        testCombatSystem();

        testRewardAndLevelUpSystem();

        testHealingPotionSystem();

        testWeaponEquipSystem();

        testSaveAndLoadSystem();
    }

    private static void testCombatSystem() {

        System.out.println("--- COMBAT SYSTEM TEST ---");

        Player player = new Player("Hero", 100, 20, 5);

        Enemy goblin = new Enemy(
                "Goblin",
                50,
                10,
                3,
                25,
                8
        );

        Game game = new Game(player, goblin);

        System.out.println("Player before turn:");
        System.out.println(player);

        System.out.println("Enemy before turn:");
        System.out.println(goblin);

        game.nextTurn();

        System.out.println("Player after turn:");
        System.out.println(player);

        System.out.println("Enemy after turn:");
        System.out.println(goblin);

        System.out.println();
    }

    private static void testRewardAndLevelUpSystem() {

        System.out.println("--- REWARD AND LEVEL UP TEST ---");

        Player player = new Player("Hero", 100, 20, 5);

        Enemy boss = new Enemy(
                "Boss",
                10,
                10,
                3,
                150,
                20
        );

        Game game = new Game(player, boss);

        System.out.println("Player before boss fight:");
        System.out.println(player);

        System.out.println("Experience before fight: "
                + player.getExperience());

        System.out.println("Gold before fight: "
                + player.getGold());

        game.nextTurn();

        System.out.println("Player after boss fight:");
        System.out.println(player);

        System.out.println("Experience after fight: "
                + player.getExperience());

        System.out.println("Gold after fight: "
                + player.getGold());

        System.out.println();
    }

    private static void testHealingPotionSystem() {

        System.out.println("--- HEALING POTION TEST ---");

        Player player = new Player("Hero", 100, 20, 5);

        HealingPotion potion = new HealingPotion(
                "Small Potion",
                "Restores 50 health points",
                10,
                50
        );

        player.addItem(potion);

        System.out.println("Inventory before using potion:");
        System.out.println(player.getInventory());

        player.takeDamage(40);

        System.out.println("Health after damage: "
                + player.getCurrentHealth());

        player.useItem(potion);

        System.out.println("Health after potion: "
                + player.getCurrentHealth());

        System.out.println("Inventory after using potion:");
        System.out.println(player.getInventory());

        System.out.println();
    }

    private static void testWeaponEquipSystem() {

        System.out.println("--- WEAPON EQUIP TEST ---");

        Player player = new Player("Hero", 100, 20, 5);

        Weapon sword = new Weapon(
                "Iron Sword",
                "A basic iron sword",
                50,
                10
        );

        player.addItem(sword);

        System.out.println("Inventory before equipping weapon:");
        System.out.println(player.getInventory());

        System.out.println("Attack before equip: "
                + player.getAttack());

        sword.equip(player);

        System.out.println("Attack after equip: "
                + player.getAttack());

        System.out.println("Inventory after equipping weapon:");
        System.out.println(player.getInventory());

        System.out.println();
    }

    private static void testSaveAndLoadSystem() {

        System.out.println("--- SAVE AND LOAD TEST ---");

        GameRepository repository = new GameRepository();

        Player player = new Player("Hero", 100, 20, 5);

        Enemy goblin = new Enemy(
                "Goblin",
                50,
                10,
                3,
                25,
                8
        );

        Game game = new Game(player, goblin);

        repository.saveGame(game, "savegame.json");

        Game loadedGame = repository.loadGame("savegame.json");

        System.out.println("Loaded player:");
        System.out.println(loadedGame.getPlayer());

        System.out.println("Loaded enemy:");
        System.out.println(loadedGame.getCurrentEnemy());

        System.out.println();
    }
}