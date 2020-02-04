package br.com.clinic.api;

import br.com.clinic.application.dto.PatientDTO;
import br.com.clinic.application.dto.ResponseDTO;
import br.com.clinic.gateway.general.gateway.PatientGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientAPI {

    @Autowired
    private PatientGateway patientGateway;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll(@RequestParam(name = "offset", required = false) final String offset,
                                           @RequestParam(name = "pageSize", required = false) final String pageSize){
        final ResponseDTO responseDTO = patientGateway.findAll(offset, pageSize);
        return ResponseEntity.status(HttpStatus.valueOf(responseDTO.getStatus())).body(responseDTO);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        final ResponseDTO responseDTO = patientGateway.findById(id);
        return ResponseEntity.status(HttpStatus.valueOf(responseDTO.getStatus())).body(responseDTO);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody PatientDTO patientDTO) {
        final ResponseDTO responseDTO = patientGateway.save(patientDTO);
        return ResponseEntity.status(HttpStatus.valueOf(responseDTO.getStatus())).body(responseDTO);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        final ResponseDTO responseDTO = patientGateway.delete(id);
        return ResponseEntity.status(HttpStatus.valueOf(responseDTO.getStatus())).body(responseDTO);
    }

}
