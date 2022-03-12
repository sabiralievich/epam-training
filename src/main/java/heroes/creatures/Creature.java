package heroes.creatures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
            } else {
                System.out.println("Player " + this.player.getId() + ": " + this.getClass().getSimpleName() + ": It's time to fight!!");

                break;
            }

        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Player " + this.player.getId() + ": " + this.getClass().getSimpleName() + " has moved at cell #" + this.getCurrentCell().getX());
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
            if (BattleField.getInstance().getCells().get(i).isBusy()) {
                for (Player player : BattleField.getInstance().getPlayers()) {
                    for (Creature creature : player.getCreatures()) {
                        if (creature.getCurrentCell().getX() == BattleField.getInstance().getCells().get(i).getX()) {
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
        int bonus = 0;
        if(this instanceof Troglodyte) {
            bonus = this.superSkillActivate();
        }
        int actualDamage = countDamagePoints(this.getSkills().getDamage() + bonus);
        System.out.println("Player " + this.player.getId() + "'s " + this.getClass().getSimpleName() + ": Attack! " + actualDamage + " pts");
        this.getCurrentEnemy().takeDamage(actualDamage);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int countDamagePoints(String damage) {
        String text = this.getSkills().getDamage();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String part : text.split("\\-")) {
            numbers.add(Integer.parseInt(part));
        }
        int a = numbers.get(0);
        int b = numbers.get(1);
        int actualDamage = a + (int) (Math.random() * ((b - a) + 1));
        return actualDamage;
    }

    @Override
    public int takeDamage(int damage) {
        int bonus = 0;
        if(this instanceof Minotaur) {
            bonus = this.superSkillActivate();
        }
        if(this.getSkills().getDefence() != 0) {
            this.getSkills().setDefence(this.getSkills().getDefence() - damage);
        }
        this.getSkills().setHealth(this.getSkills().getHealth() + this.getSkills().getDefence() - damage + bonus);
        if(this.getSkills().getHealth() <= 3) {
            System.out.println("Player " + this.player.getId() + "'s " + this.getClass().getSimpleName() + ": Do you realy want to kill me?!");
        }
        System.out.println("Player " + this.player.getId() + "'s " + this.getClass().getSimpleName() + ": I'm hurt!" + " (-" + damage + " health points)");
        if (this.getSkills().getHealth() <= 0) {
            this.die();
        }
        return 0;
    }

    private void die() {
        System.out.println("Player " + this.player.getId() + "'s " + this.getClass().getSimpleName() + ": I'm dead..");
        this.setIsAlive(false);
        OneDimensionGame.fightStage = false;
    }


    public abstract int superSkillActivate();
}