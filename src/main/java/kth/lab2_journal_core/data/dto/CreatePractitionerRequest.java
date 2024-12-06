package kth.lab2_journal_core.data.dto;

import kth.lab2_journal_core.data.role.Role;

import java.time.LocalDateTime;

public class CreatePractitionerRequest {
    private LocalDateTime dateOfBirth;
    private String name;
    private Role role;
    private Long organizationId;
    private String userEmail;


    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
}
