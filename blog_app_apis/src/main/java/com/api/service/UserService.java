package com.api.service;

import java.util.List;

import com.api.dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto>  getAllUserDtos();
	
	void deleteUser(Integer userId);
	
	
}
