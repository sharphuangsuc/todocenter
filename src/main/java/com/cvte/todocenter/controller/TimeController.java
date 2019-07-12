package com.cvte.todocenter.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeController {
    public Timestamp getTime()
    {
        Date date =new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        String nowTime=simpleDateFormat.format(date);
        Timestamp timestamp=Timestamp.valueOf(nowTime);
        return timestamp;
    }
}
