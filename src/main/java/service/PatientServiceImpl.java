package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Hospitals.Hospitals.entity.Patient;
import com.Hospitals.Hospitals.Repositry.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository repo;

    @Override
    public Patient createPendingPatient(Patient incoming){
        incoming.setPaymentStatus("SUCCESS");
        if (incoming.getAmount()==null) incoming.setAmount(300);
        return repo.save(incoming);
    }

    @Override
    public Optional<Patient> findByMerchant(String merchantOrderId){
        return repo.findByMerchantOrderId(merchantOrderId);
    }

    @Override
    public Patient updatePaymentStatus(String merchantOrderId, String status){
        Optional<Patient> opt = repo.findByMerchantOrderId(merchantOrderId);
        if (opt.isEmpty()) return null;
        Patient p = opt.get();
        p.setPaymentStatus(status);
        return repo.save(p);
    }

    @Override
    public List<Patient> getAllPatients(){
        return repo.findAll();
    }

    @Override
    public Patient getPatientById(Long id){
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Patient> searchByName(String name){
        return repo.findByNameContainingIgnoreCase(name);
    }
}


