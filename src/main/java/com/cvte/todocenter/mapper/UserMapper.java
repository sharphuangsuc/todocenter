package com.cvte.todocenter.mapper;

import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.bean.Team;
import com.cvte.todocenter.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "userMapper")
public interface UserMapper {

    void insertUser(User user);

    void deleteUserById(int user_id);

    void updateUserById(User user);

    User selectUserById(int user_id);

    List<User> selectAll();

    List<User> selectUserByName(String user_name);

    List<User> selectAllDelUser();

    void batchDeleteUser(List<Integer> delList);

    List<Team> selectAllUserTeam(int user_id);

    List<Task> selectAllUserTask(int user_id);

}
