package com.api.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import com.api.dto.UserDto;
import com.api.entity.User;
import com.api.exception.ResourceNotFoundException;
import com.api.repo.UserRepo;
import com.api.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment environment;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user=this.dtoToUser(userDto);
		
		User user2=this.repo.save(user);
		return this.userToDto(user2);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user=this.repo.findById(userId)
		.orElseThrow(()-> new ResourceNotFoundException("User", "Id",userId));
		
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		
	    User updatedUser=this.repo.save(user);
        UserDto dto=this.userToDto(updatedUser);		    
		    
		return dto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
        User user=this.repo.findById(userId)
        .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        
        
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUserDtos() {
		
	//List<User> users= this.repo.findAll();
	
	//List<UserDto> dtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
	   
		 String sql =environment.getProperty("SQL_QUERY");

	     List<UserDto> us=jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserDto>(UserDto.class));

	     return us;
	}

	@Override
	public void deleteUser(Integer userId) {
		
	User user=	this.repo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id", userId));
		
	   this.repo.delete(user);
	}
	
	@SuppressWarnings("unused")
	private User  dtoToUser(UserDto dto) { 
//		     User user=new User();
//             user.setId(dto.getId());
//         	 user.setName(dto.getName());
//         	 user.setAbout(dto.getAbout());
//         	 user.setEmail(dto.getEmail());
//		     user.setPassword(dto.getPassword());
//		     return user;
		
		   User user=this.modelMapper.map(dto, User.class);
	
		   return user;	
	}
	@SuppressWarnings("unused")
	private UserDto userToDto(User user) {
		
//		UserDto userDto=new UserDto();
//		
//		userDto.setAbout(user.getAbout());
//		userDto.setEmail(user.getEmail());
//		userDto.setId(user.getId());
//		userDto.setPassword(user.getPassword());
//		userDto.setName(user.getName());
//		
//		return userDto;
		
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
