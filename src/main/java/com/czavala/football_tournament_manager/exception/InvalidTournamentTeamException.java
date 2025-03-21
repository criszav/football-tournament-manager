package com.czavala.football_tournament_manager.exception;

public class InvalidTournamentTeamException extends RuntimeException {

    public InvalidTournamentTeamException(String message) {
        super(message);
    }

    public InvalidTournamentTeamException(String message, Throwable cause) {
        super(message, cause);
    }
}
