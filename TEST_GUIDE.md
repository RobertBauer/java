# Testing Guide for Tic-Tac-Toe Game

## Prerequisites Check

Before testing, ensure you have:
- **Java 17 or higher** (current: Java 8 detected - needs upgrade)
- **Maven 3.6+** (not currently installed)

## Option 1: Install Maven and Test

### Install Maven:
1. Download Maven from: https://maven.apache.org/download.cgi
2. Extract and add to PATH
3. Verify: `mvn --version`

### Run Tests:
```bash
# Run unit tests
mvn test

# Build and run the application
mvn spring-boot:run

# Then open browser to: http://localhost:8080
```

## Option 2: Manual Testing Checklist

### Game Logic Tests (Unit Tests Created)

The following test cases have been created in `GameServiceTest.java`:

✅ **Initial State Test**
- Game starts with player X
- Board is empty
- Game is not over

✅ **Basic Move Test**
- Players can make moves
- Turns alternate correctly (X → O → X)

✅ **Win Detection Tests**
- Win by row (3 in a row horizontally)
- Win by column (3 in a column vertically)
- Win by main diagonal (top-left to bottom-right)
- Win by anti-diagonal (top-right to bottom-left)

✅ **Draw Detection Test**
- Game detects draw when board is full with no winner

✅ **Invalid Move Test**
- Cannot move to occupied cell
- Game state remains unchanged

✅ **Reset Test**
- Reset clears board and resets to initial state

✅ **Game Over Test**
- Cannot make moves after game ends

### Manual Web Testing

Once the application is running:

1. **Open Browser**: Navigate to `http://localhost:8080`

2. **Test Game Flow**:
   - [ ] Page loads with empty board
   - [ ] Current player shows "X"
   - [ ] Click a cell - X appears
   - [ ] Current player changes to "O"
   - [ ] Click another cell - O appears
   - [ ] Continue alternating turns

3. **Test Win Scenarios**:
   - [ ] **Row Win**: X in row 0 (0,0), (0,1), (0,2) → X wins
   - [ ] **Column Win**: X in column 0 (0,0), (1,0), (2,0) → X wins
   - [ ] **Diagonal Win**: X in diagonal (0,0), (1,1), (2,2) → X wins
   - [ ] **Anti-Diagonal Win**: X in (0,2), (1,1), (2,0) → X wins

4. **Test Draw**:
   - [ ] Fill board without a winner → Draw message appears

5. **Test Reset**:
   - [ ] Click "New Game" button
   - [ ] Board clears
   - [ ] Game resets to player X

6. **Test Invalid Moves**:
   - [ ] Try clicking occupied cell → Nothing happens
   - [ ] Try clicking after game over → Nothing happens

### API Endpoint Testing

You can test the REST API directly using curl or Postman:

```bash
# Get game state
curl http://localhost:8080/api/game/state

# Make a move (row 0, col 0)
curl -X POST http://localhost:8080/api/game/move \
  -H "Content-Type: application/json" \
  -d "{\"row\":0,\"col\":0}"

# Reset game
curl -X POST http://localhost:8080/api/game/reset
```

## Current System Status

- ✅ Java installed (version 1.8.0_221)
- ❌ Java 17 required (current: Java 8)
- ❌ Maven not installed
- ✅ All source code files created
- ✅ Unit tests created
- ✅ Frontend files created

## Next Steps

1. **Upgrade Java to version 17+**
2. **Install Maven**
3. **Run**: `mvn test` to verify game logic
4. **Run**: `mvn spring-boot:run` to start the server
5. **Test in browser**: Open `http://localhost:8080`
