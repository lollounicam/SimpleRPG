package it.unicam.cs.mpgc.rpg119163.model;



import it.unicam.cs.mpgc.rpg119163.model.characters.Enemy;
import it.unicam.cs.mpgc.rpg119163.model.characters.Player;
import it.unicam.cs.mpgc.rpg119163.persistence.ShopRepository;

public class Game {

    final private Player player;
    private Enemy currentEnemy;
    private final GameFactory gameFactory;
    private final Shop shop;

    public Game(final Player player, final Enemy enemy) {
        this.player = player;
        this.currentEnemy = enemy;
        this.gameFactory = new GameFactory();
        this.shop = new Shop();
        final ShopRepository shopRepository = new ShopRepository();

        shopRepository.loadShopItems().forEach(this.shop::addItem);
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    private double playerAttack() {
        return this.player.attackTarget(this.currentEnemy);
    }

    public double enemyAttack() {
        return this.currentEnemy.attackTarget(this.player);
    }

    public String nextTurn() {
        final double playerDamage = this.playerAttack();
        //System.out.println("Player dealt " + playerDamage + " damage.");
        if (!this.currentEnemy.isAlive()) {
            this.player.gainExperience(this.currentEnemy.getRewardExperience());
            this.player.gainGold(this.currentEnemy.getRewardGold());
            this.currentEnemy = this.gameFactory.createRandomEnemy();
            //System.out.println("You won the game!");
            return "Enemy defeated!";
        }

        final double enemyDamage = this.enemyAttack();
        //System.out.println("Enemy dealt " + enemyDamage + " damage.");

        if (!this.player.isAlive()) {
            //System.out.println("You lost the game!");
            return "Player defeated!";
        }

        return this.player.getName()
                + " dealt "
                + (int) playerDamage
                + " damage to "
                + this.currentEnemy.getName()
                + ". "
                + this.currentEnemy.getName()
                + " dealt "
                + (int) enemyDamage
                + " damage back.";
    }

    public Shop getShop() {
        return this.shop;
    }
}
