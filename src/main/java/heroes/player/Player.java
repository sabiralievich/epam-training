package heroes.player;

import heroes.castle.Castle;
import heroes.creatures.Creature;
import heroes.creatures.CreatureGenerator;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int id;
    private List<Castle> castles = new ArrayList<>();


    public Player(int id){

        this.id = id;
    }

    public Player(int id, Castle castle) {
        this.id = id;
        this.castles.add(castle);

    }
}
