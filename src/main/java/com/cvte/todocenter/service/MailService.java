package com.cvte.todocenter.service;

import com.cvte.todocenter.bean.Mail;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class MailService {

    @Autowired
    private UserService userService;
    public String sendEmailAccount="todocenter@cvte.com";
    public String sendEmailPassword="123456";
    public String sendEmailSMTPHost="smtp.cvte.com";
    //获取邮件收件对象函数
    public InternetAddress[] getAddress(List<UserTask> addUserList) throws IOException
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
    public void sendMail(InternetAddress[] internetAddresses,Mail mail) throws Exception
    {
        Properties properties=new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", sendEmailAccount);
        properties.setProperty("mail.smtp.auth", "true");
        Session session=Session.getInstance(properties);
        session.setDebug(true);
        MimeMessage mimeMessage=createMimeMessage(session,sendEmailAccount,internetAddresses,mail);
        Transport transport=session.getTransport();
        transport.connect(sendEmailAccount,sendEmailPassword);
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

        transport.close();
    }

    //邮件创建函数
    public  MimeMessage createMimeMessage(Session session, String sendMail, InternetAddress[] receiveMail, Mail mail) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, mail.getSendName(), "UTF-8"));
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipients(MimeMessage.RecipientType.TO,receiveMail);
        // 4. Subject: 邮件主题
        message.setSubject(mail.getSubject(), "UTF-8");
        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(mail.getText(), "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }

}
