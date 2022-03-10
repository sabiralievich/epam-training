package heroes;

import heroes.battle.BattleField;
import heroes.battle.Cell;
import heroes.creatures.Creature;
import heroes.player.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OneDimensionGame {

    static BattleField battleField;
    static Player playerOne;
    static Player playerTwo;
    public static boolean movingStage = true;
    public static boolean fightStage = false;

    public static void placeCreaturesOnBattleField(BattleField battleField) {
        battleField = battleField;
        playerOne = battleField.getPlayers().get(0);
        playerTwo = battleField.getPlayers().get(1);

        playerOne.getCreatures().get(0).setCurrentCell(battleField.getCells().get(0));
        battleField.getCells().get(0).setBusy();

        playerTwo.getCreatures().get(0).setCurrentCell(battleField.getCells().get(25));
        battleField.getCells().get(25).setBusy();

        // BattleField.getInstance().getCells().get(5).setBusy();
        // BattleField.getInstance().getCells().get(20).setBusy();*/

        while (movingStage) {
            if (!playerOne.getCreatures().get(0).enemyIsNear()) {
                playerOne.getCreatures().get(0).move();
            } else {
                movingStage = false;
                fightStage = true;

            }
            if (!playerTwo.getCreatures().get(0).enemyIsNear()) {
                playerTwo.getCreatures().get(0).move();
            } else {
                movingStage = false;
                fightStage = true;
            }
        }

        while (fightStage) {
            if (playerOne.getCreatures().get(0).getIsAlive() && playerTwo.getCreatures().get(0).getIsAlive()) {
                playerOne.getCreatures().get(0).attack();
            } else {
                System.out.println("Game Over");
            }

            if (playerOne.getCreatures().get(0).getIsAlive() && playerTwo.getCreatures().get(0).getIsAlive()) {
                playerTwo.getCreatures().get(0).attack();

            }else {
                System.out.println("Game Over");
            }

        }

    }

}
