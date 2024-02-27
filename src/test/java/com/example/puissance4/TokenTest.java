package com.example.puissance4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TokenTest {

    @Test
    public void shouldCreateToken(){
        Token token = new Token();
        Assertions.assertEquals("red", token.getColor());
    }

    @Test
    public void shouldCreateGreenToken(){
        Token token = new Token("green");
        Assertions.assertEquals("green", token.getColor());
    }


}
