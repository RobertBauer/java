package com.tictactoe.controller;

import com.tictactoe.model.GameState;
import com.tictactoe.model.MoveRequest;
import com.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/state")
    public ResponseEntity<GameState> getGameState() {
        return ResponseEntity.ok(gameService.getGameState());
    }

    @PostMapping("/move")
    public ResponseEntity<GameState> makeMove(@RequestBody MoveRequest moveRequest) {
        if (moveRequest.getRow() < 0 || moveRequest.getRow() > 2 ||
            moveRequest.getCol() < 0 || moveRequest.getCol() > 2) {
            return ResponseEntity.badRequest().build();
        }
        GameState gameState = gameService.makeMove(moveRequest.getRow(), moveRequest.getCol());
        return ResponseEntity.ok(gameState);
    }

    @PostMapping("/reset")
    public ResponseEntity<GameState> resetGame() {
        return ResponseEntity.ok(gameService.resetGame());
    }
}
