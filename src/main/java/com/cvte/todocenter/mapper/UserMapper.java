package com.cvte.todocenter.mapper;

import com.cvte.todocenter.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "apiCategoryMapper")
public interface UserMapper {

    void insertUser(User user);

    void deleteUserById(int user_id);

    void updateUserById(int user_id,User user);

    User selectUserById(int user_id);

    List<User> selectAll();
}
