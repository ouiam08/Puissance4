package com.example.puissance4;

import com.example.puissance4.exceptions.FullColumnException;
import com.example.puissance4.exceptions.OutOfGridRangeException;

public record Player(Token token, Grid grid) {

    public Player {
        if (token == null) {
            throw new NullPointerException("Token should not be null.");
        }
        if (grid == null) {
            throw new NullPointerException("Grid should not be null.");
        }
    }

    public void play(Integer i) throws OutOfGridRangeException, NullPointerException, FullColumnException {
        grid.insert(i, this.token);
    }
}
