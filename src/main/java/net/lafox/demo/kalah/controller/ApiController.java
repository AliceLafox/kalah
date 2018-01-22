package net.lafox.demo.kalah.controller;

import net.lafox.demo.kalah.data.GameDto;
import net.lafox.demo.kalah.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiController.API_PATH)
public class ApiController {
    public static final String API_PATH = "/api/v1/game";
    @Autowired
    private GameService gameService;

    @GetMapping(value = "/newGame", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newGame(@RequestParam(required = false) Integer seeds) {
        return ResponseEntity.ok(gameService.newGame(seeds));
    }

    @PostMapping(value = "/nextTurn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity nextTurn(@RequestBody GameDto gameDto) {
        return ResponseEntity.ok(gameService.nextTurn(gameDto));
    }

}
