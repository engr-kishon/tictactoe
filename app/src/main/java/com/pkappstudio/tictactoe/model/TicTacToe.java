package com.pkappstudio.tictactoe.model;

/**
 * Created by kishon on 09,March,2022
 */
public class TicTacToe {
    private int array[][];
    private boolean isFinished;
    private int cell;
    private int winner = Game.PLAYER_NONE.getValue();

    public TicTacToe(int cell) {
        this.cell = cell;
        this.array = new int[cell][cell];
    }


    public void initGame() {
        for (int x_index = 0; x_index < array.length; x_index++) {
            for (int y_index = 0; y_index < array.length; y_index++) {
                array[x_index][y_index] = -1;
            }
        }
    }


    public boolean put(int x_index, int y_index, int value) {
        if (array[x_index][y_index] == -1) {
            array[x_index][y_index] = value;
            return true;
        }

        return false;
    }


    public void isEmpty() {
        if (this.isFinished) {
            return;
        }

        this.isFinished = true;

        for (int[] arr : array) {
            for (int value : arr) {
                if (value == -1) {
                    this.isFinished = false;
                    break;
                }
            }
        }
    }

    public void match(int x_axis, int y_axis, int player) {
        int x_count = 1, y_count = 1, r_count = 1, l_count = 1;

        int length = array.length - 1;

        for (int index = 0; index < array.length - 1; index++) {
            if (array[x_axis][index] == array[x_axis][index + 1]) {
                x_count++;
            }

            if (array[index][y_axis] == array[index + 1][y_axis]) {
                y_count++;
            }

            if (array[index + 1][index + 1] != -1 && array[index][index] == array[index + 1][index + 1]) {
                r_count++;
            }

            if (array[index + 1][length - 1] != -1 && array[index][length] == array[index + 1][length - 1]) {
                l_count++;
            }

            if (x_count == cell || y_count == cell || r_count == cell || l_count == cell) {
                this.winner = player;
                this.isFinished = true;
                break;
            }

            length--;
        }
    }


    public int getCell() {
        return this.cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public int getWinner() {
        return this.winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
}
