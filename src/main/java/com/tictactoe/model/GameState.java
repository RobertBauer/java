package com.tictactoe.model;

public class GameState {
    private String[][] board;
    private String currentPlayer;
    private String winner;
    private boolean gameOver;
    private boolean draw;

    public GameState() {
        this.board = new String[3][3];
        this.currentPlayer = "X";
        this.winner = null;
        this.gameOver = false;
        this.draw = false;
    }

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }
}
