package it.unicam.cs.mpgc.rpg119163;

import it.unicam.cs.mpgc.rpg119163.model.Enemy;
import it.unicam.cs.mpgc.rpg119163.model.GameCharacter;
import it.unicam.cs.mpgc.rpg119163.model.Player;

public class Main {
    public static void main(String[] args) {
        Player hero = new Player("Player", 100, 10, 10);
        Enemy enemy = new Enemy("Enemy", 100, 5, 2, 10, 10);

        System.out.println(enemy);
        double damage = hero.attackTarget(enemy);
        System.out.println("Danno inflitto: " + damage);
        System.out.println(enemy);

        Enemy tank = new Enemy("Tank", 100, 5, 50, 20, 10);

        double damageToTank = hero.attackTarget(tank);
        System.out.println("Damage dealt to tank: " + damageToTank);
        System.out.println(tank);
    }
}
