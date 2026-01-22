package com.tictactoe.service;

import com.tictactoe.model.GameState;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private GameState gameState;

    public GameService() {
        resetGame();
    }

    public GameState getGameState() {
        return gameState;
    }

    public GameState makeMove(int row, int col) {
        if (gameState.isGameOver()) {
            return gameState;
        }

        if (gameState.getBoard()[row][col] != null) {
            return gameState; // Cell already occupied
        }

        // Make the move
        gameState.getBoard()[row][col] = gameState.getCurrentPlayer();

        // Check for winner
        if (checkWinner(row, col)) {
            gameState.setWinner(gameState.getCurrentPlayer());
            gameState.setGameOver(true);
            return gameState;
        }

        // Check for draw
        if (isBoardFull()) {
            gameState.setDraw(true);
            gameState.setGameOver(true);
            return gameState;
        }

        // Switch player
        gameState.setCurrentPlayer(gameState.getCurrentPlayer().equals("X") ? "O" : "X");
        return gameState;
    }

    public GameState resetGame() {
        gameState = new GameState();
        return gameState;
    }

    private boolean checkWinner(int row, int col) {
        String player = gameState.getBoard()[row][col];
        String[][] board = gameState.getBoard();

        // Check row
        if (board[row][0] != null && board[row][0].equals(player) &&
            board[row][1] != null && board[row][1].equals(player) &&
            board[row][2] != null && board[row][2].equals(player)) {
            return true;
        }

        // Check column
        if (board[0][col] != null && board[0][col].equals(player) &&
            board[1][col] != null && board[1][col].equals(player) &&
            board[2][col] != null && board[2][col].equals(player)) {
            return true;
        }

        // Check main diagonal
        if (row == col) {
            if (board[0][0] != null && board[0][0].equals(player) &&
                board[1][1] != null && board[1][1].equals(player) &&
                board[2][2] != null && board[2][2].equals(player)) {
                return true;
            }
        }

        // Check anti-diagonal
        if (row + col == 2) {
            if (board[0][2] != null && board[0][2].equals(player) &&
                board[1][1] != null && board[1][1].equals(player) &&
                board[2][0] != null && board[2][0].equals(player)) {
                return true;
            }
        }

        return false;
    }

    private boolean isBoardFull() {
        String[][] board = gameState.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
