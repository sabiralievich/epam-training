package heroes.player;

import heroes.castle.Castle;
import heroes.creatures.Creature;
import heroes.creatures.CreatureGenerator;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int id;
    private List<Castle> castles = new ArrayList<>();
    private List<Creature> creatures = new ArrayList<>();

    public Player(int id){

        this.id = id;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public int getId() {
        return id;
    }

    public Player(int id, Castle castle) {
        this.id = id;
        this.castles.add(castle);
        // Quantity of creatures to be created (from 1 to 2):
        for (int i = 0; i < 1; i++) {
            creatures.add(CreatureGenerator.createCreature(castle.getType()));
        }
        for(Creature creature : creatures) {
            creature.setPlayer(this);
        }
        System.out.println("player " + id + " created. He has " + this.getCreatures().size() + " creatures");
    }

    public List<Castle> getCastles() {
        return castles;
    }
}
