package boardgame;

public class Player {
    private Token[] tokens = new Token[4];
    private PlayerColors color;

    enum PlayerColors {
        /**
         * 
         */
        red(0, 0, "R"), blue(1, 10, "B"), yellow(2, 20, "Y"), green(3, 30, "G");
        private int playerNr;
        private int offset;
        private String colorFirstLetterCapsString;

        private PlayerColors(int playerNr, int offset, String colorFirstLetterCapsString) {
            this.playerNr = playerNr;
            this.offset = offset;
            this.colorFirstLetterCapsString = colorFirstLetterCapsString;
        }
        
        /**
         * @return first letter of color string (in caps)
         */
        public String getFirstLetterCapsString() {
            return colorFirstLetterCapsString;
        }
    }

    /**
     * @param nr number of player
     */
    public Player(int nr) {
        Token[] tokens = {new Token(), new Token(), new Token(), new Token() };
        this.tokens = tokens;
        // this.color = new PlayerColors(PlayerColors.)
        switch (nr) {
        case 0:
            color = PlayerColors.red;
        case 1:
            color = PlayerColors.blue;
        case 2:
            color = PlayerColors.yellow;
        case 3:
            color = PlayerColors.green;
        default: // error
        }
    }

    /**
     * @return tokens of player
     */
    public Token[] getTokens() {
        return tokens;
    }

    /**
     * @param tokens set tokens of player to this value
     */
    public void setTokens(Token[] tokens) {
        this.tokens = tokens;
    }

    /**
     * @return number of player
     */
    public int getPlayerNr() {
        return color.playerNr;
    }

    /**
     * @param field field to look for tokens
     * @return true if there is a token at the field, else false
     */
    public boolean isTokenAtField(int field) {
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
    public void setToken(int tokenNr, int globalPosition) {
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
    public void resetTokenAtPosition(int globalField) {
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
        return isTokenAtField(getFieldPlusNumber(token, steps));
    }

    /**
     * @return int, which identicates the offset on the gameboard
     */
    public int getOffset() {
        return color.offset;
    }

    /**
     * @param startingField value between -4 and 39
     * @param steps         value between 1 and 6
     * @return value 0 for no possibility to move, else return position of token at
     *         startingFiled going x steps
     */
    public int getFieldPlusNumber(int startingField, int steps) {
        if (startingField >= 0) {
            if (startingField + steps <= 39) {
                return startingField + steps;
            } else if (startingField + steps <= 43) {
                return startingField + steps - 44;
            } else {
                return 0; // can't be reached, because steps is only called with values higher than zero
            }
        } else {
            if (startingField + steps < 0) {
                return startingField + steps;
            } else {
                return 0;
            }
        }
    }

    /**
     * @param token token to be checked
     * @param steps steps to be moved/checked
     * @return empty string if move isn't possible else the string of the move
     */
    public String checkIfTokenCanBeMovedAndReturnMoveString(int token, int steps) {
        if (canTokenMoveBy(token, steps)) {
            int positionOfCurrentToken = getTokens()[token].getPosition();
            return positionOfCurrentToken + "-" + getFieldPlusNumber(positionOfCurrentToken, steps);
        } else {
            return "";
        }
    }

}
