package boardgame;

public class Player {
    private Token[] tokens = new Token[4];
    private PlayerColors color;

    /**
     * constructor which creates 4 new token and initializes them with values zero
     * writes the player color depending on the number which is transfered
     * 
     * @param playerNr number of player (from 0 to 3)
     */
    public Player(int playerNr) {
        Token[] tokens = {new Token(), new Token(), new Token(), new Token() };
        this.tokens = tokens;

        switch (playerNr) {
        case 0:
            color = PlayerColors.RED;
            break;
        case 1:
            color = PlayerColors.BLUE;
            break;
        case 2:
            color = PlayerColors.GREEN;
            break;
        default: // only called if playeriNr is 3
            color = PlayerColors.YELLOW;
        }
    }

    /**
     * @return tokens of player
     */
    protected Token[] getTokens() {
        return tokens;
    }

    /**
     * @param tokens set tokens of player to this value
     */
    private void setTokens(Token[] tokens) {
        this.tokens = tokens;
    }

    /**
     * @return number of player
     */
    protected int getPlayerNr() {
        return color.getPlayerNr();
    }

    /**
     * @param field field to look for tokens
     * @return true if there is a token at the field, else false
     */
    protected boolean isTokenAtField(int field) {
        for (Token token : tokens) {
            if (token.getPosition() == field) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param tokenNr        tokennumber
     * @param globalPosition position to set to
     */
    protected void setToken(int tokenNr, int globalPosition) {
        byte position = globalPositionToRelativePosition(globalPosition);
        tokens[tokenNr].setPosition(position);
    }

    /**
     * @param globalPosition
     * @return
     */
    private byte globalPositionToRelativePosition(int globalPosition) {
        int position;
        if (globalPosition > 0) {
            position = (globalPosition + getOffset()) % 40;
        } else if (globalPosition < 0) {
            position = globalPosition;
        } else {
            position = 0;
        }
        return (byte) position;
    }

    /**
     * @param relativePosition relative position to be converted (between -4 and 39)
     * @return global position
     */
    private String relativePositionToGlobalPosition(int relativePosition) {
        String globalPositionString;
        if (relativePosition >= 0) {
            globalPositionString = Integer.toString((relativePosition - getOffset()) % 40);
        } else {
            globalPositionString = "" + color.getFirstLetterCapsString();
        }
        return globalPositionString;
    }

    /**
     * @param globalField position, at which a token is, which should be resetted
     */
    private void resetTokenAtPosition(int globalField) {
        int localPosition = (globalField + getOffset()) % 40;
        for (Token token : tokens) {
            if (token.getPosition() == localPosition) {
                token.resetPosition();
            }
        }
    }

    /**
     * @param token token to be checked
     * @param steps steps to move to be checked
     * @return if token can move x steps
     */
    private boolean canTokenMoveBy(int token, int steps) {
        /*
         * if (tokens[token].getPosition() == -1) { if (steps != 6) { return false; }
         * else { return !isTokenAtField(getFieldPlusNumber(token, 1)); } } else {
         */
        if (tokens[token].getPosition() > -1) { // normal fields
            return !isTokenAtField(getFieldPlusNumber(token, steps));
        } else if ((tokens[token].getPosition() + steps) < -1) { // last four home fields
            return !isTokenAtField(getFieldPlusNumber(token, steps));
        } else if (tokens[token].getPosition() == -1) { // starting field
            if (steps == 6) {
                return !isTokenAtField(getFieldPlusNumber(token, steps));
            } else {
                return false;
            }
        } else { // if token is at a home field, and cannot move x steps
            return false;
        }

    }

    /**
     * getter for the offset of the this player on the board
     * 
     * @return int, which identicates the offset on the gameboard
     */
    private int getOffset() {
        return color.getOffset();
    }

    /**
     * method which determines on which field a token lands, if its moved by 5,
     * returns 0 if token cant be moved x steps
     * 
     * @param startingField value between -5 and 39
     * @param steps         value between 1 and 6
     * @return value -10 for no possibility to move, else return position of token
     *         at startingFiled going x steps
     */
    private int getFieldPlusNumber(int startingField, int steps) {
        if (startingField >= 0) {
            if (startingField + steps <= 39) {
                return startingField + steps;
            } else if (startingField + steps <= 43) {
                return startingField + steps - 44;
            } else {
                return -10; // can't be reached, because steps is only called with values higher than zero
            }
        } else if (startingField < -1) {
            if (startingField + steps < 0) {
                return startingField + steps;
            } else {
                return -10;
            }
        } else {
            if (steps == 6) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    /**
     * @param token token to be checked
     * @param steps steps to be moved/checked
     * @return empty string if move isn't possible else the string of the move
     */
    protected String checkIfTokenCanBeMovedAndReturnMoveString(int token, int steps) {
        String returnString = "";
        if (canTokenMoveBy(token, steps)) {
            int positionOfCurrentToken = getTokens()[token].getPosition();
            returnString = convertLocalPositionToGlobalString(positionOfCurrentToken) + "-";
            returnString += convertLocalPositionToGlobalString(getFieldPlusNumber(positionOfCurrentToken, steps));
        }
        return returnString;

    }

    private String convertLocalPositionToGlobalString(int localPosition) {
        if (localPosition >= 0) {
            return Integer.toString((localPosition + getOffset()) % 40);
        } else {
            return Token.getSpecialRepresentativeCharacter(localPosition) + color.getFirstLetterCapsString();
        }
    }
    
    /**
     * @return string
     */
    public String toString() {
        String returnString;
        returnString = convertLocalPositionToGlobalString(tokens[0].getPosition());
        for (int tokenNr = 1; tokenNr < 4; tokenNr++) {
            returnString += "," + convertLocalPositionToGlobalString(tokens[tokenNr].getPosition());
        }
        return returnString;
    }

}
