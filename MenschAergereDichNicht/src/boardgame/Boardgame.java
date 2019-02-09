package boardgame;

/**
 * @author ratzupaltuff
 *
 */
public class Boardgame {

    private Board board;

    /**
     * default constructor which creates a new boardgame instance
     */
    public Boardgame() {
        board = new Board();
    }

    /**
     * @param startArray values for the tokens for all 4 players
     */
    public Boardgame(int[][] startArray) {
        board = new Board();
        for (int playerNr = 0; playerNr < 4; playerNr++) { //4 sollte wieder Konstante sein
            for (int tokenNr = 0; tokenNr < 4; tokenNr++) {
                board.getPlayer(playerNr).setToken(tokenNr, startArray[playerNr][tokenNr]);
            }
        }
    }

    /**
     * @param numberOfSteps the value the dice shows, (from 1 to 6)
     * @return positions of player tokens
     */
    public String returnPossibleMoveForPlayer(int numberOfSteps) {
        return board.returnPositionsForPlayer(numberOfSteps);
    }

    /**
     * @return string
     */
    public String toString() {
        String returnString;
        returnString = board.getPlayer(0).toString();
        for (int playerNr = 1; playerNr < 4; playerNr++) {
            returnString += "\n" + board.getPlayer(playerNr).toString();
        }
        returnString += "\n" + PlayerColors.getPlayerByNr(board.getTurnsPlayerId());
        return returnString;
    }

    /**
     * @return true if you can roll the dice
     */
    public boolean isRollable() {
        if (board.getLastRoll() == 0) { //Warum == 0???
            return true;
        }
        return false;
    }

    /**
     * @param tokenPosition token to move
     * @return whether move was successful
     */
    public boolean tryToMoveTokenToField(int tokenPosition) {
        return false;
    }

    /**
     * @return player Nr (0-3)
     */
    public int getCurrentPlayerNr() {
        return board.getTurnsPlayerId();
    }

    /**
     * @return stringarray for valid inputs
     */
    protected String[] getCurrentPossibleMoves() {
        int currentPlayerId = board.getTurnsPlayerId();
        int currentDice = board.getLastRoll();
        String[] returnArray = new String[4];
        for (int tokenNr = 0; tokenNr < 4; tokenNr++) {
            returnArray[tokenNr] = board.getPlayer(currentPlayerId).checkIfTokenCanBeMovedAndReturnMoveString(tokenNr,
                    currentDice).substring(0, 2);
        }

        return returnArray;
    }

}
