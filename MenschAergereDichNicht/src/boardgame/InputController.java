package boardgame;

import edu.kit.informatik.*;

public class InputController {

    /**
     * 
     */
    public void begin() {
        String input;
        do {
            input = Terminal.readLine();
            inputParser(input);
        } while (!input.contentEquals("quit"));
    }

    /**
     * @param input input String
     */
    public void inputParser(String input) {
        String[] inputArrayStrings = input.split(" ");
        
        switch (inputArrayStrings[0]) {
        
        case "start":
            start(inputArrayStrings);
            break;
        default: Terminal.printError("Error, command not found");
        
        }
    }
    
    /**
     * @param inputArrayStrings input
     */
    public void start(String[] inputArrayStrings) {
        Terminal.printLine(inputArrayStrings.length);
        Terminal.printLine("OK");
    }

}
