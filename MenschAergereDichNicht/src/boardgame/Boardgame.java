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
        for (int playerNr = 0; playerNr < 4; playerNr++) {
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
        return returnString;
    }

}
