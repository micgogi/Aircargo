package com.brajesh.cargoauth.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.brajesh.cargoauth.entity.User;


public interface SecurityTokenGenerator {
	
	Map<String,String> generateTokens(User user);

}
