package com.cvte.todocenter.mapper;

import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTask;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Component(value = "taskMapper")
public interface TaskMapper {
    void insertTask(Task task);

    void deleteTaskById(int taskId, Timestamp lastOpeTime, String operation);

    void updateTaskById(Task task);

    Task selectTaskById(int taskId);

    List<Task> selectAll();

    List<Task> selectTaskByName(String taskName);

    List<Task> selectAllDelTask();

    void batchDeleteTask(List<Integer> delList,Timestamp lastOpeTime, String operation);

    void insertTaskUser(List<UserTask> addUserList);

    void deleteTaskUserById(int taskId,int userId);

    List<User> selectAllTaskUser(int taskId);

}
