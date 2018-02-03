package br.com.fib.Biblioteca.validator;

import br.com.fib.Biblioteca.beans.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import br.com.fib.Biblioteca.services.UsuarioService;


@Component
public class LoginValidator implements Validator {
	
	@Autowired
	private UsuarioService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Usuario.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		
		Usuario user = (Usuario) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		
		if (userService.findByUsername(user.getUsername()) == null) {
			errors.rejectValue("username", "NotExist.userForm.username");
		}
		
	}
}