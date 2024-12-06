package kth.lab2_journal_core.data.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public List<Organization> getAll(){
        return organizationRepository.findAll();
    }

    public Organization getById(long id){
        return organizationRepository.findById(id);
    }
}
