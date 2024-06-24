package MVP.GameFunctions;

import java.util.Random;

public class Dice implements Rollable {

    private int numberRolled;

    public Dice() {
    }

    public int getNumberRolled() {
        return numberRolled;
    }

    public void setNumberRolled(int numberRolled) {
        this.numberRolled = numberRolled;
    }

    @Override
    public int roll() {
        Random random=new Random();
        this.numberRolled= random.nextInt(7)+1;
        return getNumberRolled();
    }
}
