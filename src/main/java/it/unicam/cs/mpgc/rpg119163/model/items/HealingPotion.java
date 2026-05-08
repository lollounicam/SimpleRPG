package it.unicam.cs.mpgc.rpg119163.model.items;

import it.unicam.cs.mpgc.rpg119163.model.characters.GameCharacter;

public class HealingPotion extends Item {
    private final double healingAmount;

    public HealingPotion(final String name,  final String description,final int price, final double healingAmount) {
        super(name, description, price);
        this.healingAmount = healingAmount;
    }

    public double getHealingAmount() {
        return this.healingAmount;
    }

    @Override
    public void use(GameCharacter target) {
        target.heal(this.healingAmount);
    }
}
