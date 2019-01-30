package boardgame;
/**
 * @author ratzupaltuff
 *
 */
enum PlayerColors {
    /**
     *  the four players with color as their names, number as their first value,
     *  value of the starting field as their second value and their first letter in Uppercase
     */
    red(0, 0, "R"), blue(1, 10, "B"), green(2, 20, "G"), yellow(3, 30, "Y");

    private int playerNr;
    private int offset;
    private String colorFirstLetterCapsString;

    /**
     * private constructer for the enum
     * @param playerNr playerNr (0 to 3)
     * @param offset value of the starting field of the player
     * @param colorFirstLetterCapsString first letter of the colorstring in caps
     */
    private PlayerColors(int playerNr, int offset, String colorFirstLetterCapsString) {
        this.playerNr = playerNr;
        this.offset = offset;
        this.colorFirstLetterCapsString = colorFirstLetterCapsString;
    }

    /**
     * getter for offset
     * @return offset of the player on the board
     */
    protected int getOffset() {
        return offset;
    }

    /**
     * getter for the playerNr
     * @return playerNr (0 to 3)
     */
    protected int getPlayerNr() {
        return playerNr;
    }

    /**
     * @return first letter of color string (in caps)
     */
    protected String getFirstLetterCapsString() {
        return colorFirstLetterCapsString;
    }
}