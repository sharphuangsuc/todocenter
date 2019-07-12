package com.cvte.todocenter.controller;

import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTask;
import com.cvte.todocenter.service.TaskService;
import com.cvte.todocenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
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
        taskService.add(task);
    }

    //删除任务
    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    public void delTaskById(@RequestParam int task_id)
    {
        taskService.delTaskById(task_id);
    }

    //修改更新任务
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(Task task)
    {
        taskService.updateTaskById(task);
    }

    //通过任务id取得任务
    @RequestMapping("/getById")
    public Task getTaskById(@RequestParam int task_id)
    {
        return taskService.getTaskById(task_id);
    }

    //通过任务名获取任务
    @RequestMapping("/getByName")
    public List<Task> getTaskByName(@RequestParam String task_name)
    {
        return taskService.selectTaskByName(task_name);
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
        taskService.delBatch(delList);
    }

    //为任务指派执行用户并发送邮件
    public String sendEmailAccount="todocenter@cvte.com";
    public String sendEmailPassword="123456";
    public String sendEmailSMTPHost="smtp.cvte.com";
    @RequestMapping(value= "/addUser",method = RequestMethod.POST)
    public void addTaskUser(@RequestParam("addUserList") List<UserTask> addUserList)throws Exception
    {
        taskService.addTaskUser(addUserList);
        InternetAddress[] internetAddresses=getAddress(addUserList);
        String task_name=taskService.getTaskById(addUserList.get(0).getTask_id()).getTask_name();
        sendMail(internetAddresses,task_name);
    }

    //撤除任务的执行人员
    @RequestMapping(value = "/delUserTask",method = {RequestMethod.POST})
    public void delTaskUser(@RequestParam int task_id,@RequestParam int user_id)
    {
        taskService.delTaskUser(task_id,user_id);
    }

    //获取任务的所有执行用户
    @RequestMapping("/getTaskUser")
    public List<User> getTaskUserById(@RequestParam int task_id)
    {
        return taskService.getTaskUserById(task_id);
    }

    //获取邮件收件对象函数
    public InternetAddress[] getAddress(List<UserTask> addUserList) throws IOException
    {
        InternetAddress[] internetAddressesList=new InternetAddress[addUserList.size()];
        for(int i=0;i<internetAddressesList.length;i++)
        {
            int user_id=addUserList.get(i).getUser_id();
            User user=userService.getUserById(user_id);
            String email=user.getEmail();
            String user_name=user.getUser_name();
            InternetAddress internetAddress=new InternetAddress(email,user_name,"UTF-8");
            internetAddressesList[i]=internetAddress;
        }
        return  internetAddressesList;
    }
    //发送邮件函数
    public void sendMail(InternetAddress[] internetAddresses,String task_name) throws Exception
    {
        Properties properties=new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", sendEmailAccount);
        properties.setProperty("mail.smtp.auth", "true");
        Session session=Session.getInstance(properties);
        session.setDebug(true);
        MimeMessage mimeMessage=createMimeMessage(session,sendEmailAccount,internetAddresses,task_name);
        Transport transport=session.getTransport();
        transport.connect(sendEmailAccount,sendEmailPassword);
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
    }
    //邮件创建函数
    public  MimeMessage createMimeMessage(Session session, String sendMail, InternetAddress[] receiveMail,String task_name) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "代办任务中心", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        //message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, user_name+"用户", "UTF-8"));

        message.setRecipients(MimeMessage.RecipientType.TO,receiveMail);
        // 4. Subject: 邮件主题
        message.setSubject("任务通知", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent("您有一个新任务："+task_name, "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

}
