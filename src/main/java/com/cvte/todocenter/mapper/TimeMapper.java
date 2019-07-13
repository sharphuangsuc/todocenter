package com.cvte.todocenter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Mapper
@Component(value = "timeMapper")
public interface TimeMapper {
    void updateTeamOpeTime(Timestamp lastOpeTime, int teamId,String operation);
    void updateTaskOpeTime(Timestamp lastOpeTime,int task_id,String operation);
}
