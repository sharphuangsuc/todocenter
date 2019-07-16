package com.cvte.todocenter.bean;

import java.sql.Timestamp;

public class User {

    private int userId;
    private String userName;
    private String email;
    private int isDelete;
    private Timestamp lastOpeTime;
    private String operation;

    public Timestamp getLastOpeTime() {
        return lastOpeTime;
    }

    public void setLastOpeTime(Timestamp lastOpeTime) {
        this.lastOpeTime = lastOpeTime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
