package com.brajesh.cargoauth.service;

import com.brajesh.cargoauth.entity.User;
import com.brajesh.cargoauth.exception.UserAlreadyExistException;
import com.brajesh.cargoauth.exception.UserNotFoundException;

public interface UserService {

	public boolean saveUser(User user)throws UserAlreadyExistException;
	
	public User findByUserIdAndPassword(String userId,String password) throws UserNotFoundException;
}
