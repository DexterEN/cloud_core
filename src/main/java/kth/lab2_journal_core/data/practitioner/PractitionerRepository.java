package kth.lab2_journal_core.data.practitioner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PractitionerRepository extends JpaRepository<Practitioner, Long> {
    Practitioner findByEmail(String email);
}
