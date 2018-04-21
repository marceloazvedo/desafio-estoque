package br.com.controleestoque.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.exception.ValidationException;

public class ValidationAttributes {
	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	private static final List<String> invalidCpfs = new ArrayList<>();
	static {
		invalidCpfs.add("11111111111");
		invalidCpfs.add("22222222222");
		invalidCpfs.add("33333333333");
		invalidCpfs.add("44444444444");
		invalidCpfs.add("55555555555");
		invalidCpfs.add("66666666666");
		invalidCpfs.add("77777777777");
		invalidCpfs.add("88888888888");
		invalidCpfs.add("99999999999");
	};

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static void isValidCPF(String cpf) throws ValidationException {
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		if (invalidCpfs.contains(cpf)) throw new ValidationException("CPF inválido.", "cpf", cpf);
		if ((cpf == null) || (cpf.length() != 11)) throw new ValidationException("CPF inválido.", "cpf", cpf);

		Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
		boolean valid = cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
		if (!valid) throw new ValidationException("CPF inválido.", "cpf", cpf);
	}

	public static void isValidCNPJ(String cnpj) throws ValidationException {
		if ((cnpj == null) || (cnpj.length() != 14)) throw new ValidationException("CNPJ inválido.", "cnpj", cnpj);

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
		boolean valid = cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
		if (!valid) throw new ValidationException("CNPJ inválido.", "cnpj", cnpj);
	}

	public static void isValidLogin(String login) throws ValidationException {
		if (!Pattern.compile("^[a-z0-9._]{3,}$").matcher(login).find()) 
			throw new ValidationException("Login inválido.", "login", login);
	}
	
	public static void validateAttributeIsNotBlank(String value, String field, String message) throws ValidationException {
		if(null==value||"".equals(value)){
			throw new ValidationException(message, field, value);
		}
	}
	
	public static ErrorsValidationsException buildUniqueErrorValidation(String mensagem, String campo, String valor) {
		return new ErrorsValidationsException(Arrays.asList(new ValidationException[] {new ValidationException(mensagem, campo, valor)}), mensagem);
	}
	
}
