package it.unicam.cs.mpgc.rpg119163;

import it.unicam.cs.mpgc.rpg119163.model.GameCharacter;

public class Main {
    public static void main(String[] args) {
        GameCharacter superman = new GameCharacter("Superman", 1000,100, 100);
        System.out.println(superman);

        superman.takeDamage(20);
        System.out.println(superman);

        superman.takeDamage(500);
        System.out.println(superman);

        superman.takeDamage(-50);
        System.out.println(superman);

        superman.takeDamage(10);
        System.out.println(superman);
    }
}
