package it.unicam.cs.mpgc.rpg119163.model;

import it.unicam.cs.mpgc.rpg119163.model.characters.Player;
import it.unicam.cs.mpgc.rpg119163.model.items.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShopTest {

    @Test
    void shouldBuyItemWhenPlayerHasEnoughGold() {
        final Player player = new Player("Hero", 100, 20, 5);
        player.gainGold(100);

        final Weapon weapon =
                new Weapon("Sword", "Basic sword", 50, 10);

        final Shop shop = new Shop();
        shop.addItem(weapon);

        final boolean bought = shop.buyItem(player, weapon);

        assertTrue(bought);
        assertTrue(player.getInventory().contains(weapon));
        assertEquals(50, player.getGold());
    }

    @Test
    void shouldNotBuyItemWhenPlayerHasNotEnoughGold() {
        final Player player = new Player("Hero", 100, 20, 5);

        final Weapon weapon =
                new Weapon("Sword", "Basic sword", 50, 10);

        final Shop shop = new Shop();
        shop.addItem(weapon);

        final boolean bought = shop.buyItem(player, weapon);

        assertFalse(bought);
        assertFalse(player.getInventory().contains(weapon));
        assertEquals(0, player.getGold());
    }
}