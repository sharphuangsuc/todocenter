package com.cvte.todocenter.service;

import com.cvte.todocenter.bean.Mail;
import com.cvte.todocenter.bean.User;
import com.cvte.todocenter.bean.UserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class MailService {

    private String sendEmailAccount = "todocenter@cvte.com";
    private String sendEmailPassword = "123456";
    private String sendEmailSMTPHost = "smtp.cvte.com";

    //获取邮件收件对象函数
    private InternetAddress[] getAddress(List<User> userList) throws IOException {
        InternetAddress[] internetAddressesList = new InternetAddress[userList.size()];
        for (int i = 0; i < internetAddressesList.length; i++) {
            InternetAddress internetAddress = new InternetAddress(userList.get(i).getEmail(), userList.get(i).getUserName(), "UTF-8");
            internetAddressesList[i] = internetAddress;
        }
        return internetAddressesList;
    }

    //发送邮件函数
    public void sendMail(List<User> userList, Mail mail) throws MessagingException, IOException {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", sendEmailAccount);
        properties.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties);
        session.setDebug(true);
        MimeMessage mimeMessage = createMimeMessage(session, sendEmailAccount, getAddress(userList), mail);
        Transport transport = null;
        try {
            transport = session.getTransport();
            transport.connect(sendEmailAccount, sendEmailPassword);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                transport.close();
            }
        }

    }

    //邮件创建函数
    private MimeMessage createMimeMessage(Session session, String sendMail, InternetAddress[] receiveMail, Mail mail) throws MessagingException {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人
        try {
            message.setFrom(new InternetAddress(sendMail, mail.getSendName(), "UTF-8"));
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipients(MimeMessage.RecipientType.TO, receiveMail);
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

    @Async
    public void sendMailTest() throws InterruptedException {
        Thread.sleep(1000);
    }

}
