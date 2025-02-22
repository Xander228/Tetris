# Tetris Game

This project is a Tetris game implemented in Java. It includes the core game mechanics such as tetromino movement, rotation, line clearing, and game over detection.

## Features

- Tetromino movement (left, right, down)
- Tetromino rotation (clockwise and counter-clockwise)
- Line clearing
- Game over detection
- Ghost piece display
- Soft and hard drop

## Requirements

- Java Development Kit (JDK) 11 or higher
- IntelliJ IDEA 2024.1.6 or any other Java IDE

## Getting Started

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/Xander228/tetris-game.git
    cd tetris-game
    ```

2. Open the project in IntelliJ IDEA:
    - Open IntelliJ IDEA.
    - Click on `File` > `Open...`.
    - Select the `tetris-game` directory.

### Running the Game

1. Locate the `Main.java` file in the `src` directory.
2. Right-click on `Main.java` and select `Run 'Main.main()'`.

### Controls

- **Left Arrow**: Move tetromino left
- **Right Arrow**: Move tetromino right
- **Up Arrow**: Rotate tetromino clockwise
- **Control**: Rotate tetromino counter-clockwise
- **Down Arrow**: Soft drop
- **Space**: Hard drop

## Project Structure

- `MatrixPanel.java`: Handles the game board and tetromino locking, row identification, and clearing.
- `Tetromino.java`: Represents the tetromino pieces, their movements, and rotations.
- `Constants.java`: Contains constant values used throughout the game.
- `Draw.java`: Handles the drawing of the game elements.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
