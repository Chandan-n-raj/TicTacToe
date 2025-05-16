package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TicTacToe extends Application {
    private boolean xTurn = true;
    private Button[][] board = new Button[3][3];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button cell = new Button();
                cell.setPrefSize(100, 100);
                final int r = row, c = col;
                cell.setOnAction(e -> handleMove(cell, r, c));
                board[row][col] = cell;
                grid.add(cell, col, row);
            }
        }

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(new Scene(grid, 300, 300));
        primaryStage.show();
    }

    private void handleMove(Button cell, int row, int col) {
        if (!cell.getText().isEmpty()) return;

        cell.setText(xTurn ? "X" : "O");
        if (checkWin()) {
            showAlert((xTurn ? "X" : "O") + " wins!");
            resetBoard();
        } else if (isDraw()) {
            showAlert("It's a draw!");
            resetBoard();
        } else {
            xTurn = !xTurn;
        }
    }

    private boolean checkWin() {
        String current = xTurn ? "X" : "O";
        for (int i = 0; i < 3; i++) {
            // Rows and Columns
            if (current.equals(board[i][0].getText()) &&
                    current.equals(board[i][1].getText()) &&
                    current.equals(board[i][2].getText())) return true;

            if (current.equals(board[0][i].getText()) &&
                    current.equals(board[1][i].getText()) &&
                    current.equals(board[2][i].getText())) return true;
        }

        // Diagonals
        if (current.equals(board[0][0].getText()) &&
                current.equals(board[1][1].getText()) &&
                current.equals(board[2][2].getText())) return true;

        if (current.equals(board[0][2].getText()) &&
                current.equals(board[1][1].getText()) &&
                current.equals(board[2][0].getText())) return true;

        return false;
    }

    private boolean isDraw() {
        for (Button[] row : board) {
            for (Button cell : row) {
                if (cell.getText().isEmpty()) return false;
            }
        }
        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetBoard() {
        for (Button[] row : board) {
            for (Button cell : row) {
                cell.setText("");
            }
        }
        xTurn = true;
    }
}

