package boardgame;

public class Board {
    private Player[] players;

    /**
     * 
     */
    public Board() {
        Player[] players = {new Player(0), new Player(1), new Player(2), new Player(3) };
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

}
