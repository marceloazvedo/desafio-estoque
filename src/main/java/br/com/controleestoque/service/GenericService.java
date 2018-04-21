package br.com.controleestoque.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.exception.ValidationException;
import br.com.controleestoque.model.enums.MensagemAPI;
import br.com.controleestoque.util.StringUtils;

@Service
abstract class GenericService {

	@Autowired
	private Validator validator;

	public void validateEntity(Object object) throws ErrorsValidationsException {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
		List<ValidationException> erros = new ArrayList<>();
		for (ConstraintViolation<Object> violation : constraintViolations) {
			String value = "";
			if (violation.getInvalidValue() instanceof Calendar) {
				value = StringUtils.getDataCompleta((Calendar) violation.getInvalidValue());
			} else {
				value = violation.getInvalidValue() == null ? "" : violation.getInvalidValue().toString();
			}
			erros.add(new ValidationException(violation.getMessage(), violation.getPropertyPath().toString(), value));
		}
		erros.forEach(err -> {System.out.println(err);});
		if (!erros.isEmpty())
			throw new ErrorsValidationsException(MensagemAPI.ERRO_VALIDACAO, erros, "Erro ao tentar persistir informação");
	}

}
