package com.czavala.football_tournament_manager.exception;

public class DuplicateSquadNumberException extends RuntimeException {

    public DuplicateSquadNumberException(String message) {
        super(message);
    }

    public DuplicateSquadNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
