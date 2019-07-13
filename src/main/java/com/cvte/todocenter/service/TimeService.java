package com.cvte.todocenter.service;

import com.cvte.todocenter.mapper.TimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TimeService {

    @Autowired
    private TimeMapper timeMapper;
    public void updateTeamTime(Timestamp lastOpeTime,int team_id,String operation){

        timeMapper.updateTeamOpeTime(lastOpeTime,team_id,operation);
    }

    public void updateTaskTime(Timestamp lastOpeTime,int taskId,String operation)
    {
        timeMapper.updateTaskOpeTime(lastOpeTime,taskId,operation);
    }
}
