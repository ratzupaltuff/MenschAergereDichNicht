package boardgame;

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