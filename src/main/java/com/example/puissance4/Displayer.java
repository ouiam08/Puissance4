package com.example.puissance4;

public class Displayer {

        public static void displayGrid(Grid grid) {
            for (int row = grid.getNumRows() - 1; row >= 0; row--) {
                for (int col = 0; col < grid.getNumColumns(); col++) {
                    Token token = grid.getGrid().get(row).get(col);
                    if (token == null) {
                        System.out.print("| ");
                    } else {
                        System.out.print("|" + token.getColor().charAt(0));
                    }
                }
                System.out.println("|");
            }
            for (int col = 0; col < grid.getNumColumns(); col++) {
                System.out.print("|" + (col + 1));
            }
            System.out.println("|");
        }
    }


