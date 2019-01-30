package boardgame;

/**
 * @author ratzupaltuff
 *
 */
public class Board {
    private Player[] players;
    private int turnOfPlayerNumber;
    private int lastRoll;
    private int hasTurnsLeft;

    /**
     * 
     */
    public Board() {
        Player[] players = { new Player(0), new Player(1), new Player(2), new Player(3) };
        this.players = players;
        turnOfPlayerNumber = 0;
        lastRoll = 0;
        hasTurnsLeft = 1;
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
    protected Player getPlayer(int playerNr) {
        return players[playerNr];
    }

    /**
     * @param globalPosition field of player
     * @param playerNr       playerNr
     * @param tokenNr        number of token to be moved
     * @return whether move was successfully
     */
    private boolean moveTokenOfPlayerToField(int globalPosition, int playerNr, int tokenNr) {
        if (!isTokenOnFieldforPlayer(globalPosition, playerNr)) {
            players[playerNr].setToken(tokenNr, globalPosition);
            return true;
        }
        return false;
    }

    /**
     * 
     */
    private void endTurn() {
        if (lastRoll != 6) {
            if (hasTurnsLeft <= 1) {
                if (turnOfPlayerNumber < 3) {
                    turnOfPlayerNumber++;
                } else {
                    turnOfPlayerNumber = 0;
                }
                if (!hasThreeRolls()) {
                    hasTurnsLeft = 1;
                } else {
                    hasTurnsLeft = 3;
                }
            } else {
                hasTurnsLeft--;
            }
        } else {
            hasTurnsLeft = 1;
        }
        lastRoll = 0;
        // TODO: check if sombody has won
    }

    /**
     * @param numberOfSteps check for moves if the dice shows this number (from 1 to
     *                      6)
     * @return String with possible moves depending on the steps
     */
    protected String returnPositionsForPlayer(int numberOfSteps) {
        lastRoll = numberOfSteps;
        boolean thereWasAlreadyANullPosition = false;
        String returnString = "";
        returnString += players[turnOfPlayerNumber].checkIfTokenCanBeMovedAndReturnMoveString(0, numberOfSteps);
        if (players[turnOfPlayerNumber].getTokens()[0].getPosition() == -1) {
            thereWasAlreadyANullPosition = true;
        }
        for (int tokenNr = 1; tokenNr < 4; tokenNr++) {
            if (!thereWasAlreadyANullPosition) {

                returnString += "\n"
                        + players[turnOfPlayerNumber].checkIfTokenCanBeMovedAndReturnMoveString(tokenNr, numberOfSteps);

                thereWasAlreadyANullPosition = true;
            } else {
                if (!(players[turnOfPlayerNumber].getTokens()[tokenNr].getPosition() == -1)) {
                    returnString += "\n" + players[turnOfPlayerNumber]
                            .checkIfTokenCanBeMovedAndReturnMoveString(tokenNr, numberOfSteps);

                }

            }

        }
        return returnString;
    }

    /**
     * @return number of the player who is on the row (values from 0 to 3)
     */
    protected int getTurnsPlayerId() {
        return turnOfPlayerNumber;
    }

    /**
     * @return last value of the dice, 0 if the dice hasnt rolled this turn
     */
    protected int getLastRoll() {
        return lastRoll;
    }

    /**
     * @return yes if no token is on the field
     */
    protected boolean hasThreeRolls() {
        for (int tokenNr = 0; tokenNr < 4; tokenNr++) {
            if (players[turnOfPlayerNumber].getTokens()[tokenNr].getPosition() >= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param playerNr player to check if won
     * @return true if player has won, false else
     */
    protected boolean hasPlayerWon(int playerNr) {
        for (int tokenNr = 0; tokenNr < 4; tokenNr++) {
            if (players[playerNr].getTokens()[tokenNr].getPosition() >= -1) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * @return -1 if nobody won and the playernumber if somebody won
     */
    protected int whichPlayerHasWon() {
        for (int playerNr = 0; playerNr < 4; playerNr++) {
            if (hasPlayerWon(playerNr)) {
                return playerNr;
            }
        }
        return -1;
    }
}
