package com.example.puissance4;

import com.example.puissance4.exceptions.FullColumnException;
import com.example.puissance4.exceptions.NullGridException;
import com.example.puissance4.exceptions.NullTokenException;
import com.example.puissance4.exceptions.OutOfGridRangeException;

public class Player {

    private final Token token;

    Grid grid;
    public Player(Token token, Grid grid) throws NullTokenException, NullGridException {
        if(token == null){
            throw new NullTokenException("Token should not be null.");
        }
        if(grid == null){
            throw new NullGridException("Grid should not be null.");
        }
        this.token=token;
        this.grid=grid;
    }

    public Token getToken() {
        return this.token;
    }
    public Grid getGrid() {
        return this.grid;
    }

    public void play(Integer i) throws OutOfGridRangeException, NullTokenException, FullColumnException {
        grid.insert(i,this.token);
    }
}
