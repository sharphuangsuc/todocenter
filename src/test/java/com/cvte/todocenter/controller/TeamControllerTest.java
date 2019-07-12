package com.cvte.todocenter.controller;

import com.cvte.todocenter.bean.Team;
import com.cvte.todocenter.bean.UserTeam;
import com.cvte.todocenter.service.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamControllerTest {

    @Autowired
    private TeamService teamService;
    @Test
    public void addTeam() {
        Team team=new Team();
        team.setTeam_name("fefedww");
        teamService.addTeam(team);
    }

    @Test
    public void addTeamUser() {
        List<UserTeam> list=new ArrayList<>();
        UserTeam userTeam1=new UserTeam();
        userTeam1.setTeam_id(1);
        userTeam1.setUser_id(1);
        UserTeam userTeam2 =new UserTeam();
        userTeam2.setTeam_id(2);
        userTeam2.setUser_id(2);
        list.add(userTeam1);
        list.add(userTeam2);
        teamService.addTeamUser(list);
    }
}