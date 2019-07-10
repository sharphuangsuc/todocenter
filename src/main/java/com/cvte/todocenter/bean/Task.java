package com.cvte.todocenter.bean;


import java.util.Date;

public class Task {
    private int task_id;
    private String task_name;
    private Date deadline;
    private int is_delete;

    public int getTask_id() {
        return task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    @Override
    public String toString(){
        return "id"+this.task_id+" "+"task_name"+this.task_name+" "+"deadline"+this.deadline;
    }

}
