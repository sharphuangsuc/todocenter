package com.cvte.todocenter.controller;

import com.cvte.todocenter.bean.Mail;
import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTask;
import com.cvte.todocenter.service.MailService;
import com.cvte.todocenter.service.TaskService;
import com.cvte.todocenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import javax.mail.internet.InternetAddress;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/task")
@EnableAsync
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
        String operation="delete the task";
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

    @RequestMapping(value ="/test")
    public void test(){
        return;
    }
    @RequestMapping(value= "/addUser",method = RequestMethod.POST)
    public void addTaskUser(@RequestBody List<UserTask> addUserList) throws SQLException, InterruptedException {
        List<User> userList=new ArrayList<>();
        int len=addUserList.size();
        for(int i=0;i<len;i++)
        {
            int userId=addUserList.get(i).getUserId();
            int taskId=addUserList.get(i).getTaskId();
            User user=userService.getUserById(userId);
            Task task=taskService.getTaskById(taskId);
            if(user==null||task==null)
            {
                throw new SQLException("任务或者成员不存在");
            }
            userList.add(user);
        }
        taskService.addTaskUser(addUserList);
        mailService.sendMailTest();
        //Thread.sleep(1000);
        /*Mail mail=new Mail();
        mail.setSendName("");
        mail.setSubject("");
        mail.setText("");
        mailService.sendMail(userList,mail);*/
    }

    /*@RequestMapping(value= "/addUser",method = RequestMethod.POST)
    public void addTaskUser(@RequestBody List<UserTask> addUserList) throws Exception {

        List<User> userList=new ArrayList<>();
        int len=addUserList.size();
        for(int i=0;i<len;i++)
        {
            int userId=addUserList.get(i).getUserId();
            int taskId=addUserList.get(i).getTaskId();
            User user=userService.getUserById(userId);
            Task task=taskService.getTaskById(taskId);
            if(user==null||task==null)
            {
                throw new Exception("任务或者成员不存在");
            }
            userList.add(user);
        }
        taskService.addTaskUser(addUserList);
        Callable<String> callable=new Callable<String>() {
            @Override
            public String call() throws Exception {
                mailService.sendMailTest();
                return null;
            }
        };
        callable.call();
    }*/


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
