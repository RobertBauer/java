# Tic-Tac-Toe Web Game

A web-based Tic-Tac-Toe game built with Java Spring Boot, featuring a modern and responsive user interface.

## Features

- Interactive 3x3 game board
- Turn-based gameplay (X and O players)
- Win detection (rows, columns, and diagonals)
- Draw detection
- Game reset functionality
- Modern, responsive UI with smooth animations

## Prerequisites

- Java 8 or higher
- Maven 3.6 or higher

## Project Structure

```
java/
├── pom.xml                                    # Maven configuration
├── src/
│   └── main/
│       ├── java/
│       │   └── com/tictactoe/
│       │       ├── TicTacToeApplication.java  # Main Spring Boot application
│       │       ├── controller/
│       │       │   ├── GameController.java    # REST API endpoints
│       │       │   └── WebController.java     # Web page controller
│       │       ├── model/
│       │       │   ├── GameState.java         # Game state model
│       │       │   └── MoveRequest.java       # Move request model
│       │       └── service/
│       │           └── GameService.java       # Game logic service
│       └── resources/
│           ├── application.properties         # Application configuration
│           ├── templates/
│           │   └── index.html                 # Main game page
│           └── static/
│               ├── css/
│               │   └── style.css             # Game styles
│               └── js/
│                   └── game.js                # Game client logic
└── README.md
```

## How to Run

### Option 1: Using Maven

1. **Build the project:**
   ```bash
   mvn clean install
   ```

2. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

### Option 2: Using the JAR file

1. **Build the JAR:**
   ```bash
   mvn clean package
   ```

2. **Run the JAR:**
   ```bash
   java -jar target/tictactoe-web-1.0.0.jar
   ```

### Option 3: Using an IDE

1. Import the project as a Maven project in your IDE (IntelliJ IDEA, Eclipse, etc.)
2. Run the `TicTacToeApplication` class

## Accessing the Game

Once the application is running, open your web browser and navigate to:

```
http://localhost:8080
```

## API Endpoints

The game exposes the following REST API endpoints:

- `GET /api/game/state` - Get the current game state
- `POST /api/game/move` - Make a move (requires JSON body: `{"row": 0, "col": 0}`)
- `POST /api/game/reset` - Reset the game to initial state

## How to Play

1. The game starts with player X
2. Click on any empty cell to make your move
3. Players alternate turns (X → O → X → ...)
4. The first player to get three in a row (horizontally, vertically, or diagonally) wins
5. If all cells are filled with no winner, it's a draw
6. Click "New Game" to start a fresh game

## Technologies Used

- **Backend:** Spring Boot 2.7.18
- **Frontend:** HTML5, CSS3, JavaScript (ES6+)
- **Build Tool:** Maven
- **Java Version:** 8 (1.8)

## License

This project is open source and available for educational purposes.
