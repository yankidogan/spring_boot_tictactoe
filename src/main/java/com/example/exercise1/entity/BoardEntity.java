package com.example.exercise1.entity;

import javax.persistence.*;

@Entity
@Table(name="Board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long gsId;
    private Long playerXId;
    private Long playerOId;

    @Lob
    private String[][] board;

    public Long getPlayerXId() {
        return playerXId;
    }

    public void setPlayerXId(Long playerXId) {
        this.playerXId = playerXId;
    }

    public Long getPlayerOId() {
        return playerOId;
    }

    public void setPlayerOId(Long playerOId) {
        this.playerOId = playerOId;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGsId() {
        return gsId;
    }

    public void setGsId(Long gsId) {
        this.gsId = gsId;
    }

    public String[][] getBoard() {
        String [][] new_board;
        new_board = board;
        return new_board;
    }

    public void setBoard(String[][] board) {
        this.board = board.clone();
    }
}
