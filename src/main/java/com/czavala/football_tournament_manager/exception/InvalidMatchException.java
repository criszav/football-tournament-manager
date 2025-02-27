package com.czavala.football_tournament_manager.exception;

public class InvalidMatchException extends RuntimeException {

    public InvalidMatchException(String message) {
        super(message);
    }

    public InvalidMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
