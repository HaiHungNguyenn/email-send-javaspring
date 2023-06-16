package com.hunghai.emailverifycode.controller;

//import com.hunghai.emailverifycode.service.MailService;
import com.hunghai.emailverifycode.model.Email;
import com.hunghai.emailverifycode.model.User;
import com.hunghai.emailverifycode.service.EmailSenderService;
import com.hunghai.emailverifycode.util.Utils;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DemoController {
    @Autowired
    private EmailSenderService emailSenderService;
    @Value("${spring.mail.username}")
    private String email;
    @Value("${spring.mail.password}")
    private String password;
    @Autowired
    HttpSession session;

    @RequestMapping("/change_pass")
    public ModelAndView verifycode() throws MessagingException {
        ModelAndView modelAndView = new ModelAndView();
        Email email = new Email();
        String code =Utils.generateRandomString();
        email.setTo("hainhse173100@fpt.edu.vn");
        email.setFrom("nguyenhai181911@gmail.com");
        email.setSubject("Welcome Email from Hai Hung Nguyen");
        email.setTemplate("mail-verifycode.html");
        User user = new User("hunghai","22","12390128");
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", user.getName());
        properties.put("age", user.getAge());
        properties.put("phone", user.getPhone());
        properties.put("verificationCode", code);
        email.setProperties(properties);
        System.out.println(email.getProperties());
        emailSenderService.sendHtmlMessage(email);


        session.setAttribute("verificationCode",code);
        modelAndView.setViewName("check");



        return modelAndView;

    }

    @RequestMapping("/")
    public ModelAndView HomPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping("/verify")
    public ModelAndView verify(@RequestParam("code") String code){
        ModelAndView modelAndView = new ModelAndView();
        String verificationCode = (String)session.getAttribute("verificationCode");
        if(code.equalsIgnoreCase(verificationCode)){

        modelAndView.setViewName("sucess");
        }
        else{
            modelAndView.setViewName("check");
        }

        return modelAndView;
    }

}
