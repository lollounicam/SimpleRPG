package it.unicam.cs.mpgc.rpg119163.model.characters;

import it.unicam.cs.mpgc.rpg119163.model.items.HealingPotion;
import it.unicam.cs.mpgc.rpg119163.model.items.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void shouldEquipWeaponFromInventory() {
        final Player player = new Player("Hero", 100, 20, 5);

        final Weapon weapon =
                new Weapon("Sword", "Basic sword", 50, 10);

        player.addItem(weapon);
        player.equipWeapon(weapon);

        assertEquals(30, player.getAttack());
        assertFalse(player.getInventory().contains(weapon));
        assertSame(weapon, player.getEquippedWeapon());
    }

    @Test
    void shouldUseHealingPotion() {
        final Player player =
                new Player("Hero", 100, 20, 5);

        player.takeDamage(50);

        final HealingPotion potion =
                new HealingPotion(
                        "Potion",
                        "Heals 30 HP",
                        10,
                        30
                );

        player.addItem(potion);

        player.useItem(potion);

        assertEquals(80, player.getCurrentHealth());

        assertFalse(
                player.getInventory().contains(potion)
        );
    }

    @Test
    void shouldNotEquipWeaponOutsideInventory() {
        final Player player = new Player("Hero", 100, 20, 5);

        final Weapon weapon =
                new Weapon("Sword", "Basic sword", 50, 10);

        player.equipWeapon(weapon);

        assertEquals(20, player.getAttack());
        assertFalse(player.getInventory().contains(weapon));
        assertNull(player.getEquippedWeapon());
    }
}