package com.example.puissance4;

import com.example.puissance4.exceptions.FullColumnException;
import com.example.puissance4.exceptions.OutOfGridRangeException;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private static final int NUM_ROWS = 6;
    private static final int NUM_COLUMNS = 7;
    private final List<List<Token>> gridlist;

    public Grid() {
        gridlist = new ArrayList<>();
        for (int i = 0; i < NUM_ROWS; i++) {
            List<Token> row = new ArrayList<>();
            for (int j = 0; j < NUM_COLUMNS; j++) {
                row.add(null);
            }
            gridlist.add(row);
        }
    }

    public int getNumRows() {
        return NUM_ROWS;
    }

    public int getNumColumns() {
        return NUM_COLUMNS;
    }

    public List<List<Token>> getGridlist() {
        return gridlist;
    }

    public void insert(Integer column, Token token) throws OutOfGridRangeException, NullPointerException, FullColumnException {
        validateColumn(column);
        validateToken(token);

        int rowIndex = getLowestEmptyRow(column - 1);
        if (rowIndex == -1) {
            throw new FullColumnException("Column is full.");
        }

        gridlist.get(rowIndex).set(column - 1, token);
    }

    private static void validateToken(Token token) throws NullPointerException {
        if (token == null) {
            throw new NullPointerException("Token cannot be null.");
        }
    }

    private void validateColumn(Integer column) throws OutOfGridRangeException {
        if (column == null || column < 1 || column > NUM_COLUMNS) {
            throw new OutOfGridRangeException("Column index out of grid range.");
        }
    }

    private int getLowestEmptyRow(int column) {
        for (int i = 0; i < NUM_ROWS; i++) {
            if (gridlist.get(i).get(column) == null) {
                return i;
            }
        }
        return -1;
    }

    public boolean isTokenWinner(Token token) {
        return checkHorizontal(token) || checkVertical(token) || checkBottomLeftToTopRightDiagonal(token) || checkTopLeftToBottomRightDiagonal(token);
    }

    private boolean checkTopLeftToBottomRightDiagonal(Token token) {
        for (int row = NUM_ROWS - 1; row >= 3; row--) {
            for (int col = 0; col <= NUM_COLUMNS - 4; col++) {
                if (gridlist.get(row).get(col) != null && gridlist.get(row).get(col).equals(token) &&
                        gridlist.get(row - 1).get(col + 1) != null && gridlist.get(row - 1).get(col + 1).equals(token) &&
                        gridlist.get(row - 2).get(col + 2) != null && gridlist.get(row - 2).get(col + 2).equals(token) &&
                        gridlist.get(row - 3).get(col + 3) != null && gridlist.get(row - 3).get(col + 3).equals(token)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkBottomLeftToTopRightDiagonal(Token token) {
        for (int row = 0; row <= NUM_ROWS - 4; row++) {
            for (int col = 0; col <= NUM_COLUMNS - 4; col++) {
                if (gridlist.get(row).get(col) != null && gridlist.get(row).get(col).equals(token) &&
                        gridlist.get(row + 1).get(col + 1) != null && gridlist.get(row + 1).get(col + 1).equals(token) &&
                        gridlist.get(row + 2).get(col + 2) != null && gridlist.get(row + 2).get(col + 2).equals(token) &&
                        gridlist.get(row + 3).get(col + 3) != null && gridlist.get(row + 3).get(col + 3).equals(token)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical(Token token) {
        for (int col = 0; col < NUM_COLUMNS; col++) {
            int consecutiveCount = 0;
            for (int row = 0; row < NUM_ROWS; row++) {
                Token currentToken = gridlist.get(row).get(col);
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
        for (int row = 0; row < NUM_ROWS; row++) {
            int consecutiveCount = 0;
            for (int col = 0; col < NUM_COLUMNS; col++) {
                Token currentToken = gridlist.get(row).get(col);
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

    public boolean isGridEntirelyFull() {
        for (int col = 0; col < NUM_COLUMNS; col++) {
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
