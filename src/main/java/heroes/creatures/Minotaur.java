package heroes.creatures;import java.util.Random;public class Minotaur extends Creature {    public Minotaur() {        System.out.println("Minotaur created!");    }    public int superSkillActivate() {        Random rnd = new Random();        int bonus = rnd.nextInt(this.getSkills().getSpecialSkill());        //this.getSkills().setHealth(this.getSkills().getHealth() + addHealth);        System.out.println("Player " + this.player.getId() + ": " + this.getClass().getSimpleName() + " has +"                + bonus + " of health points (Super defence)");        return bonus;    }}