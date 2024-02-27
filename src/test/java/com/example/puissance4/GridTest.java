package com.example.puissance4;

import com.example.puissance4.exceptions.FullColumnException;
import com.example.puissance4.exceptions.OutOfGridRangeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GridTest {

    @Test
    public void shouldCreateGrid(){
        Grid grid = new Grid();
        Assertions.assertEquals(6, grid.getNumRows());
        Assertions.assertEquals(7, grid.getNumColumns());
    }

    @Test
    public  void shouldThrowExceptionIfColumnIsOutOfRange(){
        Grid grid = new Grid();
        assertThrows(OutOfGridRangeException.class, () -> grid.insert(9, new Token()));
        assertThrows(OutOfGridRangeException.class, () -> grid.insert(-2, new Token()));
    }

    @Test
    public  void shouldThrowExceptionIfColumnIsNull(){
        Grid grid = new Grid();
        assertThrows(OutOfGridRangeException.class, () -> grid.insert(null, new Token()));
    }

    @Test
    public  void shouldThrowExceptionIfTokenIsNull(){
        Grid grid = new Grid();
        assertThrows(NullPointerException.class, () -> grid.insert(1, null));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7})
    public void shouldInsertTokenInFirstRowOfSpecifiedColumn(int column) throws OutOfGridRangeException, NullPointerException, FullColumnException {
        Grid grid = new Grid();
        Token token = new Token("green");
        grid.insert(column, token);
        Assertions.assertEquals(token, grid.getGridlist().get(0).get(column-1));
    }
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7})
    public void shouldThrowExceptionIfColumnIsFull(int column) throws OutOfGridRangeException, NullPointerException,
            FullColumnException {
        Grid grid = new Grid();
        Token token = new Token("green");
        for(int i=0;i<6;i++) {
            grid.insert(column, token);
        }
        Assertions.assertThrows(FullColumnException.class,()->grid.insert(column, token));
    }
    @Test
    public void shouldReturnFalseIfGridIsNotFull() {
        Grid grid = new Grid();
        Assertions.assertFalse(grid.isGridEntirelyFull());
    }

    @Test
    public void shouldReturnFalseWhenGridIsPartiallyFilled() throws OutOfGridRangeException, NullPointerException, FullColumnException {
        Grid grid = new Grid();
            grid.insert(1, new Token("red"));
            grid.insert(2, new Token("green"));
        Assertions.assertFalse(grid.isGridEntirelyFull());

    }
}
