package zc.backend.controllers;


import antlr.collections.List;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zc.backend.config.EmailConfig;
import zc.backend.modles.Feedback;
import zc.backend.services.UserServiceImpl;

import javax.mail.internet.AddressException;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("api/feedback")
public class FeedbackController {

    private EmailConfig emailCfg ;
    private UserServiceImpl userService ;
public  FeedbackController(EmailConfig emailConfig,UserServiceImpl userService) {
    this.emailCfg=emailConfig;
    this.userService=userService;
}

    @PostMapping
    public void sendFeedback(@RequestBody Feedback feedback,
                                 BindingResult bindingResult) throws ValidationException, AddressException {
      if(bindingResult.hasErrors()){
          throw  new ValidationException("FeedBack is  not  valid");
      }
      //create mail  sender
        JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl() ;
      javaMailSender.setHost(this.emailCfg.getHost());
      javaMailSender.setPort(this.emailCfg.getPort());
      javaMailSender.setUsername(this.emailCfg.getUsername());
      javaMailSender.setPassword(this.emailCfg.getPassword());
      //create mail instance
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());//the sender of the  email
         var data=userService.getUsers();
        data.forEach((user) -> {
            mailMessage.setTo(user.getUsername());
            mailMessage.setSubject("cas  positif Covid 19");
            mailMessage.setText("sorry i am gonna  inform you that xx  is  tested  positif");
            javaMailSender.send(mailMessage);
        });

        //mailMessage.setTo("email1@test.com");


        //send this mail


    }

}
