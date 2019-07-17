package com.example.exercise1.entity;

import javax.persistence.*;

@Entity
@Table(name="Player")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private int numberOfWins;
    private int numberOfDraws;
    private int numberOfLosses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public int getNumberOfDraws() {
        return numberOfDraws;
    }

    public void setNumberOfDraws(int numberOfDraws) {
        this.numberOfDraws = numberOfDraws;
    }

    public int getNumberOfLosses() {
        return numberOfLosses;
    }

    public void setNumberOfLosses(int numberOfLosses) {
        this.numberOfLosses = numberOfLosses;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", name=" + name + ", numberOfWins=" + numberOfWins + ", numberOfDraws=" + numberOfDraws + ", numberOfLosses=" + numberOfLosses + "]";
    }
}
