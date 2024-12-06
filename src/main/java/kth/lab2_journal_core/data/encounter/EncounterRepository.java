package kth.lab2_journal_core.data.encounter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncounterRepository extends JpaRepository<Encounter, Long> {
    List<Encounter> findByPatientId(Long patientId);
}