package com.example.puissance4;

import com.example.puissance4.exceptions.DifferentGridException;
import com.example.puissance4.exceptions.NullGridException;
import com.example.puissance4.exceptions.NullPlayerException;

public class Arbiter {
    private final Player player1;
    private final Player player2;
    private final Grid grid;
    private Player currentPlayer;


    public Arbiter(Player player1, Player player2, Grid grid) throws NullPlayerException, NullGridException, DifferentGridException {
        if(player1 == null || player2 == null){
            throw new NullPlayerException();
        }
        if (grid ==  null)
        {
            throw new NullGridException();
        }
        if(player1.getGrid() != player2.getGrid() || player1.getGrid() != grid || grid != player2.getGrid() ){
            throw new DifferentGridException();
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
        if(grid.isTokenWinner(player1.getToken())){
            return player1;
        } else if(grid.isTokenWinner(player2.getToken())) {
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
