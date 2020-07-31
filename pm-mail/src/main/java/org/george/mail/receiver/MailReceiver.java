package org.george.mail.receiver;

import org.george.pm.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
public class MailReceiver {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MailProperties mailProperties;

    @Autowired
    TemplateEngine templateEngine;

    public static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);

    /**
     * 使用 rabbitmq 监听消息，发送邮件
     * @param employee
     */
    @RabbitListener(queues = "george.mail.welcome")
    public void handler(Employee employee){
        //收到消息，发送邮件
        MimeMessage ms = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(ms);
        try {
            helper.setTo(employee.getEmail());
            helper.setFrom(mailProperties.getUsername());
            helper.setSubject("入职欢迎");
            helper.setSentDate(new Date());
            Context context = new Context();
            context.setVariable("name", employee.getName());
            context.setVariable("pname", employee.getPosition().getName());
            context.setVariable("jname", employee.getJobLevel().getName());
            context.setVariable("dname", employee.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail, true);
            javaMailSender.send(ms);
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("邮件发送失败：" + e.getMessage());
        }
    }
}
