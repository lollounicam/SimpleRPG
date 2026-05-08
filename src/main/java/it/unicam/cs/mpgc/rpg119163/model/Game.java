package it.unicam.cs.mpgc.rpg119163.model;

/*
 * TODO:
 *  [x] Create GameCharacter base class
 *  [x] Implement Player inheritance
 *  [x] Implement Enemy inheritance
 *  [x] Implement damage system
 *  [x] Implement basic combat mechanics
 *  [x] Implement enemy attack
 *  [ ] Implement level up system
 *  [ ] Implement healing system
 *  [x] Implement combat turns
 *  [ ] Implement inventory
 *  [ ] Implement game loop
 *  [ ] Implement save/load system
 *  [ ] Implement Swing GUI
 */

public class Game {

    private Player player;
    private Enemy currentEnemy;

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
        final double playerDamage = this.playerAttack();
        //System.out.println("Player dealt " + playerDamage + " damage.");
        if (!this.currentEnemy.isAlive()) {
            this.player.gainExperience(this.currentEnemy.getRewardExperience());
            this.player.gainGold(this.currentEnemy.getRewardGold());
            System.out.println("You won the game!");
            return;
        }

        final double enemyDamage = this.enemyAttack();
        //System.out.println("Enemy dealt " + enemyDamage + " damage.");

        if (!this.player.isAlive()) {
            System.out.println("You lost the game!");
            return;
        }
    }
}
