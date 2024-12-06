package kth.lab2_journal_core.data.patient;

import kth.lab2_journal_core.data.dto.CreatePatientRequest;
import kth.lab2_journal_core.data.user.User;
import kth.lab2_journal_core.data.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final UserService userService;

    @Autowired
    public PatientService(PatientRepository patientRepository, UserService userService) {
        this.patientRepository = patientRepository;
        this.userService = userService;
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }
    public Patient getPatientByEmail(String email) {
        return patientRepository.findByUser_Email(email);
    }

    public Patient findPatientByEmail(String email) {
        return patientRepository.findByUser_Email(email);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Transactional
    public void createPatient(CreatePatientRequest request) {
        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender());

        User user = userService.findUserByEmail(request.getUserEmail());
        if (user == null) {throw new IllegalArgumentException("User with email " + request.getUserEmail() + " not found.");}
        patient.setUser(user);

        savePatient(patient);
    }

}
