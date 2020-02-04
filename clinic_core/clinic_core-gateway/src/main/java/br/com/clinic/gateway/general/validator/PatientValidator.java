package br.com.clinic.gateway.general.validator;

import br.com.clinic.application.dto.ErrorDTO;
import br.com.clinic.application.dto.PatientDTO;
import br.com.clinic.application.dto.ResponseDTO;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class PatientValidator implements ClinicValidator<PatientDTO>{


    @Override
    public ResponseDTO<PatientDTO> validFields(final PatientDTO patientDTO) {

        final List<ErrorDTO> errors = Lists.newArrayList();

        if (isNull(patientDTO.getName())) {
            errors.add(new ErrorDTO("name", null, "Field not null"));
        }

        return new ResponseDTO<>(errors);
    }

}
