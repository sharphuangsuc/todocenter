package com.cvte.todocenter.service;

import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.bean.Team;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTeam;
import com.cvte.todocenter.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamMapper teamMapper;

    public void addTeam(Team team){
        teamMapper.insertTeam(team);
    }

    public void delTeamById(int teamId, Timestamp lastOpeTime, String operation)
    {
        teamMapper.deleteTeamById(teamId,lastOpeTime,operation);
    }

    public void updateTeamById(Team team){
        teamMapper.updateTeamById(team);
    }

    public Team getTeamById(int teamId)
    {
        return teamMapper.selectTeamById(teamId);
    }

    public List<Team> getAllTeam(){
        return teamMapper.selectAll();
    }

    public List<Team> selectTeamByName(String teamName){
        return teamMapper.selectTeamByName(teamName);
    }

    public List<Team> selectAllDelTeam()
    {
        return teamMapper.selectAllDelTeam();
    }

    public void delBatch(List<Integer> delList, Timestamp lastOpeTime, String operation)
    {
        teamMapper.batchDeleteTeam(delList,lastOpeTime,operation);
    }

    public void addTeamUser(List<UserTeam> addUserList)
    {
        teamMapper.insertTeamUser(addUserList);
    }

    public void delTeamUser(int teamId,int userId)
    {
        teamMapper.deleteTeamUserById(teamId,userId);
    }

    public List<User> getTeamUserById(int teamId)
    {
        return teamMapper.selectAllTeamUser(teamId);
    }

}
