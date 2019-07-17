package com.example.exercise1.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetBoardDTO {
    private Long id;
    private String[][] board;

    public Long getId() {
        Long temp_id;
        temp_id = id;
        return temp_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[][] getBoard() {
        return board.clone();
    }

    public void setBoard(String[][] board) {
        this.board = board.clone();
    }
}
