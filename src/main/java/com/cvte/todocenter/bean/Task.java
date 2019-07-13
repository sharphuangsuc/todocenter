package com.cvte.todocenter.bean;


import java.sql.Date;

public class Task {
    private int taskId;
    private String taskName;
    private Date deadline;
    private int isDelete;

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString(){
        return "id"+this.taskId +" "+"taskName"+this.taskName +" "+"deadline"+this.deadline;
    }

}
