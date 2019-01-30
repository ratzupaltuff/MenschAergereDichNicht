package boardgame;

public class Boardgame {

    private Board board;

    /**
     *  default constructor which creates a new boardgame instance
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
     * @return positions of player tokens
     */
    public String returnPositionsForPlayer() {
        return board.returnPositionsForPlayer();
    }

}
