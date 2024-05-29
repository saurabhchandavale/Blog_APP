package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.payload.UserDto;
public interface UserService {

	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userID);
	UserDto getUserById(Integer userID);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userID);


	
}
