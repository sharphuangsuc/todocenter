package com.cvte.todocenter.service;

import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTask;
import com.cvte.todocenter.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    public List<Task> getAllTask(){
        return taskMapper.selectAll();
    }

    public void add(Task task){
        taskMapper.insertTask(task);
    }

    public void delTaskById(int taskId, Timestamp lastOpeTime, String operation)
    {
        taskMapper.deleteTaskById(taskId,lastOpeTime,operation);
    }

    public void updateTaskById(Task task)
    {
        taskMapper.updateTaskById(task);
    }

    public Task getTaskById(int taskId)
    {
        return taskMapper.selectTaskById(taskId);
    }

    public List<Task> selectTaskByName(String taskName){
        return taskMapper.selectTaskByName(taskName);
    }

    public List<Task> selectAllDelTask()
    {
        return taskMapper.selectAllDelTask();
    }

    public void delBatch(List<Integer> delList,Timestamp lastOpeTime, String operation)
    {
        taskMapper.batchDeleteTask(delList,lastOpeTime,operation);
    }

    public void addTaskUser(List<UserTask> addUserList)
    {
        taskMapper.insertTaskUser(addUserList);
    }

    public void delTaskUser(int task_id,int userId)
    {
        taskMapper.deleteTaskUserById(task_id,userId);
    }

    public List<User> getTaskUserById(int taskId)
    {
        return taskMapper.selectAllTaskUser(taskId);
    }
}
