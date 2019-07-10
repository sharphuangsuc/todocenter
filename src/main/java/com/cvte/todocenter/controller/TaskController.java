package com.cvte.todocenter.controller;

import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/getAll")
    public List<Task> getAllTask(){
        List<Task> list=taskService.getAllTask();
        return list;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addTask(@RequestBody Task task)
    {
        taskService.add(task);
    }

    @RequestMapping(value = "/del",method = {RequestMethod.GET,RequestMethod.POST})
    public void delTaskById(@RequestParam int task_id)
    {
        taskService.delTaskById(task_id);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(@RequestParam int task_id,@RequestBody Task task)
    {
        taskService.updateTaskById(task_id,task);
    }

    @RequestMapping("/getById")
    public Task getTaskById(@RequestParam int task_id)
    {
        return taskService.getTaskById(task_id);
    }

}
