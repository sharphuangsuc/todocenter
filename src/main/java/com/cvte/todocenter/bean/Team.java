package com.cvte.todocenter.bean;

import java.sql.Timestamp;

public class Team {

    private int teamId;
    private String teamName;
    private int isDelete;
    private Timestamp lastOpeTime;
    private String operation;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Timestamp getLastOpeTime() {
        return lastOpeTime;
    }

    public void setLastOpeTime(Timestamp lastOpeTime) {
        this.lastOpeTime = lastOpeTime;
    }


    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int team_id) {
        this.teamId = team_id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
