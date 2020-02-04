package br.com.clinic.gateway.general.gateway;

import br.com.clinic.application.dto.PatientDTO;
import br.com.clinic.application.dto.ResponseDTO;
import br.com.clinic.application.service.PatientService;
import br.com.clinic.domain.Patient;
import br.com.clinic.gateway.general.translator.PatientTranslator;
import br.com.clinic.gateway.general.validator.PatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Component
public class PatientGateway extends GenericGateway{

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientTranslator patientTranslator;

    @Autowired
    private PatientValidator patientValidator;

    public ResponseDTO findAll(final String offset, final String pageSize){
        try {
            final ResponseDTO<PatientDTO> responseDTO = new ResponseDTO<>(offset, pageSize);

            final ResponseDTO<Patient> response = patientService.findAll(responseDTO.getOffset(), responseDTO.getPageSize());

            setResponseAttributes(responseDTO, response);

            responseDTO.setResponseData(patientTranslator.toDTOList(response.getResponseData()));

            return responseDTO;
        } catch (Exception e){
            return new ResponseDTO().analyseExcetion(e);
        }
    }

    private void setResponseAttributes(ResponseDTO<PatientDTO> responseDTO, ResponseDTO<Patient> response) {
        responseDTO.setStatus(response.getStatus());
        responseDTO.setMessage(response.getMessage());
        responseDTO.setCount(response.getCount());
        responseDTO.setErrors(response.getErrors());
    }

    public ResponseDTO findById(final Long patientId){
        try {
            final ResponseDTO<Patient> response = patientService.findById(patientId);

            final ResponseDTO<PatientDTO> responseDTO = new ResponseDTO<>(response.getErrors());

            setResponseAttributes(responseDTO, response);

            if (isEmpty(response.getErrors())) {
                responseDTO.setResponseData(patientTranslator.toDTOList(response.getResponseData()));
            }

            return responseDTO;
        } catch (Exception e) {
            return new ResponseDTO().analyseExcetion(e);
        }
    }

    public ResponseDTO<PatientDTO> save(final PatientDTO patientDTO){
        final ResponseDTO<PatientDTO> responseDTO = patientValidator.validFields(patientDTO);

        if (isNotEmpty(responseDTO.getErrors())) {
            responseDTO.setRequestData(patientDTO);
            return responseDTO;
        }

        final Patient patient = patientService.save(patientTranslator.toEntity(patientDTO));
        responseDTO.setRequestData(patientTranslator.toDTO(patient));
        return responseDTO;
    }

    public ResponseDTO delete(final Long patientId){
        try {
            return patientService.delete(patientId);
        } catch (Exception e) {
            return new ResponseDTO().analyseExcetion(e);
        }
    }

}
