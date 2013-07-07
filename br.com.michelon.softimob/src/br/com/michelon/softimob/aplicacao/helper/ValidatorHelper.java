package br.com.michelon.softimob.aplicacao.helper;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang.StringUtils;

import br.com.michelon.softimob.aplicacao.exception.ValidationException;
import br.com.michelon.softimob.tela.dialog.ValidationErrorDialog;

public abstract class ValidatorHelper {

	public static void validar(Object obj) throws ValidationException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<Object>> violations = factory.getValidator().validate(obj);

		String messages = StringUtils.EMPTY;
		for (ConstraintViolation<Object> violation : violations) {
			messages += violation.getMessage() + "\n";
		}

		if (!messages.trim().isEmpty())
			throw new ValidationException(messages);
	}

	public static boolean validarComMensagem(Object obj) {
		try {
			validar(obj);
			return true;
		
		} catch (ValidationException e) {
			new ValidationErrorDialog(ShellHelper.getActiveShell(), e.getMessage()).open();
		}

		return false;
	}
}