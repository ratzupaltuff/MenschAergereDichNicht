package boardgame;

/**
 * @author ratzupaltuff
 *
 */
public class Token {
    // normal fields from 0-39 and from lowest to highest the special fields are -4
    // to -1
    private byte relativePosition;

    /**
     * @param relativePosition initial position of the token
     */
    public Token(byte relativePosition) {
        this.relativePosition = relativePosition;
    }

    /**
     * 
     */
    public Token() {
        this.relativePosition = -1;
    }

    /**
     * @return position of token
     */
    public byte getPosition() {
        return relativePosition;
    }

    /**
     * @param relativePosition set the token to this position
     */
    public void setPosition(byte relativePosition) {
        this.relativePosition = relativePosition;
    }

    /**
     * @return representative caps letter or an empty string, if the input is a normal number
     */
    public String getSpecialRepresentativeCharacter() {
        if (relativePosition <= 0) {
            return SpecialPositions.intLowerThanOneToSpecialCharacter(relativePosition);
        }
        return "";
    }
    
    /**
     * @param relativePosition position which should be translated to the representative string
     * @return representative string if value is less than 0 else empty string
     */
    public static String getSpecialRepresentativeCharacter(int relativePosition) {
        if (relativePosition <= 0) {
            return SpecialPositions.intLowerThanOneToSpecialCharacter(relativePosition);
        }
        return "";
    }

    /**
     * 
     */
    public void resetPosition() {
        relativePosition = 0;
    }

}
