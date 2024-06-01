package com.example.demo.services.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.UserDto;
import com.example.demo.reporitries.UserRepo;
import com.example.demo.services.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;


	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		System.out.println("Inside method create user----------------");
		User user = this.userDtoToUserEntity(userDto);
		System.out.println(user.getName());
		User savedUser= this.userRepo.save(user);
		
		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userID) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("User","Id", userID));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = userRepo.save(user);
		return this.userToUserDto(updatedUser);
		
	}

	@Override
	public UserDto getUserById(Integer userID) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("User","Id", userID));
		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDto = users.stream().map(user -> this.userToUserDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(Integer userID) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("User","Id", userID));
		this.userRepo.delete(user);
		
	}
	
	public User userDtoToUserEntity(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		/*user.setId(userDto.getId());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		System.out.println(user.getAbout()+"---------------------------------");*/
		return user;
			
		
		
	}
	
	public UserDto userToUserDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		/*userDto.setName(user.getName());
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());*/
		return userDto;
	}

}
