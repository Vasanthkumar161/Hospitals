package service;

import com.Hospitals.Hospitals.entity.Patient;
import java.util.List;
import java.util.Optional;

public interface PatientService {

    Patient createPendingPatient(Patient incoming);

    Optional<Patient> findByMerchant(String merchantOrderId);

    Patient updatePaymentStatus(String merchantOrderId, String status);

    List<Patient> getAllPatients();

    Patient getPatientById(Long id);

    List<Patient> searchByName(String name);

}
