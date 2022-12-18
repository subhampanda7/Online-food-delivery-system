package com.masai.service;

import com.masai.dto.LoginDTO;
import com.masai.exceptions.LoginException;

public interface LoginService {
	
	public String logIntoAccount(LoginDTO dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;

}
