package kth.lab2_journal_core;

import kth.lab2_journal_core.data.condition.ConditionService;
import kth.lab2_journal_core.data.dto.CreateObservationRequest;
import kth.lab2_journal_core.data.dto.CreatePatientRequest;
import kth.lab2_journal_core.data.dto.CreatePractitionerRequest;
import kth.lab2_journal_core.data.encounter.Encounter;
import kth.lab2_journal_core.data.encounter.EncounterService;
import kth.lab2_journal_core.data.observation.Observation;
import kth.lab2_journal_core.data.observation.ObservationService;
import kth.lab2_journal_core.data.organization.Organization;
import kth.lab2_journal_core.data.organization.OrganizationService;
import kth.lab2_journal_core.data.patient.Patient;
import kth.lab2_journal_core.data.patient.PatientService;
import kth.lab2_journal_core.data.practitioner.Practitioner;
import kth.lab2_journal_core.data.practitioner.PractitionerService;
import kth.lab2_journal_core.data.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    private final PatientService patientService;
    private final PractitionerService practitionerService;
    private final ObservationService observationService;
    private final EncounterService encounterService;
    private final OrganizationService organizationService;

    @Autowired
    public Controller(PatientService patientService, PractitionerService practitionerService, ObservationService observationService,
                      EncounterService encounterService, OrganizationService organizationService) {
        this.patientService = patientService;
        this.practitionerService = practitionerService;
        this.observationService = observationService;
        this.encounterService = encounterService;
        this.organizationService = organizationService;
    }

    @GetMapping("/healthz")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/patient/get/byEmail")
    public ResponseEntity<?> getPatientByEmail(@RequestParam String email) {
        Patient patientOpt = patientService.findPatientByEmail(email);

        return new ResponseEntity<>(patientOpt, HttpStatus.OK);
    }

    @GetMapping("/patient/get/all")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PostMapping("/observation/create/description")
    public ResponseEntity<String> saveObservation(@RequestBody String description) {
        try {
            Observation observation = new Observation();
            observation.setDescription(description);
            observationService.saveObservation(observation);
            return ResponseEntity.status(HttpStatus.CREATED).body("Observation saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving observation");
        }
    }

    @PostMapping("/observation/create/full")
    public ResponseEntity<String> createObservation(@RequestBody CreateObservationRequest request) {
        try {
            observationService.createFullObservation(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Observation, Encounter, and Condition created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating observation: " + e.getMessage());
        }
    }


    @GetMapping("/practitioner/get/all")
    public ResponseEntity<List<Practitioner>> getAllPractitioners() {
        List<Practitioner> allPractitioners = practitionerService.getAllPractitioners();
        return new ResponseEntity<>(allPractitioners, HttpStatus.OK);
    }


    @GetMapping("/organization/get/all")
    public ResponseEntity<List<Organization>> getAllOrganizations() {
        List<Organization> organizations = organizationService.getAll();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }


    @GetMapping("/encounter/get/byId")
    public ResponseEntity<List<Encounter>> getAllOrganizations(@RequestParam long patientId) {
        List<Encounter> encounters = encounterService.findByPatientId(patientId);
        return new ResponseEntity<>(encounters, HttpStatus.OK);
    }

    @PostMapping("/practitioner/create")
    public ResponseEntity<String> createPractitioner(@RequestBody CreatePractitionerRequest request) {
        try {
            practitionerService.createPractitioner(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Practitioner saved successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating practitioner: " + e.getMessage());
        }
    }

    @PostMapping("/patient/create")
    public ResponseEntity<String> createPatient(@RequestBody CreatePatientRequest request) {
        try {
            patientService.createPatient(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Patient saved successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating patient: " + e.getMessage());
        }
    }
}
