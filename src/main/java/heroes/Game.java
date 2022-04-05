package heroes;

import heroes.creatures.Creature;
import heroes.creatures.CreatureFactory;
import heroes.creatures.Mode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    static List<Creature> creatures = new ArrayList<>();
    static Random rnd = new Random();

    static {
        creatures.add(CreatureFactory.createCreature(1));
        creatures.add(CreatureFactory.createCreature(2));
        System.out.println();
    }

    public static List<Creature> getCreatures() {
        return creatures;
    }

    public static void main(String[] args) {

        while (creatures.get(0).getMode() != Mode.DEAD && creatures.get(1).getMode() != Mode.DEAD) {
            if (getRandom() <= 25) {
                switch (creatures.get(0).getMode()) {
                    case MOVING:
                        creatures.get(0).move();
                        break;
                    case FIGHTING:
                        creatures.get(0).action();
                }

            } else {
                switch (creatures.get(1).getMode()) {
                    case MOVING:
                        creatures.get(1).move();
                        break;
                    case FIGHTING:
                        creatures.get(1).action();
                }
            }
        }
    }

    public static int getRandom() {

        return rnd.nextInt(50) + 1;
    }
}
