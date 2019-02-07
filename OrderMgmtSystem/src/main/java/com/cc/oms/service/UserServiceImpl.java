package com.cc.oms.service;

import org.springframework.stereotype.Service;

import com.cc.oms.entities.User;

@Service
public class UserServiceImpl implements UserService {
	@Override
	public String addUser(User user) {
		// Call Repository layer
		return "User added successfully";
	}

}
