package heroes.creatures;

import java.util.Random;

public class CreatureFactory {

    static Random rnd = new Random();

    public static Creature createCreature(int player) {

        Creature creature;
        int rnd = getRandom();
        if (rnd > 0 && rnd <= 30) {
            creature = new Troglodyte(4, 5, 3, "1-3", 4, player);
        } else if (rnd > 30 && rnd <= 60) {
            creature = new Minotaur(6, 50, 12, "12-20", 4, player) {
            };
        } else {
            creature = new Beholder(5, 22, 7, "9-20", 22, player);
        }
        return creature;
    }

    private static int getRandom() {

        return rnd.nextInt(90) + 1;
    }
}
