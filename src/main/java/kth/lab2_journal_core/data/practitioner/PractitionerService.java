package kth.lab2_journal_core.data.practitioner;

import kth.lab2_journal_core.data.dto.CreatePractitionerRequest;
import kth.lab2_journal_core.data.organization.Organization;
import kth.lab2_journal_core.data.organization.OrganizationService;
import kth.lab2_journal_core.data.user.User;
import kth.lab2_journal_core.data.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PractitionerService {
    private final PractitionerRepository practitionerRepository;
    private final OrganizationService organizationService;
    private final UserService userService;

    @Autowired
    public PractitionerService(PractitionerRepository practitionerRepository, OrganizationService organizationService, UserService userService) {
        this.practitionerRepository = practitionerRepository;
        this.organizationService = organizationService;
        this.userService = userService;
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
        return practitionerRepository.findByUser_Email(email);
    }

    public Practitioner findByEmail(String email) {
        return practitionerRepository.findByUser_Email(email);
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

        User user = userService.findUserByEmail(request.getUserEmail());
        if (user == null) {throw new IllegalArgumentException("User with email " + request.getUserEmail() + " not found.");}
        practitioner.setUser(user);

        savePractitioner(practitioner);
    }
}
