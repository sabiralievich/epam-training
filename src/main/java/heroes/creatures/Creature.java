package heroes.creatures;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import heroes.actions.CanFight;
import heroes.actions.CanMove;
import heroes.actions.CanTakeDamage;
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
    public Object getCurrentEnemy() {
        return currentEnemy;
    }

    @JsonProperty("currentEnemy")
    public void setCurrentEnemy(Creature currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    @JsonProperty("currentCell")
    public Object getCurrentCell() {
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
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}