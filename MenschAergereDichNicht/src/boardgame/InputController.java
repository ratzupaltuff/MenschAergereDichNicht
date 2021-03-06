package boardgame;

/**
 * @author ratzupaltuff
 *
 */
import edu.kit.informatik.*;

public class InputController {
    private Boardgame game;

    /**
     * doesn't terminate until "quit" is typed, calls inputParser on every Input
     */
    public void begin() {
        String input;
        do {
            input = Terminal.readLine();
            inputParser(input);
        } while (!input.contentEquals("quit"));
    }

    /**
     * determines which method should be called, depending on the command entered
     * 
     * @param input input String
     */
    public void inputParser(String input) {
        String[] inputArrayStrings = input.split(" ");

        if (inputArrayStrings.length > 0) { // catch empty strings (with spaces)
            switch (inputArrayStrings[0]) {

            case "start":
                start(inputArrayStrings);
                break;

            case "roll":
                roll(inputArrayStrings);
                break;

            case "move":

                break;

            case "print":
                print(inputArrayStrings);
                break;
            default:
                Terminal.printError("command not found");

            }
        } else {
            Terminal.printError("command should contain text");
        }
    }

    /**
     * @param inputArrayString command
     */
    public void print(String[] inputArrayString) {
        if (isGameStarted()) {
            if (inputArrayString.length == 1) {
                Terminal.printLine(game.toString());
            } else {
                Terminal.printError("the print command needs no parameter, try again");
            }
        } else {
            Terminal.printError("game is not running");
        }
    }



    /**
     * @param inputArrayString input
     */
    //Die else Verzweigungen kann man sich sparen. Macht das Ganze lesbarer.
    //Nun zu der Exception, was meiner Meinung nach Geschmackssache ist.
    //Pro: Sollte man die Terminalklasse austauschen wollen, muss man das lediglich in der einen Methode machen,
    //      die die IllegalArgumentException abfängt
    //Contra: Exception sollte so früh wie möglich behandelt werden. Also kann man auf die Exception verzichten und direkt die Ausgabe machen.

