package com.cvte.todocenter.service;

import com.cvte.todocenter.mapper.TimeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

public class TimeService {

    @Autowired
    private TimeMapper timeMapper;
    public void updateTeamTime(Timestamp last_ope_time,int team_id){
        timeMapper.updateTeamOpeTime(last_ope_time,team_id);
    }
}
