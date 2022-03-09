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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "nestHouse",
        "skills",
        "currentEnemy",
        "currentCell",
        "isAlive",
        "isActive"
})
@Generated("jsonschema2pojo")
public abstract class Creature implements CanMove, CanFight, CanTakeDamage {


    @JsonProperty("nestHouse")
    private String nestHouse;
    @JsonProperty("skills")
    private Skills skills;
    @JsonProperty("currentEnemy")
    private Object currentEnemy;
    @JsonProperty("currentCell")
    private Object currentCell;
    @JsonProperty("isAlive")
    private Boolean isAlive;
    @JsonProperty("isActive")
    private Boolean isActive;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
    public void setCurrentEnemy(Object currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    @JsonProperty("currentCell")
    public Object getCurrentCell() {
        return currentCell;
    }

    @JsonProperty("currentCell")
    public void setCurrentCell(Object currentCell) {
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