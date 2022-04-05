package heroes;

import java.util.ArrayList;
import java.util.List;

public class Battlefield {
    private static Battlefield battlefield = null;
    private final List<Cell> cells = new ArrayList<>();

    public Battlefield() {
        System.out.println("Here is the battlefield!");
        // here to initiate cells with objects of Cell class:
        for (int i = 0; i <= 25; i++) {
            cells.add(new Cell(i));
        }
    }

    public List<Cell> getCells() {
        return cells;
    }

    public static Battlefield getInstance() {
        if (battlefield == null) {
            battlefield = new Battlefield();
        }
        return battlefield;
    }

    public static class Cell {
        private final int x;
        private boolean isBusy;

        protected Cell(int x) {
            this.x = x;
            this.setFree();
        }

        public int getX() {
            return x;
        }

        public boolean isBusy() {
            return isBusy;
        }

        public void setBusy() {
            isBusy = true;
        }

        public void setFree() {
            isBusy = false;
        }
    }
}
