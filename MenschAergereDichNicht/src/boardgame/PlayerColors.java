package boardgame;

/**
 * @author ratzupaltuff
 *
 */
//Ich würde als Name eher PlayerInformation wählen, da du ja nicht nur die Farbe speicherst
enum PlayerColors {
    /**
     * the four players with color as their names, number as their first value,
     * value of the starting field as their second value and their first letter in
     * Uppercase
     */
    RED(0, 0, "R", "red"), BLUE(1, 10, "B", "blue"), GREEN(2, 20, "G", "green"), YELLOW(3, 30, "Y", "yellow");

    private int playerNr;
    private int offset;
    private String colorFirstLetterCapsString;
    private String name;

    /**
     * private constructer for the enum
     * 
     * @param playerNr                   playerNr (0 to 3)
     * @param offset                     value of the starting field of the player
     * @param colorFirstLetterCapsString first letter of the colorstring in caps
     */
    private PlayerColors(int playerNr, int offset, String colorFirstLetterCapsString, String name) {
        this.playerNr = playerNr;
        this.offset = offset;
        this.colorFirstLetterCapsString = colorFirstLetterCapsString;
        this.name = name;
    }

    /**
     * getter for offset
     * 
     * @return offset of the player on the board
     */
    protected int getOffset() {
        return offset;
    }

    /**
     * @param playerNr number of player
     * @return  name of player
     */
    protected static String getPlayerByNr(int playerNr) {
        for (PlayerColors e : PlayerColors.values()) {
            if (playerNr == e.playerNr)
                return e.name;
        }
        return null;
    }

    /**
     * getter for the playerNr
     * 
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