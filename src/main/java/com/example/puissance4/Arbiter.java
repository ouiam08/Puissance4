package com.example.puissance4;

import com.example.puissance4.exceptions.DifferentGridException;

public class Arbiter {
    private final Player player1;
    private final Player player2;
    private final Grid grid;
    private Player currentPlayer;


    public Arbiter(Player player1, Player player2, Grid grid) throws NullPointerException, DifferentGridException {
        if(player1 == null || player2 == null){
            throw new NullPointerException();
        }
        if (grid ==  null)
        {
            throw new NullPointerException();
        }
        if(player1.grid() != player2.grid() || player1.grid() != grid || player2.grid() != grid  ){
            throw new DifferentGridException("Only One Grid is allowed!");
        }
        this.grid=grid;
        this.player1=player1;
        this.player2=player2;
        this.currentPlayer = player1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Grid getGrid() {
        return grid;
    }

    public Player getWinner() {
        if(grid.isTokenWinner(player1.token())){
            return player1;
        } else if(grid.isTokenWinner(player2.token())) {
            return player2;
        }
        return null;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
}
