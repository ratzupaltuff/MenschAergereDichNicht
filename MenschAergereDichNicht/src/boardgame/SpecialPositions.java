package boardgame;
/**
 * @author ratzupaltuff
 *
 */
enum SpecialPositions {
    /**
     * 
     */
    start(-1, "S"), a(-5, "A"), b(-4, "B"), c(-3, "C"), d(-2, "D");
    private int positionNumber;
    private String representativeLetter;

    private SpecialPositions(int positionNumber, String representativeLetter) {
        this.positionNumber = positionNumber;
        this.representativeLetter = representativeLetter;
    }

    
    /**
     * @return first letter of color string (in caps)
     */
    protected String getLetterCapsString() {
        return representativeLetter;
    }

    /**
     * @param position position which should be converted to the string
     * @return the string representing the position
     */
    protected static String intLowerThanOneToSpecialCharacter(int position) {
        if (start.positionNumber == position) {
            return start.getLetterCapsString();
        } else if (a.positionNumber == position) {
            return a.getLetterCapsString();
        } else if (b.positionNumber == position) {
            return b.getLetterCapsString();
        } else if (c.positionNumber == position) {
            return c.getLetterCapsString();
        } else { // if (d.positionNumber == position) is the only case where this is called
            return d.getLetterCapsString();
        }
    }
}