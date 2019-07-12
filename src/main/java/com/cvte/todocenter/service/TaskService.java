package com.cvte.todocenter.service;

import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTask;
import com.cvte.todocenter.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void delTaskById(int task_id)
    {
        taskMapper.deleteTaskById(task_id);
    }

    public void updateTaskById(Task task)
    {
        taskMapper.updateTaskById(task);
    }

    public Task getTaskById(int task_id)
    {
        return taskMapper.selectTaskById(task_id);
    }

    public List<Task> selectTaskByName(String task_name){
        return taskMapper.selectTaskByName(task_name);
    }

    public List<Task> selectAllDelTask()
    {
        return taskMapper.selectAllDelTask();
    }

    public void delBatch(List<Integer> delList)
    {
        taskMapper.batchDeleteTask(delList);
    }

    public void addTaskUser(List<UserTask> addUserList)
    {
        taskMapper.insertTaskUser(addUserList);
    }

    public void delTaskUser(int task_id,int user_id)
    {
        taskMapper.deleteTaskUserById(task_id,user_id);
    }

    public List<User> getTaskUserById(int task_id)
    {
        return taskMapper.selectAllTaskUser(task_id);
    }
}
