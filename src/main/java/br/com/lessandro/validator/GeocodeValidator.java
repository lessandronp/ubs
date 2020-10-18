package br.com.lessandro.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import br.com.lessandro.resources.exception.ValidationException;

public class GeocodeValidator {

	public static void validateGeocode(String query) throws ValidationException {
		if (StringUtils.isEmpty(query)) {
			throw new ValidationException(System.currentTimeMillis(), HttpStatus.BAD_REQUEST, "Erro de validação",
					"Os parâmetros de latitude e longitude precisam ser informados");
		}

		if (query.split(",").length != 2) {
			throw new ValidationException(System.currentTimeMillis(), HttpStatus.BAD_REQUEST, "Erro de validação",
					"Os parâmetros de latitude e longitude estão incompletos");
		}
	}
}
