package heroes;

import heroes.battle.BattleField;
import heroes.player.Player;

public class OneDimensionGame extends Thread {

    private static Player playerOne;
    private static Player playerTwo;
    private static boolean movingStage = true;
    private static boolean fightStage = false;

    public static void setFightStage(boolean fightStage) {
        OneDimensionGame.fightStage = fightStage;
    }

   public  void run() {
        BattleField battleField = BattleField.getInstance();
        playerOne = battleField.getPlayers().get(0);
        playerTwo = battleField.getPlayers().get(1);

        playerOne.getCreatures().get(0).setCurrentCell(battleField.getCells().get(0));
        battleField.getCells().get(0).setBusy();

        playerTwo.getCreatures().get(0).setCurrentCell(battleField.getCells().get(25));
        battleField.getCells().get(25).setBusy();

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
