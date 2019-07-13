package com.cvte.todocenter.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeController {
    public Timestamp getTime()
    {
        Date date =new Date();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        Timestamp timestamp=Timestamp.valueOf(nowTime);
        return timestamp;
    }
}
