package com.example.exercise1.service;

import com.example.exercise1.DTO.GetBoardDTO;
import com.example.exercise1.DTO.BoardDTO;
import com.example.exercise1.DTO.PlayerDTO;
import com.example.exercise1.DTO.PlayerNumbersDTO;
import com.example.exercise1.entity.BoardEntity;
import com.example.exercise1.entity.PlayerEntity;
import com.example.exercise1.repository.BoardRepository;
import com.example.exercise1.repository.PlayerRepository;
import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.NoSuchObjectException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class TicTacToeService {
    public String lastPlaced;
    int Counter = 0;
    String gameStatus = "Game continues";
    private String[][] board = new String[10][10];
    ModelMapper mapper = new ModelMapper();

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private BoardRepository boardRepository;

    public PlayerDTO getPlayer(Long playerId){
        Optional<PlayerEntity> playerOptional = playerRepository.findById(playerId);
        if(playerOptional.isPresent()){
           PlayerEntity player = playerOptional.get();
           return mapper.map(player, PlayerDTO.class);
        }

        else{
            throw new NoSuchElementException();
        }
    }

    public PlayerNumbersDTO getNumbers(Long playerId){
        Optional<PlayerEntity> playerOptional = playerRepository.findById(playerId);
        if(playerOptional.isPresent()){
            PlayerEntity player = playerOptional.get();
            return mapper.map(player, PlayerNumbersDTO.class);
        }
        else{
            throw new NoSuchElementException();
        }
    }

    public BoardDTO playMove (int i, int j, String player){
        if((i>9)||(j>9)) throw new ArrayIndexOutOfBoundsException();
        board [i][j] = player;
        Optional<BoardEntity> boardOptional = boardRepository.findById(Long.valueOf(1));

        if(boardOptional.isPresent()){
            BoardEntity entity = boardOptional.get();
            entity.setBoard(board);
            entity.setId(Long.valueOf(1));
            boardRepository.save(entity);
            Counter++;
            lastPlaced = player;
            return mapper.map(entity, BoardDTO.class);
        }
        else{
            throw new NullPointerException();
        }

    }

    public BoardDTO beginNewGame (Long id_playerX, Long id_playerO){
        if((id_playerX!=1||id_playerX!=2)||(id_playerO!=1||id_playerO!=2)) return null;
        if(id_playerO==id_playerX) return null;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                board[i][j] = "-";
            }
        }
        BoardEntity entity = new BoardEntity();
        entity.setBoard(board);
        entity.setPlayerXId(id_playerX);
        entity.setPlayerOId(id_playerO);
        entity.setGsId(Long.valueOf(1));
        boardRepository.save(entity);

        return mapper.map(entity, BoardDTO.class);
    }

    public GetBoardDTO getBoard (Long board_id){
        Optional<BoardEntity> boardOptional = boardRepository.findById(board_id);

        if(boardOptional.isPresent()){
            BoardEntity board = boardOptional.get();
            return mapper.map(board, GetBoardDTO.class);
        }

        else{
            throw new NullPointerException();
        }
    }

    public String checkForWinner(){
        if(board==null) throw new NullPointerException();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(board[i][j].equals(lastPlaced)){
                    if(j<=5){
                        if(board[i][j+1].equals(lastPlaced)&&board[i][j+2].equals(lastPlaced)&&board[i][j+3].equals(lastPlaced)&&board[i][j+4].equals(lastPlaced)){
                            gameStatus = lastPlaced+" has won";
                            saveData(lastPlaced, false);
                            return gameStatus;
                        }
                    }
                    if(i<=5){
                        if(board[i+1][j].equals(lastPlaced)&&board[i+2][j].equals(lastPlaced)&&board[i+3][j].equals(lastPlaced)&&board[i+4][j].equals(lastPlaced)){
                            gameStatus = lastPlaced+" has won";
                            saveData(lastPlaced, false);
                            return gameStatus;
                        }
                    }
                    if((i<=5)&&(j<=5)){
                        if(board[i+1][j+1].equals(lastPlaced)&&board[i+2][j+2].equals(lastPlaced)&&board[i+3][j+3].equals(lastPlaced)&&board[i+4][j+4].equals(lastPlaced)){
                            gameStatus = lastPlaced+" has won";
                            saveData(lastPlaced, false);
                            return gameStatus;
                        }
                    }

                    if((i<=5)&&(j>=4)){
                        if(board[i+1][j-1].equals(lastPlaced)&&board[i+2][j-2].equals(lastPlaced)&&board[i+3][j-3].equals(lastPlaced)&&board[i+4][j-4].equals(lastPlaced)){
                            gameStatus = lastPlaced+" has won";
                            saveData(lastPlaced, false);
                            return gameStatus;
                        }
                    }
                    else if(Counter==100){
                        gameStatus = "Draw";
                        saveData(lastPlaced, true);
                        return gameStatus;
                    }

                }
            }
        }
        gameStatus = "Game continues";
        return gameStatus;
    }

    private void saveData (String player, boolean isDraw){
        Optional<BoardEntity> boardOptional = boardRepository.findById(Long.valueOf(1));

        if(boardOptional.isPresent()){
            BoardEntity board = boardOptional.get();
            Optional<PlayerEntity> playerXOptional = playerRepository.findById(board.getPlayerXId());
            Optional<PlayerEntity> playerOOptional = playerRepository.findById(board.getPlayerOId());
            if(playerXOptional.isPresent() && playerOOptional.isPresent()){
                PlayerEntity playerX = playerXOptional.get();
                PlayerEntity playerO = playerOOptional.get();
            if(isDraw){

                playerX.setNumberOfDraws(playerX.getNumberOfDraws() + 1);
                playerRepository.save(playerX);

                playerO.setNumberOfDraws(playerO.getNumberOfDraws() + 1);
                playerRepository.save(playerO);

                board.setGsId(Long.valueOf(2));
                boardRepository.save(board);
            }

            else{
            if(player.equals("X")){
                playerX.setNumberOfWins(playerX.getNumberOfWins() + 1);
                playerRepository.save(playerX);
                playerO.setNumberOfLosses(playerO.getNumberOfLosses() + 1);
                playerRepository.save(playerO);
                board.setGsId(Long.valueOf(3));
                boardRepository.save(board);
            }
            else{
                playerO.setNumberOfWins(playerX.getNumberOfWins() + 1);
                playerRepository.save(playerX);
                playerX.setNumberOfLosses(playerO.getNumberOfLosses() + 1);
                playerRepository.save(playerO);
                board.setGsId(Long.valueOf(4));
                boardRepository.save(board);
            }

        }}}
    }

}
