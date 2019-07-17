package com.example.exercise1.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDTO {
    private String[][] board;

    public String[][] getBoard() {
        return board.clone();
    }

    public void setBoard(String[][] board) {
        this.board = board.clone();
    }
}
