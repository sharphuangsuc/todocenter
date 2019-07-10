package com.cvte.todocenter.service;

import com.cvte.todocenter.bean.Task;
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

    public void updateTaskById(int task_id,Task task)
    {
        taskMapper.updateTaskById(task_id,task);
    }

    public Task getTaskById(int task_id)
    {
        return taskMapper.selectTaskById(task_id);
    }
}
