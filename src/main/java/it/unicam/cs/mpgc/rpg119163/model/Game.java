package it.unicam.cs.mpgc.rpg119163.model;

/*
 * TODO:
 *  [x] Create GameCharacter base class
 *  [x] Implement Player inheritance
 *  [x] Implement Enemy inheritance
 *  [x] Implement damage system
 *  [x] Implement basic combat mechanics
 *  [x] Implement enemy attack
 *  [x] Implement level up system
 *  [x] Implement healing system
 *  [x] Implement combat turns
 *  [x] Implement inventory
 *  [x] Implement game loop
 *  [x] Implement save/load system
 *  [x] Implement Swing GUI
 */

import it.unicam.cs.mpgc.rpg119163.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg119163.model.characters.Player;

public class Game {

    final private Player player;
    final private Enemy currentEnemy;

    public Game(final Player player, final Enemy enemy) {
        this.player = player;
        this.currentEnemy = enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    public double playerAttack() {
        return this.player.attackTarget(this.currentEnemy);
    }

    public double enemyAttack() {
        return this.currentEnemy.attackTarget(this.player);
    }

    public void nextTurn() {
        this.playerAttack();
        //System.out.println("Player dealt " + playerDamage + " damage.");
        if (!this.currentEnemy.isAlive()) {
            this.player.gainExperience(this.currentEnemy.getRewardExperience());
            this.player.gainGold(this.currentEnemy.getRewardGold());
            //System.out.println("You won the game!");
            return;
        }

        this.enemyAttack();
        //System.out.println("Enemy dealt " + enemyDamage + " damage.");

        if (!this.player.isAlive()) {
            //System.out.println("You lost the game!");
            return;
        }
    }
}
