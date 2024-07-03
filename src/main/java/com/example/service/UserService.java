package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.UserForm;
import com.example.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userMapper;
	
	public UserForm find(String name) {
		return userMapper.findByUsername(name);
	}
}
