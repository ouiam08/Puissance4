package com.example.puissance4;

import com.example.puissance4.exceptions.DifferentGridException;
import com.example.puissance4.exceptions.NullGridException;
import com.example.puissance4.exceptions.NullPlayerException;
import com.example.puissance4.exceptions.NullTokenException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Puissance4Application {

    public static void main(String[] args) {
        Game game;
        try {
            game = new Game();
            game.startGame();
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.print("Player " + game.getCurrentPlayerColor() + ", enter your move (1-7): ");
                int column = scanner.nextInt();
                game.play(column);
            } while (!game.isGameOver());
            scanner.close();
        } catch (NullTokenException | NullGridException | NullPlayerException | DifferentGridException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
