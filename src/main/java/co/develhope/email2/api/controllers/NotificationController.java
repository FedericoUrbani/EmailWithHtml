package co.develhope.email2.api.controllers;


import co.develhope.email2.emails.services.SendGridMailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NotificationController {

    @Autowired
    SendGridMailer sendGridMailer;


    @GetMapping("/notification/{text}")
    public ResponseEntity sendGridNotification(@PathVariable String text){

        try{
            System.out.println("text to send: " + text);
            if (text == null || text.equals(""))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Text is empty");
            sendGridMailer.sendMailWithHtml(text);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The server is broken :(");
        }
    }

}
