package it.unicam.cs.mpgc.rpg119163.model;

import it.unicam.cs.mpgc.rpg119163.model.characters.Player;
import it.unicam.cs.mpgc.rpg119163.model.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private final List<Item> items;

    public Shop() {
        this.items = new ArrayList<>();
    }

    public void addItem(final Item item) {
        if (item != null){
            this.items.add(item);
        }
    }

    public List<Item> getItems() {
        return List.copyOf(this.items);
    }
    public boolean buyItem(final Player player, final Item item) {
        if (player == null || item == null) {
            return false;
        }
        if (!this.items.contains(item)) {
            return false;
        }
        if (player.getGold() < item.getPrice()) {
            return false;
        }

        player.spendGold(item.getPrice());
        player.addItem(item);

        return true;
    }


}
