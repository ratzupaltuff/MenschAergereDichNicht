package boardgame;

/**
 * @author ratzupaltuff
 *
 */
public class Token {
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
     * 
     */
    public void resetPosition() {
        position = 0;
    }
    
    
}
