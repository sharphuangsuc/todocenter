package com.cvte.todocenter.service;

import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.bean.Team;
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

    public void updateUserById(User user){
        userMapper.updateUserById(user);
    }

    public User getUserById(int user_id)
    {
        return userMapper.selectUserById(user_id);
    }

    public List<User> getAllTask(){
        return userMapper.selectAll();
    }

    public List<User> selectUserByName(String user_name){
        return userMapper.selectUserByName(user_name);
    }

    public List<User> selectAllDelUser()
    {
        return userMapper.selectAllDelUser();
    }

    public void delBatch(List<Integer> delList)
    {
        userMapper.batchDeleteUser(delList);
    }

    public List<Team> getUserTeamById(int user_id)
    {
        return userMapper.selectAllUserTeam(user_id);
    }

    public List<Task> getUserTaskById(int user_id)
    {
        return userMapper.selectAllUserTask(user_id);
    }
}
