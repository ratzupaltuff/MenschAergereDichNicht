package boardgame;

/**
 * @author ratzupaltuff
 *
 */
public class Token {
    // normal fields from 0-39 and from lowest to highest the special fields are -4
    // to -1
    private byte position;

    /**
     * @param position initial position of the token
     */
    public Token(byte position) {
        this.position = position;
    }

    /**
     * 
     */
    public Token() {
        this.position = 0;
    }

    /**
     * @return position of token
     */
    public byte getPosition() {
        return position;
    }

    /**
     * @param position set the token to this position
     */
    public void setPosition(byte position) {
        this.position = position;
    }

    /**
     * @return representative caps letter or an empty string, if the input is a normal number
     */
    public String getSpecialRepresentativeCharacter() {
        if (position <= 0) {
            return SpecialPositions.intLowerThanOneToSpecialCharacter(position);
        }
        return "";
    }

    /**
     * 
     */
    public void resetPosition() {
        position = 0;
    }

}
