package it.unicam.cs.mpgc.rpg119163.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.unicam.cs.mpgc.rpg119163.model.items.HealingPotion;
import it.unicam.cs.mpgc.rpg119163.model.items.Item;
import it.unicam.cs.mpgc.rpg119163.model.items.Weapon;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopRepository {

    private final Gson gson;

    public ShopRepository() {
        this.gson = new Gson();
    }

    public List<Item> loadShopItems() {
        final InputStream inputStream =
                this.getClass().getResourceAsStream("/shop_items.json");

        if (inputStream == null) {
            return new ArrayList<>();
        }

        final InputStreamReader reader = new InputStreamReader(inputStream);

        final Type listType = new TypeToken<List<Map<String, Object>>>() {
        }.getType();

        final List<Map<String, Object>> rawItems =
                this.gson.fromJson(reader, listType);

        final List<Item> items = new ArrayList<>();

        for (Map<String, Object> rawItem : rawItems) {
            final String type = (String) rawItem.get("type");
            final String name = (String) rawItem.get("name");
            final String description = (String) rawItem.get("description");
            final int price = ((Double) rawItem.get("price")).intValue();

            if ("HEALING_POTION".equals(type)) {
                final int healingAmount =
                        ((Double) rawItem.get("healingAmount")).intValue();

                items.add(
                        new HealingPotion(
                                name,
                                description,
                                price,
                                healingAmount
                        )
                );
            }

            if ("WEAPON".equals(type)) {
                final int attackBonus =
                        ((Double) rawItem.get("attackBonus")).intValue();

                items.add(
                        new Weapon(
                                name,
                                description,
                                price,
                                attackBonus
                        )
                );
            }
        }

        return items;
    }
}
