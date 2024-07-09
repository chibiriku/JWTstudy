package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.UserForm;
import com.example.model.UserLoginForm;
import com.example.service.UserDetailsServiceImpl;
import com.example.service.UserService;

@RestController
public class AuthController {
	@Autowired
	private UserService service;
	
	@Autowired
	private UserDetailsServiceImpl detailService;
	
	@PostMapping("/sample")
	@CrossOrigin
    public UserForm post(@RequestBody String user){
		UserForm player = service.find(user);  //idが取得できたか確認しているだけ
        return player;
    }
	
	@PostMapping("/api/login")
	@CrossOrigin(origins = "http://localhost:3000")
    public UserForm login(@RequestBody UserLoginForm loginForm ){
		detailService.loadUserByUsername(loginForm.getUsername());
//		UserForm player = service.find(loginForm);  //idが取得できたか確認しているだけ
        return null;
    }
	
}
