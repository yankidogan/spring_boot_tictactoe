package com.example.exercise1.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerNumbersDTO {
    private int numberOfWins;
    private int numberOfDraws;
    private int numberOfLosses;
}
