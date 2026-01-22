const API_BASE_URL = '/api/game';

let gameState = {
    board: [[null, null, null], [null, null, null], [null, null, null]],
    currentPlayer: 'X',
    winner: null,
    gameOver: false,
    draw: false
};

// Initialize game
document.addEventListener('DOMContentLoaded', () => {
    loadGameState();
    setupEventListeners();
});

function setupEventListeners() {
    const cells = document.querySelectorAll('.cell');
    cells.forEach(cell => {
        cell.addEventListener('click', handleCellClick);
    });

    document.getElementById('resetBtn').addEventListener('click', resetGame);
}

async function loadGameState() {
    try {
        const response = await fetch(`${API_BASE_URL}/state`);
        gameState = await response.json();
        updateUI();
    } catch (error) {
        console.error('Error loading game state:', error);
    }
}

async function handleCellClick(event) {
    if (gameState.gameOver) {
        return;
    }

    const row = parseInt(event.target.dataset.row);
    const col = parseInt(event.target.dataset.col);

    if (gameState.board[row][col] !== null) {
        return; // Cell already occupied
    }

    try {
        const response = await fetch(`${API_BASE_URL}/move`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ row, col })
        });

        if (response.ok) {
            gameState = await response.json();
            updateUI();
        } else {
            console.error('Error making move');
        }
    } catch (error) {
        console.error('Error making move:', error);
    }
}

async function resetGame() {
    try {
        const response = await fetch(`${API_BASE_URL}/reset`, {
            method: 'POST'
        });

        if (response.ok) {
            gameState = await response.json();
            updateUI();
        } else {
            console.error('Error resetting game');
        }
    } catch (error) {
        console.error('Error resetting game:', error);
    }
}

function updateUI() {
    // Update board
    const cells = document.querySelectorAll('.cell');
    cells.forEach(cell => {
        const row = parseInt(cell.dataset.row);
        const col = parseInt(cell.dataset.col);
        const value = gameState.board[row][col];

        cell.textContent = value || '';
        cell.className = 'cell';
        
        if (value) {
            cell.classList.add('occupied', value.toLowerCase());
        }
    });

    // Update current player
    document.getElementById('currentPlayer').textContent = gameState.currentPlayer;

    // Update game status
    const statusElement = document.getElementById('gameStatus');
    if (gameState.winner) {
        statusElement.textContent = `Player ${gameState.winner} wins!`;
        statusElement.style.color = '#4caf50';
    } else if (gameState.draw) {
        statusElement.textContent = "It's a draw!";
        statusElement.style.color = '#ff9800';
    } else {
        statusElement.textContent = '';
    }

    // Disable cells if game is over
    if (gameState.gameOver) {
        cells.forEach(cell => {
            cell.style.cursor = 'not-allowed';
        });
    } else {
        cells.forEach(cell => {
            if (!cell.classList.contains('occupied')) {
                cell.style.cursor = 'pointer';
            }
        });
    }
}
