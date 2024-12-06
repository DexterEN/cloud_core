package kth.lab2_journal_core.data.observation;

import kth.lab2_journal_core.data.condition.Condition;
import kth.lab2_journal_core.data.condition.ConditionService;
import kth.lab2_journal_core.data.dto.CreateObservationRequest;
import kth.lab2_journal_core.data.encounter.Encounter;
import kth.lab2_journal_core.data.encounter.EncounterService;
import kth.lab2_journal_core.data.organization.OrganizationService;
import kth.lab2_journal_core.data.patient.Patient;
import kth.lab2_journal_core.data.patient.PatientService;
import kth.lab2_journal_core.data.practitioner.Practitioner;
import kth.lab2_journal_core.data.practitioner.PractitionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class ObservationService {
    private final ObservationRepository observationRepository;
    private final ConditionService conditionService;
    private final EncounterService encounterService;
    private final PatientService patientService;
    private final PractitionerService practitionerService;
    private final OrganizationService organizationService;

    @Autowired
    public ObservationService(ObservationRepository observationRepository, ConditionService conditionService, EncounterService encounterService,
                              PatientService patientService, PractitionerService practitionerService,
                              OrganizationService organizationService) {
       this.observationRepository = observationRepository;
        this.conditionService = conditionService;
        this.encounterService = encounterService;
        this.patientService = patientService;
        this.practitionerService = practitionerService;
        this.organizationService = organizationService;
    }

    public void saveObservation(Observation observation) {
        observationRepository.save(observation);
    }

    @Transactional
    public void createFullObservation(CreateObservationRequest request) {
        // Create Condition
        Condition condition = new Condition();
        condition.setName(request.getConditionName());
        condition.setSeverity(request.getSeverity());
        condition = conditionService.saveCondition(condition);

        // Create Encounter
        Encounter encounter = new Encounter();
        encounter.setDateOf(request.getDateOf());
        encounter.setNotes(request.getNotes());

        Patient patient = patientService.findPatientByEmail(request.getPatientEmail());
        encounter.setPatient(patient);

        Practitioner practitioner = practitionerService.findByEmail(request.getPractitionerEmail());
        encounter.setPractitioners(Collections.singletonList(practitioner));
        encounter.setOrganization(organizationService.getById(request.getOrganizationId()));

        encounter = encounterService.saveEncounter(encounter);

        // Create Observation
        Observation observation = new Observation();
        observation.setDescription(request.getDescription());
        observation.setCondition(condition);
        observation.setEncounter(encounter);

        saveObservation(observation);
    }
}
