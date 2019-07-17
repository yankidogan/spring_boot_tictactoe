package com.example.exercise1.controller;

import com.example.exercise1.DTO.GetBoardDTO;
import com.example.exercise1.DTO.BoardDTO;
import com.example.exercise1.DTO.PlayerDTO;
import com.example.exercise1.DTO.PlayerNumbersDTO;
import com.example.exercise1.service.TicTacToeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicTacToeController {

    @Autowired
    private TicTacToeService tictactoeservice;

    @GetMapping("/hello")
    public String helloMessage(){
        return "Hello World!";
    }

  @GetMapping("/getBoard/{param_id}")
  public GetBoardDTO getBoard(@PathVariable Long param_id){
        return tictactoeservice.getBoard(param_id);
  }

    @PostMapping("/newGame")
    public BoardDTO newBoard(@RequestParam Long id_playerX, @RequestParam Long id_playerO){
        return tictactoeservice.beginNewGame(id_playerX, id_playerO);
    }

   @PostMapping("/playNext")
    public BoardDTO playNext(@RequestParam String param_player, @RequestParam int param_i, @RequestParam int param_j){
       return tictactoeservice.playMove(param_i, param_j, param_player);
    }

    @GetMapping("/checkForWinner")
    public String checkWin(){
        return tictactoeservice.checkForWinner();
    }

    @GetMapping("/getPlayer/{param_id}")
    public PlayerDTO playerName(@PathVariable Long param_id){
        return tictactoeservice.getPlayer(param_id);
    }

    @GetMapping("/getNumbers/{param_id}")
    public PlayerNumbersDTO numbers(@PathVariable Long param_id){
        return tictactoeservice.getNumbers(param_id);
    }

}
