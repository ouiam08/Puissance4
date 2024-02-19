package com.example.puissance4;

import com.example.puissance4.exceptions.*;

public class Game {
    private final Grid grid;
    private final Arbiter arbiter;

    public Game() throws NullTokenException, NullGridException, NullPlayerException, DifferentGridException {
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
                System.out.println("Player " + arbiter.getWinner().getToken().getColor() + " wins!");
                return;
            }
            arbiter.switchPlayer();
            System.out.println("Next player: " + arbiter.getCurrentPlayer().getToken().getColor());
        } catch (OutOfGridRangeException | NullTokenException | FullColumnException e) {
            System.out.println("Invalid move. Try again.");
        }
    }

    public void startGame() {
        System.out.println("Welcome to Puissance 4!");
        Displayer.displayGrid(grid);
        System.out.println("Player " + arbiter.getCurrentPlayer().getToken().getColor() + " starts the game.");
    }
    public boolean isGameOver(){
        if(grid.isGridInterlyFull()){
            return true;
        }else return arbiter.getWinner() != null;
    }

    public String getCurrentPlayerColor() {
        return arbiter.getCurrentPlayer().getToken().getColor();
    }
}
