package com.example.puissance4;

import com.example.puissance4.exceptions.FullColumnException;
import com.example.puissance4.exceptions.NullTokenException;
import com.example.puissance4.exceptions.OutOfGridRangeException;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private final int numRows = 6;
    private final int numColumns = 7;
    private final List<List<Token>> grid;

    public Grid() {
        grid = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Token> row = new ArrayList<>();
            for (int j = 0; j < numColumns; j++) {
                row.add(null);
            }
            grid.add(row);
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public List<List<Token>> getGrid() {
        return grid;
    }

    public void insert(Integer column, Token token) throws OutOfGridRangeException, NullTokenException, FullColumnException {
        validateColumn(column);
        validateToken(token);

        int rowIndex = getLowestEmptyRow(column-1);
        if (rowIndex == -1) {
            throw new FullColumnException();
        }

        grid.get(rowIndex).set(column-1,token);
    }

    private static void validateToken(Token token) throws NullTokenException {
        if (token == null) {
            throw new NullTokenException("Token cannot be null.");
        }
    }

    private void validateColumn(Integer column) throws OutOfGridRangeException {
        if (column == null || column < 1 || column > numColumns) {
            throw new OutOfGridRangeException("Column index out of grid range.");
        }
    }

    private int getLowestEmptyRow(int column) {
        for (int i = 0; i < numRows; i++) {
            if (grid.get(i).get(column) == null) {
                return i;
            }
        }
        return -1;
    }

    public boolean isTokenWinner(Token token) {
        if (checkHorizontal(token)) {
            return true;
        }
        if (checkVertical(token)) {
            return true;
        }
        if (checkBottomLeftToTopRightDiagonal(token)) {
            return true;
        }
        return checkTopLeftToBottomRightDiagonal(token);
    }

    private boolean checkTopLeftToBottomRightDiagonal(Token token) {
        for (int row = numRows - 1; row >= 3; row--) {
            for (int col = 0; col <= numColumns - 4; col++) {
                if (grid.get(row).get(col) != null && grid.get(row).get(col).equals(token) &&
                        grid.get(row - 1).get(col + 1) != null && grid.get(row - 1).get(col + 1).equals(token) &&
                        grid.get(row - 2).get(col + 2) != null && grid.get(row - 2).get(col + 2).equals(token) &&
                        grid.get(row - 3).get(col + 3) != null && grid.get(row - 3).get(col + 3).equals(token)) {
                    return true;
                }
            }
        }
        return false;
    }




    private boolean checkBottomLeftToTopRightDiagonal(Token token) {
        for (int row = 0; row <= numRows - 4; row++) {
            for (int col = 0; col <= numColumns - 4; col++) {
                if (grid.get(row).get(col) != null && grid.get(row).get(col).equals(token) &&
                        grid.get(row + 1).get(col + 1) != null && grid.get(row + 1).get(col + 1).equals(token) &&
                        grid.get(row + 2).get(col + 2) != null && grid.get(row + 2).get(col + 2).equals(token) &&
                        grid.get(row + 3).get(col + 3) != null && grid.get(row + 3).get(col + 3).equals(token)) {
                    return true;
                }
            }
        }
        return false;
    }





    private boolean checkVertical(Token token) {
        for (int col = 0; col < numColumns; col++) {
            int consecutiveCount = 0;
            for (int row = 0; row < numRows; row++) {
                Token currentToken = grid.get(row).get(col);
                if (currentToken != null && currentToken.equals(token)) {
                    consecutiveCount++;
                    if (consecutiveCount == 4) {
                        return true;
                    }
                } else {
                    consecutiveCount = 0;
                }
            }
        }
        return false;
    }


    private boolean checkHorizontal(Token token) {
        for (int row = 0; row < numRows; row++) {
            int consecutiveCount = 0;
            for (int col = 0; col < numColumns; col++) {
                Token currentToken = grid.get(row).get(col);
                if (currentToken != null && currentToken.equals(token)) {
                    consecutiveCount++;
                    if (consecutiveCount == 4) {
                        return true;
                    }
                } else {
                    consecutiveCount = 0;
                }
            }
        }
        return false;
    }

    public boolean isGridInterlyFull() {
        for (int col = 0; col < numColumns; col++) {
            if (!isColumnFull(col)) {
                return false;
            }
        }
        return true;
    }

    private boolean isColumnFull(int column) {
        return getLowestEmptyRow(column) == -1;
    }


}
