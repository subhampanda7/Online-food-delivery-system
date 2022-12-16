package com.masai.service;

import com.masai.exceptions.LoginException;
import com.masai.model.LoginDTO;

public interface LoginService {
	
	public String loginIntoAccount(LoginDTO loginDto)throws LoginException; 
	
	public String logoutFromAccount(String key)throws LoginException; 

}