    public void roll(String[] inputArrayString) throws IllegalArgumentException {
        if (isGameStarted()) {
            if (inputArrayString.length == 2) {
                throw new IllegalArgumentException("you have to specify one value");
                //oder
                Terminal.printError("you have to specify one value");
                return;
            }
            if (inputArrayString[1].matches("^[1-6]$")) {
                throw new IllegalArgumentException("you have to specify a value between one and six");
            }


                    if (game.isRollable()) {
                        String returnString = game.returnPossibleMoveForPlayer(Integer.parseInt(inputArrayString[1]));
                        if (!returnString.equals("")) { //Und warum könnte er leer sein?
                            Terminal.printLine(returnString);
                        }
                    } else {
                        Terminal.printError("you have already rolled the dice");
                    }
                } else {
                    Terminal.printError("you have to specify a value between one and six");
                }
            } else {
                Terminal.printError("you have to specify one value");
            }
        } else {
            Terminal.printError("game is not running");
        }
    }


    /**
     * @param inputArrayString input
     */
    public void roll(String[] inputArrayString) {
        if (isGameStarted()) {
            if (inputArrayString.length == 2) {
                if (inputArrayString[1].matches("^[1-6]$")) {
                    if (game.isRollable()) {
                        String returnString = game.returnPossibleMoveForPlayer(Integer.parseInt(inputArrayString[1]));
                        if (!returnString.equals("")) {
                            Terminal.printLine(returnString);
                        }
                    } else {
                        Terminal.printError("you have already rolled the dice");
                    }
                } else {
                    Terminal.printError("you have to specify a value between one and six");
                }
            } else {
                Terminal.printError("you have to specify one value");
            }
        } else {
            Terminal.printError("game is not running");
        }
    }

    /**
     * @return true if game is started
     */
    public boolean isGameStarted() {
        if (game != null) {
            return true;
        }
        return false;
    }

    /**
     * @param inputArrayStrings input
     */
    public void start(String[] inputArrayStrings) {
        if (inputArrayStrings.length == 2) {
            startWithParameters(inputArrayStrings[1]);

        } else if (inputArrayStrings.length == 1) {
            game = new Boardgame();
            Terminal.printLine("OK");
        } else {
            Terminal.printError("too many arguments");
        }
    }

    /**
     * @param parameters start parameters
     */
    public void startWithParameters(String parameters) {
        if (game == null) {
            String[] tokenOfPlayers = parameters.split(";");
            if (tokenOfPlayers.length == 4) {
                String[][] positionsOfTokenOfPlayerStrings = new String[4][4];
                for (int playerNr = 0; playerNr < 4; playerNr++) {
                    String[] playerTokenStrings = tokenOfPlayers[playerNr].split(",");
                    if (playerTokenStrings.length == 4) {
                        for (int i = 0; i < 4; i++) {
                            positionsOfTokenOfPlayerStrings[playerNr][i] = playerTokenStrings[i];
                        }
                    } else {
                        Terminal.printError("not exactly four positions of player " + (playerNr + 1) + " specified");
                        return;
                    }
                }
                int[][] startArray = parseStartString(positionsOfTokenOfPlayerStrings);
                if (startArray.length == 4) {
                    game = new Boardgame(startArray); // TODO: check if there are two players on the same field
                    Terminal.printLine("OK");
                } else {

                }
            } else {
                Terminal.printError("not entered starting positions for exactly four players");
            }
        } else {
            Terminal.printError("game is already running");
        }
    }

    /**
     * @param positionsOfTokens positions of tokens in an string array with length
     *                          16
     * @return parsed Start String
     */
    public int[][] parseStartString(String[][] positionsOfTokens) {
        int[][] parsedStartString = new int[4][4];
        for (int playerNr = 0; playerNr < 4; playerNr++) {
            for (int tokenNr = 0; tokenNr < 4; tokenNr++) {
                String currentString = positionsOfTokens[playerNr][tokenNr];
                if (currentString.matches("(^[0-9]$|^[1-3][0-9]$|^[A-DS][BGRY]$)")) {
                    if (currentString.matches("(^[0-9]$|^[1-3][0-9]$)")) {
                        parsedStartString[playerNr][tokenNr] = Integer.parseInt(currentString);
                    } else if (currentString.matches("[A-DS][BGRY]$")) {
                        switch (currentString.substring(currentString.length() - 1)) {
                        case "Y":
                            if (playerNr == 3) {
                                parsedStartString[playerNr][tokenNr] = specialFieldToValue(
                                        currentString.substring(0, 1));
                            } else {
                                return new int[][] { {} };
                            }
                            break;
                        case "R":
                            if (playerNr == 0) {
                                parsedStartString[playerNr][tokenNr] = specialFieldToValue(
                                        currentString.substring(0, 1));
                            } else {
                                return new int[][] { {} };
                            }
                            break;
                        case "G":
                            if (playerNr == 2) {
                                parsedStartString[playerNr][tokenNr] = specialFieldToValue(
                                        currentString.substring(0, 1));
                            } else {
                                return new int[][] { {} };
                            }
                            break;
                        case "B":
                            if (playerNr == 1) {
                                parsedStartString[playerNr][tokenNr] = specialFieldToValue(
                                        currentString.substring(0, 1));
                            } else {
                                return new int[][] { {} };
                            }
                            break;
                        default:
                            Terminal.printError(
                                    "invalid startposition value for player " + playerNr + " and token " + tokenNr);
                        }
                    } else {
                        Terminal.printError(
                                "One of the Values is neither a digit between 0 and 40 nor a String with the "
                                        + "first letter A-D or S and the Last letter one of the following: RBGY");
                    }
                }
            }
        }
        return parsedStartString;
    }

    /**
     * @param fieldString string which should be converted to internal
     *                    representation
     * @return value from -4 to 0 for internal usage
     */
    private int specialFieldToValue(String fieldString) {
        switch (fieldString) {
        case "A":
            return -5;
        case "B":
            return -4;
        case "C":
            return -3;
        case "D":
            return -2;
        case "S": // for visibility purposes
            return -1;
        default:
            return 0;
        }
    }

    private void move(String[] inputArrayString) {
        if (isGameStarted()) {
            if (inputArrayString.length == 2) {
                String currentString = inputArrayString[1];
                int internalValue;
                int playerNr = game.getCurrentPlayerNr();
                if (inputArrayString[1].matches("(^[0-9]$|^[1-3][0-9]$|^[A-DS][BGRY]$)")) {
                    if (currentString.matches("(^[0-9]$|^[1-3][0-9]$)")) {
                        internalValue = Integer.parseInt(currentString);
                    } else if (currentString.matches("[A-DS][BGRY]$")) {
                        String[] possibleMoveStrings = game.getCurrentPossibleMoves();
                        for (int tokenNr = 0; tokenNr < 4; tokenNr++) {
                            if(possibleMoveStrings[tokenNr].equals(currentString)) {
                                game.tryToMoveTokenToField(specialFieldToValue(currentString));
                            }
                        }
                    }
                } else {
                    Terminal.printError("not a valid value");
                }
            } else {
                Terminal.printError("not exactly one parameter specified");
            }
        } else {
            Terminal.printError("game not started");
        }
    }

}
