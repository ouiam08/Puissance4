package com.example.puissance4;

import com.example.puissance4.exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ArbiterTest {

    @Test
    public void  shouldCreateArbiter() throws NullPointerException, DifferentGridException {
        Grid grid = new Grid();
        Player player1= new Player(new Token("red"),grid);
        Player player2= new Player(new Token("green"),grid);
        Arbiter arbiter = new Arbiter(player1,player2,grid);
        Assertions.assertEquals(arbiter.getPlayer1(),player1);
        Assertions.assertEquals(arbiter.getPlayer2(),player2);
        Assertions.assertEquals(arbiter.getGrid(),grid);
    }

    @Test
    public void shouldThrowExceptionIfPlayersAreNull(){
        Grid grid = new Grid();
        Assertions.assertThrows(NullPointerException.class,
                ()->new Arbiter(null,null,grid));
        Assertions.assertThrows(NullPointerException.class,
                ()->new Arbiter(new Player(new Token("green"),grid),null,grid));
        Assertions.assertThrows(NullPointerException.class,
                ()->new Arbiter(null,new Player(new Token("red"),grid),grid));
    }

    @Test
    public void shouldThrowExceptionIfGridIsNull(){
        Grid grid = new Grid();
        Assertions.assertThrows(NullPointerException.class,
                ()->new Arbiter(new Player(new Token("red"),grid),new Player(new Token("red"),grid),null));
    }

    @Test
    public void shouldThrowExceptionIfPlayersAndArbiterDontHaveTheSameGrid() {
        Grid grid1 = new Grid();
        Grid grid2 = new Grid();
        Assertions.assertThrows(DifferentGridException.class,
                ()-> new Arbiter(new Player(new Token("red"),grid1),new Player(new Token("red"),grid2),new Grid()));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7})
    public void shouldReturnTheWinnerIfFourTokensAreInTheSameColumn(int column) throws NullPointerException, DifferentGridException, OutOfGridRangeException, FullColumnException {
        Grid grid = new Grid();
        Player player1= new Player(new Token("red"),grid);
        Player player2= new Player(new Token("green"),grid);
        Arbiter arbiter = new Arbiter(player1,player2,grid);
        player1.play(column);
        player2.play(column);
        player1.play(column);
        player1.play(column);
        player1.play(column);
        player1.play(column);
        Assertions.assertEquals(player1,arbiter.getWinner());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    public void shouldReturnTheWinnerIfFourTokensAreInTheSameRow(int column) throws NullPointerException, DifferentGridException, OutOfGridRangeException, FullColumnException {
        Grid grid = new Grid();
        Player player1= new Player(new Token("red"),grid);
        Player player2= new Player(new Token("green"),grid);
        Arbiter arbiter = new Arbiter(player1,player2,grid);
        player2.play(column);
        player2.play(column+1);
        player2.play(column+2);
        player2.play(column+3);
        Assertions.assertEquals(player2,arbiter.getWinner());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void shouldReturnTheWinnerIfFourTokensAreInBottomLeftToTopRightDiagonal(int column) throws NullPointerException, DifferentGridException, OutOfGridRangeException, FullColumnException {
        Grid grid = new Grid();
        Player player1= new Player(new Token("red"),grid);
        Player player2= new Player(new Token("green"),grid);
        Arbiter arbiter = new Arbiter(player1,player2,grid);
        player1.play(column);
        player2.play(column+1);
        player1.play(column+1);
        player2.play(column+2);
        player1.play(column+2);
        player2.play(column+3);
        player1.play(column+2);
        player2.play(column+3);
        player1.play(column+3);
        player2.play(column+4);
        player1.play(column+3);



        Assertions.assertEquals(player1,arbiter.getWinner());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void shouldReturnTheWinnerIfFourTokensAreInTopLeftToBottomRightDiagonal(int column) throws NullPointerException, DifferentGridException, OutOfGridRangeException, FullColumnException {
        Grid grid = new Grid();
        Player player1= new Player(new Token("red"),grid);
        Player player2= new Player(new Token("green"),grid);
        Arbiter arbiter = new Arbiter(player1,player2,grid);

        player1.play(column);
        player2.play(column);
        player1.play(column);
        player2.play(column);
        player1.play(column+1);
        player2.play(column+1);
        player2.play(column+1);
        player2.play(column+2);
        player2.play(column+2);
        player2.play(column+3);

        Assertions.assertEquals(player2, arbiter.getWinner());
    }

    @Test
    public void shouldAlternatePlayersAfterMoves() throws NullPointerException, DifferentGridException, OutOfGridRangeException, FullColumnException {
        Grid grid = new Grid();
        Player player1 = new Player(new Token("red"), grid);
        Player player2 = new Player(new Token("green"), grid);
        Arbiter arbiter = new Arbiter(player1, player2, grid);

        arbiter.getCurrentPlayer().play(1);
        arbiter.switchPlayer();
        Assertions.assertEquals(player2, arbiter.getCurrentPlayer());

        arbiter.getCurrentPlayer().play(2);
        arbiter.switchPlayer();
        Assertions.assertEquals(player1, arbiter.getCurrentPlayer());
    }

}
