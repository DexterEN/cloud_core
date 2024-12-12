package kth.lab2_journal_core.data.practitioner;

import kth.lab2_journal_core.data.dto.CreatePractitionerRequest;
import kth.lab2_journal_core.data.organization.Organization;
import kth.lab2_journal_core.data.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PractitionerService {
    private final PractitionerRepository practitionerRepository;
    private final OrganizationService organizationService;

    @Autowired
    public PractitionerService(PractitionerRepository practitionerRepository, OrganizationService organizationService) {
        this.practitionerRepository = practitionerRepository;
        this.organizationService = organizationService;
    }

    public Practitioner savePractitioner(Practitioner practitioner) {
        return practitionerRepository.save(practitioner);
    }
    public List<Practitioner> getAllPractitioners() {
        return practitionerRepository.findAll();
    }

    public Optional<Practitioner> getPractitionerById(Long practitionerId) {
        return practitionerRepository.findById(practitionerId);
    }

    public Practitioner getPractitionerByEmail(String email) {
        return practitionerRepository.findByEmail(email);
    }

    public Practitioner findByEmail(String email) {
        return practitionerRepository.findByEmail(email);
    }

    @Transactional
    public void createPractitioner(CreatePractitionerRequest request) {
        Practitioner practitioner = new Practitioner();
        practitioner.setName(request.getName());
        practitioner.setDateOfBirth(request.getDateOfBirth());
        practitioner.setRole(request.getRole());

        Organization organization = organizationService.getById(request.getOrganizationId());
        if (organization == null) {throw new IllegalArgumentException("Invalid organization ID: " + request.getOrganizationId());}
        practitioner.setOrganization(organization);
        practitioner.setEmail(request.getUserEmail());

        savePractitioner(practitioner);
    }
}
