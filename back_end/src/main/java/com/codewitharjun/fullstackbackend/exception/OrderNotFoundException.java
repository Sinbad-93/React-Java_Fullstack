package com.codewitharjun.fullstackbackend.exception;

import com.codewitharjun.fullstackbackend.model.User;

public class OrderNotFoundException extends RuntimeException{
	
	    public OrderNotFoundException(Long id) {
	    	super("Could not found the user with id "+ id);
	    }
	}


