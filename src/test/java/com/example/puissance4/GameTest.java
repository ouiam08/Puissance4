package com.example.puissance4;

import com.example.puissance4.exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void shouldCreateGame() throws DifferentGridException,  NullPointerException {
        Game game = new Game();
        Assertions.assertNotNull(game);
    }

    @Test
    public void shouldStartGame() throws DifferentGridException, NullPointerException{
        Game game = new Game();
        game.startGame();
        Assertions.assertEquals("red", game.getCurrentPlayerColor());
    }

    @Test
    public void shouldBeGameOverWhenGridIsFull() throws  NullPointerException, DifferentGridException {
        Game game = new Game();
        for (int col = 1; col <= 7; col++) {
            for (int row = 1; row <= 6; row++) {
                game.play(col);
            }
        }
        Assertions.assertTrue(game.isGameOver());
    }

    @Test
    public void shouldBeGameOverWhenThereIsAWinner() throws DifferentGridException, NullPointerException {
        Game game = new Game();
            game.play(1);
            game.play(2);
            game.play(1);
            game.play(2);
            game.play(1);
            game.play(2);
            game.play(1);
        Assertions.assertTrue(game.isGameOver());
    }

    @Test
    public void shouldNotBeGameOverWhenGameIsNotOver() throws DifferentGridException, NullPointerException {
        Game game = new Game();
        Assertions.assertFalse(game.isGameOver());
    }
}
