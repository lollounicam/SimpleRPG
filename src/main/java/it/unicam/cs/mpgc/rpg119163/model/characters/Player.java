package it.unicam.cs.mpgc.rpg119163.model.characters;

import it.unicam.cs.mpgc.rpg119163.model.items.Item;
import it.unicam.cs.mpgc.rpg119163.model.items.Usable;
import it.unicam.cs.mpgc.rpg119163.model.items.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player extends GameCharacter {

    private int experience;
    private int gold;
    private Weapon equippedWeapon;
    private transient final List<Item> inventory;

    public Player(final String name,
                  final double maxHealth,
                  final double attack,
                  final double defense) {
        super(name, maxHealth, attack, defense);
        this.experience = 0;
        this.gold = 0;
        this.equippedWeapon = null;
        this.inventory = new ArrayList<>();
    }

    public int getExperience() {
        return this.experience;
    }

    public int getGold() {
        return this.gold;
    }

    public void gainExperience(final int amount) {
        if (amount <= 0) {
            //System.out.println("You can't gain negative or zero experience!");
            return;
        }
        this.experience += amount;
        this.levelUpIfPossible();
    }

    public void gainGold(final int amount) {
        if (amount <= 0) {
            //System.out.println("You can't gain negative or zero gold!");
            return;
        }
        this.gold += amount;
    }

    private int experienceToNextLevel() {
        return (int) Math.round(100 * Math.pow(1.5, this.getLevel() - 1));
    }

    private void applyLevelUpBonus() {
        this.increaseMaxHealth(10);
        this.increaseAttack(3);
        this.increaseDefense(2);
        this.restoreFullHealth();
    }

//    private void checkLevelUp() {
//        if(this.experience == this.experienceToNextLevel()) {
//            this.increaseLevel();
//        }
//        if(this.experience > this.experienceToNextLevel()) {
//            int exp = this.experienceToNextLevel() - this.experience;
//            this.increaseLevel();
//            this.experience = exp;
//        }

    private boolean checkLevelUp() {
        return this.experience >= this.experienceToNextLevel();
    }

    private void levelUpIfPossible() {
        while (this.checkLevelUp()) {
            final int exp = this.experience - this.experienceToNextLevel();
            this.increaseLevel();
            this.applyLevelUpBonus();
            this.experience = exp;
        }
    }

    public void addItem(final Item item) {
        if (item == null) {
            return;
        }
        this.inventory.add(item);
    }

    public List<Item> getInventory() {
        if (this.inventory == null) {
            return List.of();
        }
        return List.copyOf(this.inventory);
    }

    public void useItem(final Item item) {
        if (item == null) {
            return;
        }
        if (!this.inventory.contains(item)) {
            return;
        }
        if (!(item instanceof Usable usableItem)) {
            return;
        }
        usableItem.use(this);
        this.inventory.remove(item);
    }

    public void equipWeapon(final Weapon weapon) {
        if (weapon == null) {
            return;
        }

        if (!this.inventory.contains(weapon)) {
            return;
        }
        if (this.equippedWeapon != null) {
            this.decreaseAttack(this.equippedWeapon.getAttackBonus());
            this.inventory.add(this.equippedWeapon);
        }

        this.increaseAttack(weapon.getAttackBonus());
        this.equippedWeapon = weapon;
        this.inventory.remove(weapon);
    }

    public Weapon getEquippedWeapon() {
        return this.equippedWeapon;
    }

}
