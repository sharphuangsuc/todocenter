package com.cvte.todocenter.controller;

import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.bean.Team;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //获取所有用户
    @RequestMapping("/getAll")
    public List<User> getAllTask(){
        List<User> list=userService.getAllTask();
        return list;
    }

    //添加用户
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public void addUser(User user)
    {
        userService.addUser(user);
    }

    //删除用户
    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    public void delUserById(@RequestParam int user_id)
    {
        userService.delUserById(user_id);
    }

    //修改用户信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(User user)
    {
        userService.updateUserById(user);
    }

    //根据id获取用户
    @RequestMapping("/getById")
    public User getUserById(@RequestParam int user_id)
    {
        return userService.getUserById(user_id);
    }

    //根据用户名获取用户
    @RequestMapping("/getByName")
    public List<User> getUserByName(@RequestParam String user_name)
    {
        return userService.selectUserByName(user_name);
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
        userService.delBatch(delList);
    }

    //获取用户所在团队
    @RequestMapping("/getTeamUser")
    public List<Team> getUserTeamById(@RequestParam int user_id)
    {
        return userService.getUserTeamById(user_id);
    }

    //获取用户所在团队
    @RequestMapping("/getTaskUser")
    public List<Task> getUserTaskById(@RequestParam int user_id)
    {
        return userService.getUserTaskById(user_id);
    }
}
