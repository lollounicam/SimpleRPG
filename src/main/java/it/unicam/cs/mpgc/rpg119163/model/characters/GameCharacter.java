package it.unicam.cs.mpgc.rpg119163.model.characters;

public class GameCharacter {

    private String name;
    private int level;
    //private int experience;
    private double maxHealth;
    private double currentHealth;
    private double attack;
    private double defense;
    //private int gold;
    private boolean isAlive;

    public GameCharacter(final String name, final double maxHealth, final double attack, final double defense) {
        this.name = name;
        this.level = 1;
        //this.experience = 0;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        //this.gold = 0;
        this.isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

//    public int getGold() {
//        return gold;
//    }

    public double getDefense() {
        return defense;
    }

    public double getAttack() {
        return attack;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

//    public int getExperience() {
//        return experience;
//    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setAttack(final double attack) {
        this.attack = attack;
    }

    public void setDefense(final double defense) {
        this.defense = defense;
    }

    // Assume constructor and other methods are here

    @Override
    public String toString() {
        return "GameCharacter {\n" +
                "  Name: " + this.name + "\n" +
                "  Level: " + this.level + "\n" +
//                "  Experience: " + this.experience + "\n" +
                "  Health: " + this.currentHealth + "/" + this.maxHealth + "\n" +
                "  Attack: " + this.attack + "\n" +
                "  Defense: " + this.defense + "\n" +
//                "  Gold: " + this.gold + "\n" +
                "  Alive: " + this.isAlive + "\n" +
                "}";
    }

    public double takeDamage(final double damage) {
        if (!this.isAlive) {
            //System.out.println("Hero is already dead, no damage taken");
            return this.currentHealth;
        }

        if (damage <= 0) {
            //System.out.println("You can't take negative or zero damage!");
            return this.currentHealth;
        }

        if (this.currentHealth - damage <= 0) {
            this.currentHealth = 0;
            this.isAlive = false;
            //System.out.println("Dead hero with this damage");
            return this.currentHealth;
        }

        this.currentHealth -= damage;
        //System.out.println("Danno minore della vita, vita rimanente= " + this.currentHealth);
        return this.currentHealth;
    }

    public double attackTarget (final GameCharacter target){
        if (!this.isAlive()){
            return 0;
        }

        if (!target.isAlive()){
            return 0;
        }

        final double damage = this.attack - target.getDefense();

        if (damage <= 0){
            return 0;
        }

        target.takeDamage(damage);
        return damage;
    }



    protected void increaseLevel() {
        this.level++;
    }

    protected void increaseMaxHealth(final double amount) {
        this.maxHealth += amount;
    }
    protected void increaseAttack(final double amount) {
        this.attack += amount;
    }
    protected void increaseDefense(final double amount) {
        this.defense += amount;
    }
    protected void restoreFullHealth(){
        this.currentHealth = this.maxHealth;
    }

    public double heal(final double amount){
        if (!this.isAlive) {
            return this.currentHealth;
        }
        if (amount <= 0){
            return this.currentHealth;
        }
        this.currentHealth += amount;
        if (this.currentHealth > this.maxHealth){
            this.currentHealth = this.maxHealth;
        }
        return this.currentHealth;
    }


}
