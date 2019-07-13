package com.cvte.todocenter.controller;

import com.cvte.todocenter.bean.Task;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTask;
import com.cvte.todocenter.service.Mail;
import com.cvte.todocenter.service.TaskService;
import com.cvte.todocenter.service.TimeService;
import com.cvte.todocenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    //@Autowired
    //private UserService userService;
    @Autowired
    private TimeService timeService;

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
        int taskId=task.getTaskId();
        TimeController timeController=new TimeController();
        Timestamp lastOpeTime=timeController.getTime();
        String operation="add new team";
        timeService.updateTaskTime(lastOpeTime,taskId,operation);
    }

    //删除任务
    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    public void delTaskById(@RequestParam int taskId)
    {
        taskService.delTaskById(taskId);
    }

    //修改更新任务
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(Task task)
    {
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
        taskService.delBatch(delList);
    }

    //为任务指派执行用户并发送邮件
    //public String sendEmailAccount="todocenter@cvte.com";
    //public String sendEmailPassword="123456";
    //public String sendEmailSMTPHost="smtp.cvte.com";
    @RequestMapping(value= "/addUser",method = RequestMethod.POST)
    public void addTaskUser(@RequestParam("addUserList") List<UserTask> addUserList)throws Exception
    {
        taskService.addTaskUser(addUserList);
        String text="被指派为任务成员";
        Mail mail =new Mail();
        InternetAddress[] internetAddresses=mail.getAddress(addUserList);
        String taskName=taskService.getTaskById(addUserList.get(0).getTaskId()).getTaskName();
        mail.sendMail(internetAddresses,taskName,text);
        //sendMail(internetAddresses,taskName);
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

    //获取邮件收件对象函数
    /*public InternetAddress[] getAddress(List<UserTask> addUserList) throws IOException
    {
        InternetAddress[] internetAddressesList=new InternetAddress[addUserList.size()];
        for(int i=0;i<internetAddressesList.length;i++)
        {
            int userId=addUserList.get(i).getUserId();
            User user=userService.getUserById(userId);
            String email=user.getEmail();
            String userName=user.getUserName();
            InternetAddress internetAddress=new InternetAddress(email,userName,"UTF-8");
            internetAddressesList[i]=internetAddress;
        }
        return  internetAddressesList;
    }
    //发送邮件函数
    public void sendMail(InternetAddress[] internetAddresses,String taskName) throws Exception
    {
        Properties properties=new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", sendEmailAccount);
        properties.setProperty("mail.smtp.auth", "true");
        Session session=Session.getInstance(properties);
        session.setDebug(true);
        MimeMessage mimeMessage=createMimeMessage(session,sendEmailAccount,internetAddresses,taskName);
        Transport transport=session.getTransport();
        transport.connect(sendEmailAccount,sendEmailPassword);
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
    }
    //邮件创建函数
    public  MimeMessage createMimeMessage(Session session, String sendMail, InternetAddress[] receiveMail,String taskName) throws Exception {
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
        message.setContent("您有一个新任务："+taskName, "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }*/

}
