package com.cvte.todocenter.controller;

import com.cvte.todocenter.bean.Team;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTeam;
import com.cvte.todocenter.service.TeamService;
import com.cvte.todocenter.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    //private TimeService timeService;
    //private TimeController timeController;

    //获取所有团队
    @RequestMapping("/getAll")
    public List<Team> getAllTeam(){
        List<Team> list=teamService.getAllTeam();
        return list;
    }

    //添加团队
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public void addTeam(Team team)
    {
        teamService.addTeam(team);
        //int team_id=team.getTeam_id();
        //Timestamp last_ope_time=timeController.getTime();
        //timeService.updateTeamTime(last_ope_time,team_id);
    }

    //删除团队
    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    public void delTeamById(@RequestParam int team_id)
    {
        teamService.delTeamById(team_id);
    }

    //修改团队信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(Team team)
    {
        teamService.updateTeamById(team);
    }

    //根据团队id获取团队
    @RequestMapping("/getById")
    public Team getTeamById(@RequestParam int team_id)
    {
        return teamService.getTeamById(team_id);
    }

    //根据团队名获取团队
    @RequestMapping("/getByName")
    public List<Team> getTeamByName(@RequestParam String team_name)
    {
        return teamService.selectTeamByName(team_name);
    }

    //获取所有已删除团队
    @RequestMapping("/getAllDel")
    public List<Team> getAllDel()
    {
        return teamService.selectAllDelTeam();
    }

    //批量删除团队
    @RequestMapping("/delBatch")
    public void delBatch(@RequestParam List<Integer> delList)
    {
        teamService.delBatch(delList);
    }

    //为团队指派用户
    @RequestMapping(value= "/addUser",method = RequestMethod.POST)
    public void addTeamUser(@RequestParam("addUserList") List<UserTeam> addUserList)
    {
        teamService.addTeamUser(addUserList);
    }

    //剔除团队成员
    @RequestMapping(value = "/delUserTeam",method = {RequestMethod.POST})
    public void delTeamUser(@RequestParam int team_id,@RequestParam int user_id)
    {
        teamService.delTeamUser(team_id,user_id);
    }

    //获取团队所有成员
    @RequestMapping("/getTeamUser")
    public List<User> getTeamUserById(@RequestParam int team_id)
    {
        return teamService.getTeamUserById(team_id);
    }
}
