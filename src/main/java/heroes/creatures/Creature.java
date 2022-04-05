package heroes.creatures;

import heroes.Battlefield;
import heroes.Game;

import java.util.ArrayList;
import java.util.Random;

public abstract class Creature implements Actions {
    static Random rnd = new Random();
    Creature currentEnemy;
    Skills skills;
    Battlefield.Cell currentCell;
    int player;

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    protected Mode mode;

    public Creature() {
    }

    public Creature(int speed, int health, int defence, String damage, int attackRange, int player) {
        this.skills = new Skills();
        this.skills.setSpeed(speed);
        this.skills.setHealth(health);
        this.skills.setDefence(defence);
        this.skills.setDamage(damage);
        this.skills.setAttackRange(attackRange);
        this.player = player;
        this.setMode(Mode.MOVING);
        this.placeOnField();
        System.out.println(this + " placed on cell #" + this.getCurrentCell().getX());
    }

    public Creature getCurrentEnemy() {
        return currentEnemy;
    }

    public Skills getSkills() {
        return skills;
    }

    public Battlefield.Cell getCurrentCell() {
        return currentCell;
    }

    public int getPlayer() {
        return player;
    }

    public void setCurrentEnemy() {
        for (Creature creature : Game.getCreatures()) {
            if (!creature.equals(this)) {
                this.currentEnemy = creature;
            }
        }
    }

    public void setCurrentCell(Battlefield.Cell currentCell) {
        this.currentCell = currentCell;
    }

    static class Skills {

        private Integer attackRange;

        private Integer defence;

        private String damage;

        private Integer health;

        private Integer speed;

        public Integer getAttackRange() {
            return attackRange;
        }

        public void setAttackRange(Integer attackRange) {
            this.attackRange = attackRange;
        }

        public Integer getDefence() {
            return defence;
        }

        public void setDefence(Integer defence) {
            this.defence = defence;
        }

        public String getDamage() {
            return damage;
        }

        public void setDamage(String damage) {
            this.damage = damage;
        }

        public Integer getHealth() {
            return health;
        }

        public void setHealth(Integer health) {
            this.health = health;
        }

        public Integer getSpeed() {
            return speed;
        }

        public void setSpeed(Integer speed) {
            this.speed = speed;
        }
    }

    public void placeOnField() {
        if (this.player == 1) {
            this.setCurrentCell(Battlefield.getInstance().getCells().get(0));
            this.getCurrentCell().setBusy();
        } else if (this.player == 2) {
            this.setCurrentCell(Battlefield.getInstance().getCells().get(25));
            this.getCurrentCell().setBusy();
        }
    }

    @Override
    public void move() {
        int direction = 0;
        if (this.player == 1) {
            direction = 1;
        }
        if (this.player == 2) {
            direction = -1;
        }
        int currentPosition = this.getCurrentCell().getX();
        int currentMoveRange = currentPosition + this.getSkills().getSpeed() * direction;
        int whereIsEnemy = whereIsEnemy();
        int enemyDistance = Math.abs(whereIsEnemy - currentPosition);

        if (enemyDistance > this.getSkills().getSpeed() && enemyDistance > this.getSkills().getAttackRange()) {
            this.getCurrentCell().setFree();
            this.setCurrentCell(Battlefield.getInstance().getCells().get(currentMoveRange));
            this.getCurrentCell().setBusy();
            System.out.println(this + " has moved at sell #" + this.getCurrentCell().getX());

        } else if (enemyDistance > this.getSkills().getSpeed()) {
            this.action();

        } else if (whereIsEnemy - direction != this.getCurrentCell().getX()) {
            this.getCurrentCell().setFree();
            this.setCurrentCell(Battlefield.getInstance().getCells().get(whereIsEnemy - direction));
            this.getCurrentCell().setBusy();
            System.out.println(this + " has moved at sell #" + this.getCurrentCell().getX());
            this.action();

        } else {
            this.action();
        }
    }

    int whereIsEnemy() {
        for (Battlefield.Cell cell : Battlefield.getInstance().getCells()) {
            if (cell.isBusy() && cell.getX() != this.getCurrentCell().getX()) {
                return cell.getX();
            }
        }
        return -1;
    }

    @Override
    public void attack() {
        int actualDamage = countDamagePoints();
        System.out.println(this + ": Attack! " + actualDamage + " pts");
        this.getCurrentEnemy().takeDamage(actualDamage);
    }

    private int countDamagePoints() {
        String text = this.getSkills().getDamage();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String part : text.split("\\-")) {
            numbers.add(Integer.parseInt(part));
        }
        int a = numbers.get(0);
        int b = numbers.get(1);
        return a + (int) (Math.random() * ((b - a) + 1));
    }

    @Override
    public void takeDamage(int damage) {
        int bonus = 0;

        if (this.getSkills().getDefence() != 0) {
            this.getSkills().setDefence(this.getSkills().getDefence() - damage);
        }
        this.getSkills().setHealth(this.getSkills().getHealth() + this.getSkills().getDefence() - damage + bonus);
        if (this.getSkills().getHealth() <= 3) {
            System.out.println(this + ": Do you really want to kill me?!");
        }
        System.out.println(this + ": I'm hurt!" + " (-" + damage + " health points)");
        if (this.getSkills().getHealth() <= 0) {
            this.die();
        }
    }

    @Override
    public void die() {
        System.out.println(this + ": I'm dead..");
        for (Creature creature : Game.getCreatures()) {
            if (creature.getCurrentEnemy() == this) {

                creature.setMode(Mode.MOVING);
            }
        }
        this.setMode(Mode.DEAD);
    }

    @Override
    public void superAction() {
    }

    public int getRandom() {
        return rnd.nextInt(50) + 1;
    }

    public void action() {
        this.setCurrentEnemy();
        this.setMode(Mode.FIGHTING);
        if (getRandom() <= 25) this.attack();
        else this.superAction();
    }
}
