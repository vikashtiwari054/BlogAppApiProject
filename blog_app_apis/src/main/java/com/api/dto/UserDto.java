package com.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
public class UserDto {
	
	private int id;
	
	@NotBlank
	@Size(min=4,message="Username must be of 4 character")
	private String name;
	
	@Email(message="email address is not valid")
	private String email;
	
	@NotBlank
	//@Size(min=4,max=20,message="password length should be 4 to 10 character")
	@Pattern( regexp = "^[a-zA-Z0-9@]{4,30}$",message="password length should be 4 to 30 character")
	private String password;
	
	
	@NotBlank
    private String about;
		
}
