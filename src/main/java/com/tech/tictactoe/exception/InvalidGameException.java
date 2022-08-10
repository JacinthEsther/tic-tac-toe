package com.tech.tictactoe.exception;

public class InvalidGameException extends Throwable {
    public InvalidGameException(String message) {
        super(message);
    }
}
