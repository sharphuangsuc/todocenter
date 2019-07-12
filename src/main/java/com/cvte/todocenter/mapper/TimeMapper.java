package com.cvte.todocenter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Mapper
@Component(value = "timeMapper")
public interface TimeMapper {
    void updateTeamOpeTime(Timestamp last_ope_time,int team_id);
}
