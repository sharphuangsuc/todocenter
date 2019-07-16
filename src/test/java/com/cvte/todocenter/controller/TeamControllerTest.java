package com.cvte.todocenter.controller;

import com.cvte.todocenter.bean.Team;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTeam;
import com.cvte.todocenter.service.TeamService;
import com.cvte.todocenter.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamControllerTest {

    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    @Test
    public void addTeam() {
        Team team=new Team();
        team.setTeamName("fefedww");
        teamService.addTeam(team);
    }

    @Test
    public void addTeamUser()throws Exception {
        List<UserTeam> list=new ArrayList<>();
        UserTeam userTeam1=new UserTeam();
        userTeam1.setTeamId(14);
        userTeam1.setUserId(1);
        UserTeam userTeam2 =new UserTeam();
        userTeam2.setTeamId(14);
        userTeam2.setUserId(2);
        list.add(userTeam1);
        list.add(userTeam2);
        for(UserTeam userTeam:list)
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
        teamService.addTeamUser(list);
    }
}