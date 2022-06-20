package com.xingyu.yygh.msm.utils;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailUtils {

    public static final String SEND_EMAIL = "2443604920@qq.com";

    public static final String PASSWORD = "mdffbheovfkdeceb";


    public static boolean sendEmail(String emailAccount,String subject, String message){
        HtmlEmail email=new HtmlEmail();//创建一个HtmlEmail实例对象
        email.setHostName("smtp.qq.com");//邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setCharset("utf-8");//设置发送的字符类型

        try{
            email.addTo(emailAccount);//设置收件人
            email.setFrom(EmailUtils.SEND_EMAIL,"yygh_admin");//发送人的邮箱为自己的，用户名可以随便填
            email.setAuthentication(EmailUtils.SEND_EMAIL,EmailUtils.PASSWORD);//设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)

            email.setSubject(subject);//设置发送主题
            email.setMsg(message);//设置发送内容
            email.send();//进行发送
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }


    public static boolean sendEmail(String emailAccount, String subject, String msg, JavaMailSender javaMailSender, MailProperties mailProperties) {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);

            messageHelper.setFrom(mailProperties.getUsername());
            messageHelper.setTo(emailAccount);
            messageHelper.setSubject(subject);
            messageHelper.setText(msg);

            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *@Author: ZongMao on 2020/4/2 20:11
     *发送html文本邮件
     *@return
     */
    public static void sendHtmlMail(String emailAccount, String subject, String msg, JavaMailSender javaMailSender, MailProperties mailProperties) {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMailMessage,true,"utf-8");

            messageHelper.setFrom(mailProperties.getUsername());
            messageHelper.setTo(emailAccount);
            messageHelper.setSubject(subject);
            messageHelper.setText(msg,true);

            javaMailSender.send(mimeMailMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }




}
