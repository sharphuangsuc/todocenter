package com.cvte.todocenter.mapper;

import com.cvte.todocenter.bean.Team;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTeam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Component(value = "teamMapper")
public interface TeamMapper {
    void insertTeam(Team team);

    void deleteTeamById(int teamId, Timestamp lastOpeTime, String operation);

    void updateTeamById(Team team);

    Team selectTeamById(int teamId);

    List<Team> selectAll();

    List<Team> selectTeamByName(String teamName);

    List<Team> selectAllDelTeam();

    void batchDeleteTeam(List<Integer> delList,Timestamp lastOpeTime, String operation);

    void insertTeamUser(List<UserTeam> addUserList);

    void deleteTeamUserById(int teamId,int userId);

    List<User> selectAllTeamUser(int teamId);
}
