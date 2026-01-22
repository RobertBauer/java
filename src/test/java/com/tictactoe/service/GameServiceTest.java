package com.tictactoe.service;

import com.tictactoe.model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService();
    }

    @Test
    void testInitialGameState() {
        GameState state = gameService.getGameState();
        assertNotNull(state);
        assertEquals("X", state.getCurrentPlayer());
        assertFalse(state.isGameOver());
        assertNull(state.getWinner());
    }

    @Test
    void testMakeMove() {
        GameState state = gameService.makeMove(0, 0);
        assertEquals("X", state.getBoard()[0][0]);
        assertEquals("O", state.getCurrentPlayer());
        assertFalse(state.isGameOver());
    }

    @Test
    void testWinByRow() {
        // X makes moves in first row
        gameService.makeMove(0, 0); // X
        gameService.makeMove(1, 0); // O
        gameService.makeMove(0, 1); // X
        gameService.makeMove(1, 1); // O
        GameState state = gameService.makeMove(0, 2); // X wins
        
        assertTrue(state.isGameOver());
        assertEquals("X", state.getWinner());
    }

    @Test
    void testWinByColumn() {
        // X makes moves in first column
        gameService.makeMove(0, 0); // X
        gameService.makeMove(0, 1); // O
        gameService.makeMove(1, 0); // X
        gameService.makeMove(0, 2); // O
        GameState state = gameService.makeMove(2, 0); // X wins
        
        assertTrue(state.isGameOver());
        assertEquals("X", state.getWinner());
    }

    @Test
    void testWinByDiagonal() {
        // X makes moves in main diagonal
        gameService.makeMove(0, 0); // X
        gameService.makeMove(0, 1); // O
        gameService.makeMove(1, 1); // X
        gameService.makeMove(0, 2); // O
        GameState state = gameService.makeMove(2, 2); // X wins
        
        assertTrue(state.isGameOver());
        assertEquals("X", state.getWinner());
    }

    @Test
    void testWinByAntiDiagonal() {
        // X makes moves in anti-diagonal
        gameService.makeMove(0, 2); // X
        gameService.makeMove(0, 0); // O
        gameService.makeMove(1, 1); // X
        gameService.makeMove(0, 1); // O
        GameState state = gameService.makeMove(2, 0); // X wins
        
        assertTrue(state.isGameOver());
        assertEquals("X", state.getWinner());
    }

    @Test
    void testDraw() {
        // Create a draw scenario
        gameService.makeMove(0, 0); // X
        gameService.makeMove(0, 1); // O
        gameService.makeMove(0, 2); // X
        gameService.makeMove(1, 0); // O
        gameService.makeMove(1, 2); // X
        gameService.makeMove(1, 1); // O
        gameService.makeMove(2, 0); // X
        gameService.makeMove(2, 2); // O
        GameState state = gameService.makeMove(2, 1); // X - draw
        
        assertTrue(state.isGameOver());
        assertTrue(state.isDraw());
        assertNull(state.getWinner());
    }

    @Test
    void testInvalidMove() {
        gameService.makeMove(0, 0); // X
        GameState state = gameService.makeMove(0, 0); // Try to move to occupied cell
        
        // Should still be O's turn, X should still be at 0,0
        assertEquals("O", state.getCurrentPlayer());
        assertEquals("X", state.getBoard()[0][0]);
    }

    @Test
    void testResetGame() {
        gameService.makeMove(0, 0);
        gameService.makeMove(0, 1);
        GameState state = gameService.resetGame();
        
        assertEquals("X", state.getCurrentPlayer());
        assertFalse(state.isGameOver());
        assertNull(state.getWinner());
        assertNull(state.getBoard()[0][0]);
        assertNull(state.getBoard()[0][1]);
    }

    @Test
    void testCannotMoveAfterGameOver() {
        // X wins
        gameService.makeMove(0, 0); // X
        gameService.makeMove(1, 0); // O
        gameService.makeMove(0, 1); // X
        gameService.makeMove(1, 1); // O
        gameService.makeMove(0, 2); // X wins
        
        GameState state = gameService.getGameState();
        assertTrue(state.isGameOver());
        
        // Try to make another move
        GameState newState = gameService.makeMove(2, 0);
        assertTrue(newState.isGameOver());
        assertEquals("X", newState.getWinner());
    }
}
