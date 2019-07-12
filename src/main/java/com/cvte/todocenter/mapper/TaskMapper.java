package com.cvte.todocenter.mapper;

import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTask;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "taskMapper")
public interface TaskMapper {
    void insertTask(Task task);

    void deleteTaskById(int task_id);

    void updateTaskById(Task task);

    Task selectTaskById(int task_id);

    List<Task> selectAll();

    List<Task> selectTaskByName(String task_name);

    List<Task> selectAllDelTask();

    void batchDeleteTask(List<Integer> delList);

    void insertTaskUser(List<UserTask> addUserList);

    void deleteTaskUserById(int task_id,int user_id);

    List<User> selectAllTaskUser(int task_id);

}
