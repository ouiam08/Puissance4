package com.example.puissance4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JetonTest {

    @Test
    public void shouldCreateToken(){
        Token token = new Token();
        Assertions.assertEquals(token.getColor(),"red");
    }

    @Test
    public void shouldCreateGreenToken(){
        Token token = new Token("green");
        Assertions.assertEquals(token.getColor(),"green");
    }


}
