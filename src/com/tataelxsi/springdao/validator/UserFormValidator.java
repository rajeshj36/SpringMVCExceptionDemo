package com.tataelxsi.springdao.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tataelxsi.springdao.dto.User;

public class UserFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		// TODO Auto-generated method stub
		return User.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.address");
	

	}

}
