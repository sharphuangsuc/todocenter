package com.cvte.todocenter.service;

import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void addUser(User user){
        userMapper.insertUser(user);
    }

    public void delUserById(int user_id)
    {
        userMapper.deleteUserById(user_id);
    }

    public void updateUserById(int user_id,User user){
        userMapper.updateUserById(user_id,user);
    }

    public User getUserById(int user_id)
    {
        return userMapper.selectUserById(user_id);
    }

    public List<User> getAllTask(){
        return userMapper.selectAll();
    }
}
