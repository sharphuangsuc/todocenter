package com.cvte.todocenter.mapper;

import com.cvte.todocenter.bean.Team;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTeam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "teamMapper")
public interface TeamMapper {
    void insertTeam(Team team);

    void deleteTeamById(int team_id);

    void updateTeamById(Team team);

    Team selectTeamById(int team_id);

    List<Team> selectAll();

    List<Team> selectTeamByName(String team_name);

    List<Team> selectAllDelTeam();

    void batchDeleteTeam(List<Integer> delList);

    void insertTeamUser(List<UserTeam> addUserList);

    void deleteTeamUserById(int team_id,int user_id);

    List<User> selectAllTeamUser(int team_id);
}
