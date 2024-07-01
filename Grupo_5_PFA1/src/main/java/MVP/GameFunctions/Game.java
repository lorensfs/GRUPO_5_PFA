package MVP.GameFunctions;

import MVP.Player.Player;
import MVP.Player.ReadyPlayerQueue;
import MVP.PrizePenalty.Operation;
import MVP.PrizePenalty.PrizePenalty;
import MVP.PrizePenalty.PrizePenaltyStack;
import MVP.PrizePenalty.Type;

import java.util.Scanner;
/**
 * The {@code Game} class represents the main game controller
 * managing player queues, dice rolling, prize/penalty stacks,
 * and game operations.
 * <br>
 * This class allows players to join the game, roll dice, handle
 * turns, and manage game states.
 * <br>
 * It also includes methods for initializing game data, refilling
 * prize/penalty stacks, and managing player interactions.
 * <br>
 * The game progresses by players taking turns based on dice rolls
 * and applying effects from the prize/penalty stack.
 * <br>
 * Note: This class assumes the existence of other classes such as
 * {@link ReadyPlayerQueue}, {@link PrizePenaltyStack}, {@link Dice},
 * {@link Player}, {@link PrizePenalty}, and {@link Operation}.
 * Dependencies: Java Scanner class.
 *
 * @author Lorenzo
 * @version 1.0
 */
public class Game {
    private Scanner scanner=new Scanner(System.in);
    private final ReadyPlayerQueue readyPlayerQueue=new ReadyPlayerQueue();
    private final PrizePenaltyStack prizePenaltyStack=new PrizePenaltyStack();
    private Boolean state; //True: ON - False: OFF
    private Dice dice1=new Dice();
    private Dice dice2=new Dice();

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public ReadyPlayerQueue getReadyPlayerQueue() {
        return readyPlayerQueue;
    }

