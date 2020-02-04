package br.com.clinic.application.repository;

import br.com.clinic.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(value = " select * from clinic_core.person pe " +
            " inner join clinic_core.patient pa on (pe.id = pa.id) " +
            " where pe.id = :id " +
            " and pe.active = true ", nativeQuery = true)
    Patient findById(@Param("id") final Long id);

    @Query(value = " select * from clinic_core.person pe " +
            " inner join clinic_core.patient pa on (pe.id = pa.id) " +
            " where pe.active = true " +
            " order by pe.id desc " +
            " OFFSET :offset LIMIT :pageSize " , nativeQuery = true)
    List<Patient> findAllWithPage(@Param("offset") final int offset,
                                  @Param("pageSize") final int pageSize);

    @Query(value = " select count(*) from clinic_core.person pe " +
            " inner join clinic_core.patient pa on (pe.id = pa.id) " +
            " where pe.active = true ", nativeQuery = true)
    Integer findCountAll();

    @Modifying
    @Query(value = " update clinic_core.person " +
            " set active = false " +
            " where id = :patientId ", nativeQuery = true)
    void deleteLogic(@Param("patientId") final Long patientId);

}
