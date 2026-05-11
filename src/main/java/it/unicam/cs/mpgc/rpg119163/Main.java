package it.unicam.cs.mpgc.rpg119163;

import it.unicam.cs.mpgc.rpg119163.model.Game;
import it.unicam.cs.mpgc.rpg119163.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg119163.model.characters.Player;
import it.unicam.cs.mpgc.rpg119163.model.items.HealingPotion;
import it.unicam.cs.mpgc.rpg119163.model.items.Weapon;
import it.unicam.cs.mpgc.rpg119163.persistence.GameRepository;
import it.unicam.cs.mpgc.rpg119163.view.MainWindow;
import it.unicam.cs.mpgc.rpg119163.view.StartWindow;

public class Main {

    public static void main(String[] args) {
        new StartWindow();
    }
}