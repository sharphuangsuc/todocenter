package com.cvte.todocenter.controller;

import com.cvte.todocenter.bean.Team;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTeam;
import com.cvte.todocenter.service.TeamService;
import com.cvte.todocenter.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    //获取所有团队
    @RequestMapping("/getAll")
    public List<Team> getAllTeam(){
        List<Team> list=teamService.getAllTeam();
        return list;
    }

    //添加团队
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public void addTeam(@Param("team") Team team)
    {
        TimeController timeController=new TimeController();
        Timestamp lastOpeTime=timeController.getTime();
        String operation="add new team";
        team.setLastOpeTime(lastOpeTime);
        team.setOperation(operation);
        teamService.addTeam(team);
    }

    //删除团队
    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    public void delTeamById(@RequestParam int teamId)
    {
        TimeController timeController=new TimeController();
        Timestamp lastOpeTime=timeController.getTime();
        String operation="delete the team";
        teamService.delTeamById(teamId,lastOpeTime,operation);
    }

    //修改团队信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(Team team)
    {
        TimeController timeController=new TimeController();
        Timestamp lastOpeTime=timeController.getTime();
        String operation="update the team";
        team.setLastOpeTime(lastOpeTime);
        team.setOperation(operation);
        teamService.updateTeamById(team);
    }

    //根据团队id获取团队
    @RequestMapping("/getById")
    public Team getTeamById(@RequestParam int teamId)
    {
        return teamService.getTeamById(teamId);
    }

    //根据团队名获取团队
    @RequestMapping("/getByName")
    public List<Team> getTeamByName(@RequestParam String teamName)
    {
        return teamService.selectTeamByName(teamName);
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
        TimeController timeController=new TimeController();
        Timestamp lastOpeTime=timeController.getTime();
        String operation="delete the team";
        teamService.delBatch(delList,lastOpeTime,operation);

    }

    //为团队指派用户
    @RequestMapping(value= "/addUser",method = RequestMethod.POST)
    public void addTeamUser(@RequestParam("addUserList") List<UserTeam> addUserList)throws Exception
    {

        for(UserTeam userTeam:addUserList)
        {
            int userId=userTeam.getUserId();
            int teamId=userTeam.getTeamId();
            User user=userService.getUserById(userId);
            Team team=teamService.getTeamById(teamId);
            if(user==null||team==null)
            {
                throw new Exception("团队或者成员不存在");
            }
        }
        teamService.addTeamUser(addUserList);
    }

    //剔除团队成员
    @RequestMapping(value = "/delUserTeam",method = {RequestMethod.POST})
    public void delTeamUser(@RequestParam int teamId,@RequestParam int userId)
    {
        teamService.delTeamUser(teamId,userId);
    }

    //获取团队所有成员
    @RequestMapping("/getTeamUser")
    public List<User> getTeamUserById(@RequestParam int teamId)
    {
        return teamService.getTeamUserById(teamId);
    }

}
