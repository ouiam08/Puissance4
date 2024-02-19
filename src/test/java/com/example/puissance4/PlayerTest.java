package com.example.puissance4;

import com.example.puissance4.exceptions.FullColumnException;
import com.example.puissance4.exceptions.NullGridException;
import com.example.puissance4.exceptions.NullTokenException;
import com.example.puissance4.exceptions.OutOfGridRangeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void shouldCreatePlayer() throws NullTokenException, NullGridException {
        Token token =  new Token("red");
        Player player =  new Player(token, new Grid());
        Assertions.assertEquals(player.getToken(),token);
    }

    @Test
    public void shouldThrowExceptionIfTokenIsNull(){
        Assertions.assertThrows(NullTokenException.class,()->new Player(null, new Grid()));
    }

    @Test
    public void shouldThrowExceptionIfGridIsNull(){
        Assertions.assertThrows(NullGridException.class,()->new Player(new Token(), null));
    }

    @Test
    public void shouldPlayInSpecificColumn() throws NullTokenException, OutOfGridRangeException, FullColumnException, NullGridException {
        Grid grid = new Grid();
        Token token = new Token("yellow");
        Player player =  new Player(token,grid);
        player.play(2);
        Assertions.assertEquals(grid.getGrid().get(0).get(1),token);
    }


}
