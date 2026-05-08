package it.unicam.cs.mpgc.rpg119163.model.items;

import it.unicam.cs.mpgc.rpg119163.model.characters.GameCharacter;
import it.unicam.cs.mpgc.rpg119163.model.characters.Player;

public abstract class Item {

    private final String name;
    private final String description;
    private final int price;

    public Item(final String name, final String description, final int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + this.name + '\'' +
                ", description=" + this.description +
                ", price=" + this.price +
                '}';
    }

}
