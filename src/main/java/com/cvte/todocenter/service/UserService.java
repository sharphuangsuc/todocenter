package com.cvte.todocenter.service;

import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.bean.Team;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void addUser(User user){
        userMapper.insertUser(user);
    }

    public void delUserById(int userId)
    {
        userMapper.deleteUserById(userId);
    }

    public void updateUserById(User user){
        userMapper.updateUserById(user);
    }

    public User getUserById(int userId)
    {
        return userMapper.selectUserById(userId);
    }

    public List<User> getAllUser(){
        return userMapper.selectAll();
    }

    public List<User> selectUserByName(String userName){
        return userMapper.selectUserByName(userName);
    }

    public List<User> selectAllDelUser()
    {
        return userMapper.selectAllDelUser();
    }

    public void delBatch(List<Integer> delList)
    {
        userMapper.batchDeleteUser(delList);
    }

    public List<Team> getUserTeamById(int userId)
    {
        return userMapper.selectAllUserTeam(userId);
    }

    public List<Task> getUserTaskById(int userId)
    {
        return userMapper.selectAllUserTask(userId);
    }
}
