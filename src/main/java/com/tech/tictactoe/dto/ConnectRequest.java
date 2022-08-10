package com.tech.tictactoe.dto;

import com.tech.tictactoe.model.Player;
import lombok.Data;

@Data
public class ConnectRequest {
    private Player player;
    private String gameId;
}
