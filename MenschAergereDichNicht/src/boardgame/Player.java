package boardgame;

public class Player {
    private Token[] tokens = new Token[4];
    private PlayerColors color;

    enum PlayerColors {
        /**
         * 
         */
        red(0, 0), blue(1, 10), yellow(2, 20), green(3, 30);
        private int playerNr;
        private int offset;

        private PlayerColors(int playerNr, int offset) {
            this.playerNr = playerNr;
            this.offset = offset;
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
     * @return int, which identicates the offset on the gameboard
     */
    public int getOffset() {
        return color.offset;
    }

}
