package com.cvte.todocenter.controller;

import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.bean.Team;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //获取所有用户
    @RequestMapping("/getAll")
    public List<User> getAllUser(){
        List<User> list=userService.getAllUser();
        return list;
    }

    //添加用户
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public void addUser(User user)
    {
        TimeController timeController=new TimeController();
        Timestamp lastOpeTime=timeController.getTime();
        String operation="add new user";
        user.setLastOpeTime(lastOpeTime);
        user.setOperation(operation);
        userService.addUser(user);
    }

    //删除用户
    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    public void delUserById(@RequestParam int userId)
    {
        TimeController timeController=new TimeController();
        Timestamp lastOpeTime=timeController.getTime();
        String operation="delete the user";
        userService.delUserById(userId,lastOpeTime,operation);
    }

    //修改用户信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(User user)
    {
        TimeController timeController=new TimeController();
        Timestamp lastOpeTime=timeController.getTime();
        String operation="update the user";
        user.setLastOpeTime(lastOpeTime);
        user.setOperation(operation);
        userService.updateUserById(user);
    }

    //根据id获取用户
    @RequestMapping("/getById")
    public User getUserById(@RequestParam int userId)
    {
        return userService.getUserById(userId);
    }

    //根据用户名获取用户
    @RequestMapping("/getByName")
    public List<User> getUserByName(@RequestParam String userName)
    {
        return userService.selectUserByName(userName);
    }

    //获取所有已删除用户
    @RequestMapping("/getAllDel")
    public List<User> getAllDel()
    {
        return userService.selectAllDelUser();
    }

    //批量删除用户
    @RequestMapping("/delBatch")
    public void delBatch(List<Integer> delList)
    {
        TimeController timeController=new TimeController();
        Timestamp lastOpeTime=timeController.getTime();
        String operation="delete the user";
        userService.delBatch(delList,lastOpeTime,operation);
    }

    //获取用户所在团队
    @RequestMapping("/getTeamUser")
    public List<Team> getUserTeamById(@RequestParam int userId)
    {
        return userService.getUserTeamById(userId);
    }

    //获取用户所承担的任务
    @RequestMapping("/getTaskUser")
    public List<Task> getUserTaskById(@RequestParam int userId)
    {
        return userService.getUserTaskById(userId);
    }
}
