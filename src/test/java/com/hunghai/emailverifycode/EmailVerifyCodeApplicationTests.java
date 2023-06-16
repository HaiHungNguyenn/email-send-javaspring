package com.hunghai.emailverifycode;

import com.hunghai.emailverifycode.model.Email;
import com.hunghai.emailverifycode.model.User;
import com.hunghai.emailverifycode.service.EmailSenderService;
import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SpringBootTest
class EmailVerifyCodeApplicationTests {
    @Autowired
    private EmailSenderService emailSenderService;
    @Value("${spring.mail.username}")
    private String email;
    @Value("${spring.mail.password}")
    private String password;
    @Test
    void contextLoads() {

        // Thiết lập thông tin tài khoản email nguồn
        String senderEmail = email;
        String senderPassword = password;

        // Thiết lập thông tin người nhận và nội dung email
        String recipientEmail = "hainhse173100@fpt.edu.vn";
        String emailSubject = "Hello from JavaMail";
        String emailContent = "This is a test email sent from Java.";

        // Thiết lập các thuộc tính cấu hình cho phiên làm việc JavaMail
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Tạo phiên làm việc JavaMail với thông tin đăng nhập
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(session);

            // Thiết lập thông tin người gửi, người nhận và tiêu đề
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(emailSubject);

            // Thiết lập nội dung email
            message.setText(emailContent);

            // Gửi email
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendHtmlMessageTest() throws MessagingException {
        Email email = new Email();
        email.setTo("hainhse173100@fpt.edu.vn");
        email.setFrom("nguyenhai181911@gmail.com");
        email.setSubject("Welcome Email from Hai Hung Nguyen");
        email.setTemplate("mail-template.html");
        User user = new User("hunghai","22","12390128");
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", user.getName());
        properties.put("age", user.getAge());
        properties.put("phone", user.getPhone());
        email.setProperties(properties);
        System.out.println(email.getProperties());

        Assertions.assertDoesNotThrow(() -> emailSenderService.sendHtmlMessage(email));
    }

}
