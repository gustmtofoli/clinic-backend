package br.com.clinic.gateway.general.validator;

import br.com.clinic.application.dto.ResponseDTO;

public interface ClinicValidator<T> {

    ResponseDTO<T> validFields(final T object);

}
