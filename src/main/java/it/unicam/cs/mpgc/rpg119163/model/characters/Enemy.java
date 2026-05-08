package it.unicam.cs.mpgc.rpg119163.model.characters;

public class Enemy extends GameCharacter {

    private final int rewardExperience;
    private final int rewardGold;

    public Enemy(final String name,
                 final double maxHealth,
                 final double attack,
                 final double defense,
                 final int rewardExperience,
                 final int rewardGold) {
        super(name, maxHealth, attack, defense);
        this.rewardExperience = rewardExperience;
        this.rewardGold = rewardGold;
    }

    public int getRewardExperience() {
        return rewardExperience;
    }

    public int getRewardGold() {
        return rewardGold;
    }
}
