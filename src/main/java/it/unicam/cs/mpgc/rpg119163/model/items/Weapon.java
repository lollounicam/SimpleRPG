package it.unicam.cs.mpgc.rpg119163.model.items;

import it.unicam.cs.mpgc.rpg119163.model.characters.Player;

public class Weapon extends Item implements Equippable {

    private final double attackBonus;

    public Weapon(final String name, final String description, final int price, final double attackBonus) {
        super(name, description, price);
        this.attackBonus = attackBonus;
    }

    public double getAttackBonus() {
        return this.attackBonus;
    }

    @Override
    public void equip(final Player player) {
        if (player == null){
            return;
        }

        player.equipWeapon(this);
    }
}
