package com.dyrent.controller;

import com.dyrent.common.Contants;

public class BaseController {

	
	public boolean checkPassword(String password){
		return Contants.PASSWORD.equals(password);
	}
}
