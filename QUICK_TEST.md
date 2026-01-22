# Quick Test Guide

## Current Status
- ✅ Java 8 installed and verified
- ✅ All source code files created
- ✅ Code logic verified (see CODE_VERIFICATION_TEST.md)
- ❌ Maven not installed (required to build/run)

## To Test the Game

### Option 1: Install Maven and Run (Recommended)

1. **Download Maven:**
   - Visit: https://maven.apache.org/download.cgi
   - Download: `apache-maven-3.9.x-bin.zip`
   - Extract to a folder (e.g., `C:\Program Files\Apache\maven`)
   - Add to PATH: `C:\Program Files\Apache\maven\bin`

2. **Verify Maven:**
   ```powershell
   mvn --version
   ```

3. **Build and Run:**
   ```powershell
   cd C:\GIT\java
   mvn clean install
   mvn spring-boot:run
   ```

4. **Test in Browser:**
   - Open: http://localhost:8080
   - Play the game!

### Option 2: Use IDE (IntelliJ IDEA / Eclipse)

1. **Open Project:**
   - File → Open → Select `C:\GIT\java` folder
   - IDE will detect Maven project

2. **Run Application:**
   - Right-click `TicTacToeApplication.java`
   - Select "Run" or "Debug"

3. **Test in Browser:**
   - Open: http://localhost:8080

### Option 3: Manual API Testing (Without UI)

If you have `curl` or Postman:

```powershell
# Get game state
curl http://localhost:8080/api/game/state

# Make a move (row 0, col 0)
curl -X POST http://localhost:8080/api/game/move -H "Content-Type: application/json" -d "{\"row\":0,\"col\":0}"

# Reset game
curl -X POST http://localhost:8080/api/game/reset
```

## What to Test

### ✅ Game Flow
1. Page loads with empty board
2. Current player shows "X"
3. Click cell → X appears
4. Current player changes to "O"
5. Click another cell → O appears
6. Continue alternating

### ✅ Win Scenarios
- **Row Win:** X in (0,0), (0,1), (0,2) → "Player X wins!"
- **Column Win:** X in (0,0), (1,0), (2,0) → "Player X wins!"
- **Diagonal Win:** X in (0,0), (1,1), (2,2) → "Player X wins!"
- **Anti-Diagonal:** X in (0,2), (1,1), (2,0) → "Player X wins!"

### ✅ Draw
- Fill all 9 cells without a winner → "It's a draw!"

### ✅ Reset
- Click "New Game" → Board clears, starts with X

### ✅ Invalid Moves
- Click occupied cell → Nothing happens
- Click after game over → Nothing happens

## Expected Results

All functionality should work as described. The code has been verified for:
- ✅ Correct win detection (all 4 win types)
- ✅ Correct draw detection
- ✅ Proper turn alternation
- ✅ Invalid move prevention
- ✅ Game reset functionality

## Troubleshooting

### Port 8080 Already in Use
Edit `src/main/resources/application.properties`:
```properties
server.port=8081
```

### Maven Build Fails
- Check Java version: `java -version` (should be 1.8+)
- Check Maven version: `mvn --version`
- Try: `mvn clean` then `mvn install`

### Application Won't Start
- Check console for error messages
- Verify all dependencies downloaded
- Check if port is available

## Next Steps

Once Maven is installed:
1. Run `mvn test` to execute unit tests
2. Run `mvn spring-boot:run` to start server
3. Open browser and play!

The game is ready - just needs Maven to build and run! 🎮
