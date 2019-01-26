package main;

import boardgame.InputController;

public class Main {

    /**
     * @param args program parameter, unused
     */
    public static void main(String[] args) {
         
        InputController gameController = new InputController();
        gameController.begin();
    }

}
