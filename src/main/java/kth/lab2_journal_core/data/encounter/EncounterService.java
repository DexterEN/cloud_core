package kth.lab2_journal_core.data.encounter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncounterService {
    private final EncounterRepository encounterRepository;

    @Autowired
    public EncounterService(EncounterRepository encounterRepository) {
        this.encounterRepository = encounterRepository;
    }

    public Encounter saveEncounter(Encounter observation) {
        return encounterRepository.save(observation);
    }

    public List<Encounter> findByPatientId(long patientId) {
        return encounterRepository.findByPatientId(patientId);
    }
}