    public PrizePenaltyStack getPrizePenaltyStack() {
        return prizePenaltyStack;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Dice getDice1() {
        return dice1;
    }

    public void setDice1(Dice dice1) {
        this.dice1 = dice1;
    }

    public Dice getDice2() {
        return dice2;
    }

    public void setDice2(Dice dice2) {
        this.dice2 = dice2;
    }

    public Game() {
        this.state = false;
    }

    /**
     * Starts the game by initializing player queue and game data.
     * Players are added to the queue until it reaches 4 players.
     * Initializes the prize/penalty stack with default values.
     *
     * @author Lorenzo
     */
    public void gameStart(){
        int n=1;
        while (readyPlayerQueue.size()!=4) {
            System.out.println("Player #"+n+"\n"+"ENTER YOUR NAME: ");
            String name=scanner.nextLine();
            readyPlayerQueue.offer(new Player(name));
            n++;
        }
        initDataStack();
        this.state=true;
    }

    /**
     * Checks if the game should end based on the state of the ready player queue.
     *
     * @return true if there are no players left in the ready player queue, indicating the game should end; false otherwise.
     *
     * @author Lorenzo
     */
    public boolean gameEnd(){
        if(readyPlayerQueue.empty()){
            this.state=false;
            return true;
        }
        return false;
    }

    /**
     * Handles a player's turn by rolling dice, calculating new position,
     * and applying effects from the prize/penalty stack.
     *
     * @param player the player whose turn is being handled
     * @author Lorenzo
     */
    public void handleTurn(Player player){
        System.out.println("Player "+player.getName()+ " rolls the dices:"
                + "\nDICE #1: Rolled="+dice1.roll()
                + "\nDICE #2: Rolled="+dice2.roll());
        int totalRoll=(dice1.getNumberRolled()+dice2.getNumberRolled());
        int tempPosition=player.getPosition();
        if(totalRoll%2==0){
            PrizePenalty tempPrizePenalty=prizePenaltyStack.pop().getData();
            if(tempPrizePenalty.getOperation().getSymbol().equals("+")){
                int tempNumber=tempPrizePenalty.getValue();
                player.setPosition(player.getPosition()+totalRoll+tempNumber);
                System.out.println("Player "+player.getName()+" rolled a pair number."+"\nPlayer reward is"
                        +" advancing "+tempNumber+" km additionally ");
                System.out.println("Player "+player.getName()+ " you are currently at "+tempPosition+"KM "
                        +"you can advance "+ totalRoll+"KM "+", your new position is: "+player.getPosition()+"KM");
            }
            if(tempPrizePenalty.getOperation().getSymbol().equals("-")){
                int tempNumber=tempPrizePenalty.getValue();
                player.setPosition(player.getPosition()+totalRoll-tempNumber);
                System.out.println("Player "+player.getName()+" rolled a pair number."+"\nPlayer penalty is"
                        +" retracing "+tempNumber+" km additionally");
                System.out.println("Player "+player.getName()+ " you are currently at "+tempPosition+"KM "
                        +"you can advance "+ totalRoll+"KM "+", your new position is: "+player.getPosition()+"KM");
            }
            if(tempPrizePenalty.getOperation().getSymbol().equals("=")){
                player.setPosition(1);
                System.out.println("Player "+player.getName()+" rolled a pair number."+"\nPlayer is set at 1KM position");
                System.out.println("Player "+player.getName()+ " you are currently at "+tempPosition+"KM "
                        +"and now at "+player.getPosition()+"KM");
            }
        }else{
            player.setPosition(player.getPosition()+totalRoll);
            System.out.println("Player "+player.getName()+ " you are currently at "+tempPosition+"KM "
                    +"you can advance "+ totalRoll+"KM "+", your new position is: "+player.getPosition()+" KM");
        }
    }


    public Player loopCyclePlayers(){
        Player temp= readyPlayerQueue.poll().getData();
        readyPlayerQueue.offer(temp);
        return temp;
    }

    /**
     * Initializes the prize/penalty stack with default values if it's empty.
     *
     * @author Lorenzo
     */
    public void initDataStack() {
        prizePenaltyStack.push(new PrizePenalty(Operation.ADD, 2, Type.PREMIO,
                "Significa que suma dos posiciones"));
        prizePenaltyStack.push(new PrizePenalty(Operation.ADD, 8, Type.PREMIO,
                "Significa que suma ocho posiciones"));
        prizePenaltyStack.push(new PrizePenalty(Operation.SUBTRACT, 3, Type.CASTIGO,
                "Significa que resta tres posiciones"));
        prizePenaltyStack.push(new PrizePenalty(Operation.ADD, 0, Type.NO_AFECTA,
                "Significa que se queda en la posición actual"));
        prizePenaltyStack.push(new PrizePenalty(Operation.EQUAL, 1, Type.CASTIGO,
                "Significa que se debe ir a la posición 1"));

    }

    /**
     * Refills the prize/penalty stack with default values if it's empty.
     *
     * @author Lorenzo
     */
    public void refillPrizePenaltyStack() {
        if (prizePenaltyStack.size()==0) {
            prizePenaltyStack.push(new PrizePenalty(Operation.ADD, 2, Type.PREMIO,
                    "Significa que suma dos posiciones"));
            prizePenaltyStack.push(new PrizePenalty(Operation.ADD, 8, Type.PREMIO,
                    "Significa que suma ocho posiciones"));
            prizePenaltyStack.push(new PrizePenalty(Operation.SUBTRACT, 3, Type.CASTIGO,
                    "Significa que resta tres posiciones"));
            prizePenaltyStack.push(new PrizePenalty(Operation.ADD, 0, Type.NO_AFECTA,
                    "Significa que se queda en la posición actual"));
            prizePenaltyStack.push(new PrizePenalty(Operation.EQUAL, 1, Type.CASTIGO,
                    "Significa que se debe ir a la posición 1"));
        }
    }

    /**
     * Removes a player from the ready player queue by their name.
     *
     * @param playerName The name of the player to be removed.
     * @return The name of the player removed from the queue, or null if the player was not found.
     *
     * @author Lorenzo
     */
    public String removePlayerFromQueue(String playerName) {
        String removedPlayer = readyPlayerQueue.poll(playerName);
        if (removedPlayer != null) {
            System.out.println("Player " + removedPlayer + " has left the game");
        }
        return removedPlayer;
    }

    /**
     * Lists the current players in the game queue.
     *
     * @author Lorenzo
     */
    public void listCurrentPlayers(){
        System.out.println(readyPlayerQueue);
    }

    /**
     * Lists the remaining prizes and penalties in {@link PrizePenaltyStack}
     *
     * @author Lorenzo
     */
    public void remainingPrizePenalty(){
        System.out.println("Remaining prices and penalties: "+prizePenaltyStack.toString());
    }

}
