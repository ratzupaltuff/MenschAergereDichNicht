package boardgame;

public class Board {
    private Player[] players;

    /**
     * 
     */
    public Board() {
        Player[] players = {new Player(0), new Player(1), new Player(2), new Player(3) };
        this.players = players;
    }

    private boolean isTokenOnField(int field) {
        for (Player player : players) {
            for (Token token : player.getTokens()) {
                if (token.getPosition() == field) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isTokenOnFieldforPlayer(int globalField, int playerNr) {
        for (Player player : players) {
            if (player.getPlayerNr() == playerNr) {
                return player.isTokenAtField(globalField);
            }
        }
        return false;
    }
    
    /**
     * @param playerNr which player should be returned?
     * @return the player that matches the playerNr
     */
    public Player getPlayer(int playerNr) {
        return players[playerNr];
    }

    /**
     * @param globalPosition field of player
     * @param playerNr       playerNr
     * @param tokenNr        number of token to be moved
     * @return whether move was successfully
     */
    public boolean moveTokenOfPlayerToField(int globalPosition, int playerNr, int tokenNr) {
        if (!isTokenOnFieldforPlayer(globalPosition, playerNr)) {
            players[playerNr].setToken(tokenNr, globalPosition);
            return true;
        }
        return false;
    }

}
