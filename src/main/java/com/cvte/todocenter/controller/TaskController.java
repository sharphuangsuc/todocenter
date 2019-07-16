package com.cvte.todocenter.controller;

import com.cvte.todocenter.bean.Mail;
import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTask;
import com.cvte.todocenter.service.MailService;
import com.cvte.todocenter.service.TaskService;
import com.cvte.todocenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.InternetAddress;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;

    //获取所有的任务
    @RequestMapping("/getAll")
    public List<Task> getAllTask(){
        List<Task> list=taskService.getAllTask();
        return list;
    }

    //添加新任务
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public void addTask(Task task)
    {
        TimeController timeController=new TimeController();
        Timestamp lastOpeTime=timeController.getTime();
        String operation="add new task";
        task.setLastOpeTime(lastOpeTime);
        task.setOperation(operation);
        taskService.add(task);
    }

    //删除任务
    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    public void delTaskById(@RequestParam int taskId)
    {
        TimeController timeController=new TimeController();
        Timestamp lastOpeTime=timeController.getTime();
        String operation="delete the taskm";
        taskService.delTaskById(taskId,lastOpeTime,operation);
    }

    //修改更新任务
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(Task task)
    {
        TimeController timeController=new TimeController();
        Timestamp lastOpeTime=timeController.getTime();
        String operation="update the team";
        task.setLastOpeTime(lastOpeTime);
        task.setOperation(operation);
        taskService.updateTaskById(task);
    }

    //通过任务id取得任务
    @RequestMapping("/getById")
    public Task getTaskById(@RequestParam int taskId)
    {
        return taskService.getTaskById(taskId);
    }

    //通过任务名获取任务
    @RequestMapping("/getByName")
    public List<Task> getTaskByName(@RequestParam String taskName)
    {
        return taskService.selectTaskByName(taskName);
    }

    //获取所有已删除任务
    @RequestMapping("/getAllDel")
    public List<Task> getAllDel()
    {
        return taskService.selectAllDelTask();
    }

    //批量删除任务
    @RequestMapping("/delBatch")
    public void delBatch(List<Integer> delList)
    {
        TimeController timeController=new TimeController();
        Timestamp lastOpeTime=timeController.getTime();
        String operation="delete the task";
        taskService.delBatch(delList,lastOpeTime,operation);
    }

    @RequestMapping(value= "/addUser",method = RequestMethod.POST)
    @Transactional
    public void addTaskUser(@RequestParam("addUserList") List<UserTask> addUserList)throws Exception
    {
        for(UserTask userTask:addUserList)
        {
            int userId=userTask.getUserId();
            int taskId=userTask.getTaskId();
            User user=userService.getUserById(userId);
            Task task=taskService.getTaskById(taskId);
            if(user==null||task==null||user.getIsDelete()==1||task.getIsDelete()==1)
            {
                throw new Exception("任务或者成员不存在");
            }
        }
        taskService.addTaskUser(addUserList);
        InternetAddress[] internetAddresses=mailService.getAddress(addUserList);
        Mail mail=new Mail();
        mail.setSendName("");
        mail.setSubject("");
        mail.setText("");
        mailService.sendMail(internetAddresses,mail);
    }

    //撤除任务的执行人员
    @RequestMapping(value = "/delUserTask",method = {RequestMethod.POST})
    public void delTaskUser(@RequestParam int taskId,@RequestParam int userId)
    {
        taskService.delTaskUser(taskId,userId);
    }

    //获取任务的所有执行用户
    @RequestMapping("/getTaskUser")
    public List<User> getTaskUserById(@RequestParam int taskId)
    {
        return taskService.getTaskUserById(taskId);
    }

}
