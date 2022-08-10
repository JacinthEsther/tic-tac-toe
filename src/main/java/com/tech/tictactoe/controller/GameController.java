package com.tech.tictactoe.controller;

import com.tech.tictactoe.dto.ConnectRequest;
import com.tech.tictactoe.exception.InvalidGameException;
import com.tech.tictactoe.exception.InvalidParamException;
import com.tech.tictactoe.exception.NotFoundException;
import com.tech.tictactoe.model.Game;
import com.tech.tictactoe.model.GamePlay;
import com.tech.tictactoe.model.Player;
import com.tech.tictactoe.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping
    public ResponseEntity<Game> start(@RequestBody Player player){
        return ResponseEntity.ok(gameService.createGame(player));
    }

    @PostMapping("/connect")
    public ResponseEntity <Game> connect(@RequestBody ConnectRequest request) throws InvalidParamException, InvalidGameException {
        return ResponseEntity.ok(gameService.connectToGame(request.getPlayer(), request.getGameId()));
    }

    @PostMapping("/connect/random")
    public ResponseEntity <Game> connectRandom(@RequestBody Player player) throws NotFoundException {
        return ResponseEntity.ok(gameService.connectToRandomGame(player));
    }

    @PostMapping("/gameplay")
    public ResponseEntity <Game> gamePlay(@RequestBody GamePlay request) throws InvalidGameException, NotFoundException {
        Game game = gameService.gamePlay(request);
        simpMessagingTemplate.convertAndSend("/topic/game-progress/"+ game.getGameId(),game);
        return ResponseEntity.ok(game);
    }
}
