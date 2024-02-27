package com.example.puissance4;

import com.example.puissance4.exceptions.DifferentGridException;
import com.example.puissance4.exceptions.FullColumnException;
import com.example.puissance4.exceptions.OutOfGridRangeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;

public class Game {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private final Grid grid;
    private final Arbiter arbiter;

    public Game() throws NullPointerException, DifferentGridException {
        this.grid = new Grid();
        Player player1 = new Player(new Token("red"), grid);
        Player player2 = new Player(new Token("green"), grid);
        this.arbiter = new Arbiter(player1, player2, grid);
    }

    public void play(int column) {
        try {
            arbiter.getCurrentPlayer().play(column);
            Displayer.displayGrid(grid);
            if (arbiter.getWinner() != null) {
                logger.info("Player {} wins!", arbiter.getWinner().token().getColor());
                return;
            }
            arbiter.switchPlayer();
            logger.info("Next player: {}", arbiter.getCurrentPlayer().token().getColor());
        } catch (OutOfGridRangeException | NullPointerException | FullColumnException | InputMismatchException e) {
            logger.info("Invalid move. Try again.");
        }
    }

    public void startGame() {
        logger.info("Welcome to Puissance 4!");
        Displayer.displayGrid(grid);
        logger.info("Player {} starts the game.", arbiter.getCurrentPlayer().token().getColor());
    }

    public boolean isGameOver(){
        if(grid.isGridEntirelyFull()){
            return true;
        }else return arbiter.getWinner() != null;
    }

    public String getCurrentPlayerColor() {
        return arbiter.getCurrentPlayer().token().getColor();
    }
}
