package com.tictactoe;

import com.tictactoe.model.GameState;
import com.tictactoe.service.GameService;

/**
 * Standalone test that can run without Maven/Spring Boot
 * Run with: java -cp "target/classes;target/test-classes" com.tictactoe.StandaloneGameTest
 */
public class StandaloneGameTest {
    
    public static void main(String[] args) {
        System.out.println("=== Tic-Tac-Toe Game Logic Test ===\n");
        
        int testsPassed = 0;
        int testsFailed = 0;
        
        // Test 1: Initial Game State
        System.out.print("Test 1: Initial Game State... ");
        try {
            GameService gameService = new GameService();
            GameState state = gameService.getGameState();
            if (state.getCurrentPlayer().equals("X") && !state.isGameOver() && state.getWinner() == null) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            testsFailed++;
        }
        
        // Test 2: Basic Move
        System.out.print("Test 2: Basic Move... ");
        try {
            GameService gameService = new GameService();
            GameState state = gameService.makeMove(0, 0);
            if (state.getBoard()[0][0].equals("X") && state.getCurrentPlayer().equals("O")) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            testsFailed++;
        }
        
        // Test 3: Win by Row
        System.out.print("Test 3: Win by Row... ");
        try {
            GameService gameService = new GameService();
            gameService.makeMove(0, 0); // X
            gameService.makeMove(1, 0); // O
            gameService.makeMove(0, 1); // X
            gameService.makeMove(1, 1); // O
            GameState state = gameService.makeMove(0, 2); // X wins
            if (state.isGameOver() && state.getWinner().equals("X")) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            testsFailed++;
        }
        
        // Test 4: Win by Column
        System.out.print("Test 4: Win by Column... ");
        try {
            GameService gameService = new GameService();
            gameService.makeMove(0, 0); // X
            gameService.makeMove(0, 1); // O
            gameService.makeMove(1, 0); // X
            gameService.makeMove(0, 2); // O
            GameState state = gameService.makeMove(2, 0); // X wins
            if (state.isGameOver() && state.getWinner().equals("X")) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            testsFailed++;
        }
        
        // Test 5: Win by Diagonal
        System.out.print("Test 5: Win by Diagonal... ");
        try {
            GameService gameService = new GameService();
            gameService.makeMove(0, 0); // X
            gameService.makeMove(0, 1); // O
            gameService.makeMove(1, 1); // X
            gameService.makeMove(0, 2); // O
            GameState state = gameService.makeMove(2, 2); // X wins
            if (state.isGameOver() && state.getWinner().equals("X")) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            testsFailed++;
        }
        
        // Test 6: Draw Detection
        System.out.print("Test 6: Draw Detection... ");
        try {
            GameService gameService = new GameService();
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
            if (state.isGameOver() && state.isDraw() && state.getWinner() == null) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            testsFailed++;
        }
        
        // Test 7: Invalid Move (Occupied Cell)
        System.out.print("Test 7: Invalid Move (Occupied Cell)... ");
        try {
            GameService gameService = new GameService();
            gameService.makeMove(0, 0); // X
            GameState state = gameService.makeMove(0, 0); // Try to move to occupied cell
            if (state.getCurrentPlayer().equals("O") && state.getBoard()[0][0].equals("X")) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            testsFailed++;
        }
        
        // Test 8: Reset Game
        System.out.print("Test 8: Reset Game... ");
        try {
            GameService gameService = new GameService();
            gameService.makeMove(0, 0);
            gameService.makeMove(0, 1);
            GameState state = gameService.resetGame();
            if (state.getCurrentPlayer().equals("X") && !state.isGameOver() && 
                state.getBoard()[0][0] == null && state.getBoard()[0][1] == null) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: " + e.getMessage());
            testsFailed++;
        }
        
        // Summary
        System.out.println("\n=== Test Summary ===");
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
        System.out.println("Total Tests: " + (testsPassed + testsFailed));
        
        if (testsFailed == 0) {
            System.out.println("\n✅ All tests PASSED! Game logic is working correctly.");
        } else {
            System.out.println("\n❌ Some tests FAILED. Please check the game logic.");
        }
    }
}
