package com.cvte.todocenter.bean;

import java.sql.Timestamp;

public class Time {
    private Timestamp last_ope_time;

    public Timestamp getLast_ope_time() {
        return last_ope_time;
    }

    public void setLast_ope_time(Timestamp last_ope_time) {
        this.last_ope_time = last_ope_time;
    }
}
