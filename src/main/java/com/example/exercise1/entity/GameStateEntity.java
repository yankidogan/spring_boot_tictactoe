package com.example.exercise1.entity;

import javax.persistence.*;

@Entity
@Table(name="GameState")
public class GameStateEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "GameState [id=" + id + ", state=" + state + "]";
    }
}
