package boardgame;

enum SpecialPositions {
    /**
     * 
     */
    start(0, "S"), a(-4, "A"), b(-3, "B"), c(-2, "C"), d(-1, "D");
    private int positionNumber;
    private String representativeLetter;

    private SpecialPositions(int positionNumber, String representativeLetter) {
        this.positionNumber = positionNumber;
        this.representativeLetter = representativeLetter;
    }

    // TODO: 1. better to use getters in enum, or to use the value directly?
    /**
     * @return first letter of color string (in caps)
     */
    public String getLetterCapsString() {
        return representativeLetter;
    }

    /**
     * @param position postion which should be converted to the string
     * @return the string representing the position
     */
    public static String intLowerThanOneToSpecialCharacter(int position) {
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