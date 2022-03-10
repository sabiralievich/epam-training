package heroes.creatures;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import heroes.OneDimensionGame;
import heroes.actions.CanFight;
import heroes.actions.CanMove;
import heroes.actions.CanTakeDamage;
import heroes.battle.BattleField;
import heroes.battle.Cell;
import heroes.castle.Castle;
import heroes.player.Player;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "castle",
        "nestHouse",
        "skills",
        "currentEnemy",
        "currentCell",
        "isAlive",
        "isActive"
})
@Generated("jsonschema2pojo")
public abstract class Creature implements CanMove, CanFight, CanTakeDamage {

    @JsonProperty("castle")
    private String castle;
    @JsonProperty("nestHouse")
    private String nestHouse;
    @JsonProperty("skills")
    private Skills skills;
    @JsonProperty("currentEnemy")
    private Creature currentEnemy;
    @JsonProperty("currentCell")
    private Cell currentCell;
    @JsonProperty("isAlive")
    private Boolean isAlive;
    @JsonProperty("isActive")
    private Boolean isActive;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonIgnore
    Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @JsonProperty("nestHouse")
    public String getNestHouse() {
        return nestHouse;
    }

    @JsonProperty("nestHouse")
    public void setNestHouse(String nestHouse) {
        this.nestHouse = nestHouse;
    }

    @JsonProperty("skills")
    public Skills getSkills() {
        return skills;
    }

    @JsonProperty("skills")
    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    @JsonProperty("currentEnemy")
    public Creature getCurrentEnemy() {
        return currentEnemy;
    }

    @JsonProperty("currentEnemy")
    public void setCurrentEnemy(Creature currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    @JsonProperty("currentCell")
    public Cell getCurrentCell() {
        return currentCell;
    }

    @JsonProperty("currentCell")
    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    @JsonProperty("isAlive")
    public Boolean getIsAlive() {
        return isAlive;
    }

    @JsonProperty("isAlive")
    public void setIsAlive(Boolean isAlive) {
        this.isAlive = isAlive;
    }

    @JsonProperty("isActive")
    public Boolean getIsActive() {
        return isActive;
    }

    @JsonProperty("isActive")
    public void activate() {
        this.isActive = true;
    }

    @JsonIgnore
    public void deactivate() {
        this.isActive = false;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int move() {
        int direction = 0;
        if (this.player.getId() == 1) direction = 1;
        if (this.player.getId() == 2) direction = -1;
        int startPoint = this.getCurrentCell().getX();
        int endPoint = startPoint + this.getSkills().getSpeed() * direction;
        Cell busy = null;

        for (int i = this.currentCell.getX() + direction; i != endPoint; i += direction) {
            if (!BattleField.getInstance().getCells().get(i).isBusy()) {
                this.getCurrentCell().setFree();
                this.setCurrentCell(BattleField.getInstance().getCells().get(i));
                this.getCurrentCell().setBusy();
                System.out.println("Now I'm standing at cell #" + this.getCurrentCell().getX());
            } else {
                System.out.println("Time to fight!");

                break;
            }
        }

        return 0;
    }

    public boolean enemyIsNear() {
        int direction = 0;
        if (this.player.getId() == 1) direction = 1;
        if (this.player.getId() == 2) direction = -1;
        int currentPosition = this.getCurrentCell().getX();
        int attackRange = this.getSkills().getAttackRange();
        int currerntAttackRange = currentPosition + attackRange;

        for (int i = this.currentCell.getX() + direction; i != direction * currerntAttackRange; i += direction) {
            if(BattleField.getInstance().getCells().get(i).isBusy()) {
                for(Player player : BattleField.getInstance().getPlayers()) {
                    for(Creature creature : player.getCreatures()) {
                        if(creature.getCurrentCell().getX() == BattleField.getInstance().getCells().get(i).getX()){
                            this.setCurrentEnemy(creature);
                            creature.setCurrentEnemy(this);

                            return true;
                        }
                    }
                }
            } else return false;
        }
        return false;
    }

    @Override
    public int attack() {
        System.out.println("Attack!");
        this.getCurrentEnemy().takeDamage(this.getSkills().getDamage());
        return 0;
    }

    @Override
    public int takeDamage(int damage){
        this.getSkills().setHealth(this.getSkills().getHealth() + this.getSkills().getDefence() - damage);
        System.out.println("I'm hurt!");
        if(this.getSkills().getHealth() <= 0) {
            this.die();
        }
        return 0;
    }

    private void die() {
        System.out.println("I'm dead..");
        this.setIsAlive(false);
        OneDimensionGame.fightStage = false;
    }
}