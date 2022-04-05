package heroes.creatures;

public class Troglodyte extends Creature {

    public Troglodyte(int speed, int health, int defence, String damage, int attackRange, int player) {
        super(speed, health, defence, damage, attackRange, player);
    }

    @Override
    public void superAction() {
        int bonus = rnd.nextInt(4) + 1;
        this.getSkills().setHealth(this.getSkills().getHealth() + bonus);
        System.out.println(this + ": Super Skill Activated! I have extra attack!");
        this.attack();
    }

    @Override
    public String toString() {
        return "player #" + this.getPlayer() + "'s Troglodyte";
    }
}
