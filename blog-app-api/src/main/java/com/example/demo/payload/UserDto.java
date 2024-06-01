package com.example.demo.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int Id;
	@NotEmpty
	@Size(min = 4, message="Username must be of 4 characters")
	private String name;
	@Email(message = "Email id is not valid")
	private String email;
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password should contain minium 3 and maximum 10 characters")
	private String password;
	@NotEmpty
	private String about;
	

}