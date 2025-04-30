package com.czavala.football_tournament_manager.exception;

public class DuplicateSquadNumberException extends RuntimeException {

    private String sqlErrorMsg;

    public DuplicateSquadNumberException(String message) {
        super(message);
    }

    public DuplicateSquadNumberException(String message, String sqlErrorMsg) {
        super(message);
        this.sqlErrorMsg = sqlErrorMsg;
    }

    public String getSqlErrorMsg() {
        return sqlErrorMsg;
    }
}
