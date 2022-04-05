package heroes.creatures;

public class Beholder extends Creature {

    public Beholder(int speed, int health, int defence, String damage, int attackRange, int player) {
        super(speed, health, defence, damage, attackRange, player);
    }

    @Override
    public void superAction() {
        int bonus = rnd.nextInt(4) + 1;
        this.getSkills().setHealth(this.getSkills().getHealth() + bonus);
        System.out.println(this + ": put a spell 'Damage' on " + this.getCurrentEnemy().toString());
        this.getCurrentEnemy().takeDamage(bonus);
    }

    @Override
    public String toString() {
        return "player #" + this.getPlayer() + "'s Beholder";
    }
}
