package com.joalvarez.challengealkemy.validation;

import com.joalvarez.challengealkemy.data.dao.UserDAO;
import com.joalvarez.challengealkemy.data.model.User;
import com.joalvarez.challengealkemy.data.repository.UserRepository;
import com.joalvarez.challengealkemy.validation.general.GenericValidation;
import com.joalvarez.challengealkemy.validation.interfaces.ExistsByUsernameOrEmail;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ExistsByUsernameOrEmailValidation extends GenericValidation<ExistsByUsernameOrEmail, String, UserRepository, User, Long, UserDAO> {

	public ExistsByUsernameOrEmailValidation(UserDAO dao) {
		super(dao);
	}

	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		return !this.dao.existsUsernameOrEmail(s, s);
	}
}
