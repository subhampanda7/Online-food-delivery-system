package com.masai.model;

import com.masai.exceptions.LoginException;

public interface LoginService {
	
	public String loginIntoAccount(LoginDTO loginDto)throws LoginException; 
	
	public String logoutFromAccount(String key)throws LoginException; 

}
