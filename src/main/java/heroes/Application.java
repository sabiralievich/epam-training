package heroes;import heroes.battle.BattleField;import heroes.creatures.Creature;import heroes.creatures.CreatureGenerator;import java.util.ArrayList;import java.util.List;public class Application {   public static void main(String[] args) {    //  BattleField battleField = BattleField.getInstance();      //OneDimensionGame.placeCreaturesOnBattleField(battleField);      new OneDimensionGame().start();   }   }