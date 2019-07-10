package com.cvte.todocenter.controller;

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

    @RequestMapping("/getAll")
    public List<User> getAllTask(){
        List<User> list=userService.getAllTask();
        return list;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addUser(@RequestBody User user)
    {
        userService.addUser(user);
    }

    @RequestMapping(value = "/del",method = {RequestMethod.GET,RequestMethod.POST})
    public void delUserById(@RequestParam int user_id)
    {
        userService.delUserById(user_id);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(@RequestParam int user_id,@RequestBody User user)
    {
        userService.updateUserById(user_id,user);
    }

    @RequestMapping("/getById")
    public User getUserById(@RequestParam int user_id)
    {
        return userService.getUserById(user_id);
    }
}
