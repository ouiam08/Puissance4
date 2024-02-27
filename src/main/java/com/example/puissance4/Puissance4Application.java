package com.example.puissance4;

import com.example.puissance4.exceptions.DifferentGridException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Puissance4Application {

    private static final Logger logger = LoggerFactory.getLogger(Puissance4Application.class);

    public static void main(String[] args) {
        try {
            playGame();
        } catch (DifferentGridException e) {
            logger.info("An error occurred: {}", e.getMessage());
        }
    }

    private static void playGame() throws DifferentGridException {
        try (Scanner scanner = new Scanner(System.in)) {
            Game game = new Game();
            game.startGame();

            while (!game.isGameOver()) {
                String playerColor = game.getCurrentPlayerColor();
                logger.info("Player {}, enter your move (1-7): ", playerColor);

                try {
                    int column = scanner.nextInt();
                    game.play(column);
                } catch (InputMismatchException  e) {
                    logger.info("Invalid input. Please enter a number between 1 and 7.");
                    scanner.nextLine();
                }
            }
            logger.info("Game over!");
        }
    }
}
