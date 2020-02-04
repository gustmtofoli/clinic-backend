package br.com.clinic.gateway.general.translator;

import br.com.clinic.application.dto.PatientDTO;
import br.com.clinic.application.service.PatientService;
import br.com.clinic.domain.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientTranslator implements ClinicTranslator<Patient, PatientDTO> {

    @Autowired
    private PatientService patientService;

    @Override
    public Patient toEntity(final PatientDTO patientDTO) {
        final Patient patient = patientService.getNewOrLoadSaved(patientDTO.getId());
        patient.setName(patientDTO.getName());
        patient.setLastName(patientDTO.getLastName());
        patient.setCpf(patientDTO.getCpf());
        patient.setRg(patientDTO.getRg());
        patient.setBirthDate(patientDTO.getBirthDate());
        patient.setHomePhone(patientDTO.getHomePhone());
        patient.setCellPhone(patientDTO.getCellPhone());
        patient.setEmail(patientDTO.getEmail());
        patient.setAddress(patientDTO.getAddress());
        patient.setAddressNumber(patientDTO.getAddressNumber());
        patient.setAddressComplement(patientDTO.getAddressComplement());
        patient.setAddressReference(patientDTO.getAddressReference());
        patient.setMotherName(patientDTO.getMotherName());
        patient.setFatherName(patientDTO.getFatherName());

        return patient;
    }

    @Override
    public PatientDTO toDTO(final Patient patient) {
        final PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setName(patient.getName());
        patientDTO.setLastName(patient.getLastName());
        patientDTO.setCpf(patient.getCpf());
        patientDTO.setRg(patient.getRg());
        patientDTO.setBirthDate(patient.getBirthDate());
        patientDTO.setHomePhone(patient.getHomePhone());
        patientDTO.setCellPhone(patient.getCellPhone());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setAddressNumber(patient.getAddressNumber());
        patientDTO.setAddressComplement(patient.getAddressComplement());
        patientDTO.setAddressReference(patient.getAddressReference());
        patientDTO.setMotherName(patient.getMotherName());
        patientDTO.setFatherName(patient.getFatherName());

        return patientDTO;
    }

    @Override
    public List<Patient> toEntityList(List<PatientDTO> patientDTOS) {
        return patientDTOS.stream().map(patient -> toEntity(patient)).collect(Collectors.toList());
    }

    @Override
    public List<PatientDTO> toDTOList(final List<Patient> patients) {
        return patients.stream().map(patient -> toDTO(patient)).collect(Collectors.toList());
    }

}
