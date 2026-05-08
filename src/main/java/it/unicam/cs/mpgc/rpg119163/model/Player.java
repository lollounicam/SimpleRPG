package it.unicam.cs.mpgc.rpg119163.model;

public class Player extends GameCharacter {

    private int experience;
    private int gold;

    public Player(final String name,
                  final double maxHealth,
                  final double attack,
                  final double defense) {
        super(name, maxHealth, attack, defense);
        this.experience = 0;
        this.gold = 0;
    }

    public int getExperience() {
        return experience;
    }

    public int getGold() {
        return gold;
    }

    public void gainExperience(final int amount){
        if(amount <= 0) {
            //System.out.println("You can't gain negative or zero experience!");
            return;
        }
        this.experience += amount;
    }
}
