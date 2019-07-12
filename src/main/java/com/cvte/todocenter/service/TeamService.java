package com.cvte.todocenter.service;

import com.cvte.todocenter.bean.Team;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTeam;
import com.cvte.todocenter.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamMapper teamMapper;

    public void addTeam(Team team){
        teamMapper.insertTeam(team);
    }

    public void delTeamById(int team_id)
    {
        teamMapper.deleteTeamById(team_id);
    }

    public void updateTeamById(Team team){
        teamMapper.updateTeamById(team);
    }

    public Team getTeamById(int team_id)
    {
        return teamMapper.selectTeamById(team_id);
    }

    public List<Team> getAllTeam(){
        return teamMapper.selectAll();
    }

    public List<Team> selectTeamByName(String team_name){
        return teamMapper.selectTeamByName(team_name);
    }

    public List<Team> selectAllDelTeam()
    {
        return teamMapper.selectAllDelTeam();
    }

    public void delBatch(List<Integer> delList)
    {
        teamMapper.batchDeleteTeam(delList);
    }

    public void addTeamUser(List<UserTeam> addUserList)
    {
        teamMapper.insertTeamUser(addUserList);
    }

    public void delTeamUser(int team_id,int user_id)
    {
        teamMapper.deleteTeamUserById(team_id,user_id);
    }

    public List<User> getTeamUserById(int team_id)
    {
        return teamMapper.selectAllTeamUser(team_id);
    }
}
