package br.com.clinic.application.service;

import br.com.clinic.application.dto.ErrorDTO;
import br.com.clinic.application.dto.ResponseDTO;
import br.com.clinic.application.repository.PatientRepository;
import br.com.clinic.domain.Patient;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.lang.String.format;
import static java.util.Objects.nonNull;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
public class PatientService extends ClinicService<Patient, Long>{

    @Autowired
    private PatientRepository patientRepository;

    public ResponseDTO<Patient> findAll(final int offset, final int pageSize){

        final List<Patient> patients = patientRepository.findAllWithPage(offset, pageSize);

        final ResponseDTO<Patient> responseDTO = new ResponseDTO<>();
        responseDTO.setResponseData(patients);
        responseDTO.setCount(patientRepository.findCountAll());

        return responseDTO;
    }

    public ResponseDTO<Patient> findById(final Long patientId){

        final Patient patient = patientRepository.findById(patientId);

        final ResponseDTO<Patient> responseDTO = new ResponseDTO<>();
        if (nonNull(patient) && nonNull(patient.getId())) {
            responseDTO.setResponseData(Lists.newArrayList(patient));
        } else {
            responseDTO.setErrors(Lists.newArrayList(new ErrorDTO("id", patientId.toString(), "Patient not found")));
            responseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            responseDTO.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        }

        return responseDTO;
    }

    public Patient getNewOrLoadSaved(final Long patientId){
        if(nonNull(patientId)){
            return patientRepository.findOne(patientId);
        }
        return new Patient();
    }

    @Transactional
    public Patient save(final Patient patient){
        return patientRepository.save(patient);
    }

    @Transactional
    public ResponseDTO delete(final Long patientId){

        final ResponseDTO responseDTO = findById(patientId);

        if (isEmpty(responseDTO.getErrors())) {
            patientRepository.deleteLogic(patientId);
            responseDTO.setResponseData(Lists.newArrayList(format("Patient %s deleted successful", patientId)));
        }

        return responseDTO;
    }

}
