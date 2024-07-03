package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.UserForm;
import com.example.service.UserService;

@RestController
public class AuthController {
	@Autowired
	private UserService service;
	
	@PostMapping("/sample")
    public UserForm post(@RequestBody String user){
		UserForm player = service.find("Tom");  //idが取得できたか確認しているだけ
        return player;
    }
}
