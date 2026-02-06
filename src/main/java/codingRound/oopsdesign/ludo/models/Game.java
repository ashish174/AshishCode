package codingRound.oopsdesign.ludo.models;

import codingRound.oopsdesign.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game {
    Long id;
    private Player[] players;
    LudoBoard board;
    private Dice dice;
    private Player currentTurn;

    public static Logger logger = LoggerFactory.getLogger(Game.class);

    private static final Integer DICE_MAX_VALUE = 6;

    public Game(Person[] people) {
        try {
            validateNumOfPlayers(people.length);
            id = CommonUtils.generateUniqueIdentifier();
            addPlayers(people);
            assignTeamToPlayers();
            dice = new Dice(DICE_MAX_VALUE);
            board = new LudoBoard(CommonUtils.generateUniqueIdentifier());
        } catch (Exception e) {
            logger.error("Error initializing the game");
            e.printStackTrace();
        }

    }

    public void validateNumOfPlayers(int numOfPlayers) throws Exception {
        if(numOfPlayers > 4 ){
            logger.error("Number of player exceeds 4");
            throw new Exception("Number of Players Exceed 4");
        }
    }

    private void addPlayers(Person[] people) {
        for(int i=0; i<=people.length; i++){
            Player player = new Player(people[i]);
            players[i]=player;
        }
    }

    public void initiateGame(){

    }

    public void assignTeamToPlayers(){
        for(int i=0; i<=players.length; i++){
            Colour colour = Colour.fromValue(i);
            players[i].setColour(colour);
            players[i].setGotis(intializeGotis(colour));
        }
        currentTurn = players[Colour.YELLOW.val];
    }

    public Goti[] intializeGotis(Colour colour){
        Goti[] gotis = new Goti[4];
        for(int i=0; i<gotis.length; i++){
            gotis[i].setColour(colour);
            gotis[i].setStatus(GotiStatus.DEAD);
            gotis[i].setLocation(null);
        }
        return gotis;
    }

    void playTurn(){
        //Run Dice
        int steps = dice.throwDice();
        Goti gotiToMove = findBestGotiToMove(currentTurn, steps);
        //isMoveValid(currentTurn, steps);
        //Validate Move
        //move
        //check If Winner
    }

    private Goti findBestGotiToMove(Player player, int steps) {
        Goti[] gotis = player.getGotis();
        for(int i=0; i<gotis.length; i++){
            if(gotis[i].getStatus()!=GotiStatus.COMPLETED){
                isMoveValid(gotis[i], steps);
            }
        }
        return null;
    }


    boolean isMoveValid(Goti goti, int steps){
        getDestinationCell(goti, steps);
        return true;
    }

    private void getDestinationCell(Goti goti, int steps) {
        Cell srcLocation = goti.getLocation();
        Colour gotiColour = goti.getColour();
        int colourVal = gotiColour.val;
        Cell tgtLocation;
        //For dead Goti needing 6
        if(srcLocation==null){
            if(steps==6){
                tgtLocation = board.getCells()[gotiColour.val][0];
            } else {
                tgtLocation = null;
            }
        }
        // For Alive Gotis
        else if (srcLocation.getType()==CellType.HOME){
            int currNum = srcLocation.getNum();
            //int finalNum = currNum +

        }


    }

    void move(int number, Cell currentPos){

    }

}
