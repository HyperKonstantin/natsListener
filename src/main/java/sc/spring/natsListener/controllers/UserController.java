package sc.spring.natsListener.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sc.spring.natsListener.entities.User;
import sc.spring.natsListener.repositories.UserRepository;
import sc.spring.natsListener.services.NatsListenService;

import java.awt.desktop.UserSessionEvent;

@RestController
public class UserController {

    @Autowired
    private NatsListenService natsListenService;

    @GetMapping("/user")
    public ResponseEntity<?> printUser(){
        User user = natsListenService.getCurrentUser();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
