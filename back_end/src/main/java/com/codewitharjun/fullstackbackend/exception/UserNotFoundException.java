package com.codewitharjun.fullstackbackend.exception;

import org.springframework.data.domain.Pageable;

import com.codewitharjun.fullstackbackend.model.User;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("Could not found the user with id "+ id);
    }

	public UserNotFoundException(User userId) {
		// TODO Auto-generated constructor stub
	}
	
	public UserNotFoundException(User userId, Pageable pageable) {
		// TODO Auto-generated constructor stub
	}
}
