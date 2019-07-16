package com.cvte.todocenter.mapper;

import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.bean.Team;
import com.cvte.todocenter.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Component(value = "userMapper")
public interface UserMapper {

    void insertUser(User user);

    void deleteUserById(int userId, Timestamp lastOpeTime, String operation);

    void updateUserById(User user);

    User selectUserById(int userId);

    List<User> selectAll();

    List<User> selectUserByName(String userName);

    List<User> selectAllDelUser();

    void batchDeleteUser(List<Integer> delList, Timestamp lastOpeTime, String operation);

    List<Team> selectAllUserTeam(int userId);

    List<Task> selectAllUserTask(int userId);

}
