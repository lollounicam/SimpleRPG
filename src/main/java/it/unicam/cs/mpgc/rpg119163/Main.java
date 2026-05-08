package it.unicam.cs.mpgc.rpg119163;

import it.unicam.cs.mpgc.rpg119163.model.GameCharacter;
import it.unicam.cs.mpgc.rpg119163.model.Player;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Player", 100, 10, 10);

        System.out.println("Current health: " + player.getCurrentHealth() + "");
        player.takeDamage(10);
        System.out.println("Current health: " + player.getCurrentHealth() + "");

        System.out.println("Experience: " + player.getExperience() + "");
        player.gainExperience(10);
        System.out.println("Experience: " + player.getExperience() + "");;

    }
}
