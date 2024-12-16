package kth.lab2_journal_core.data.practitioner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kth.lab2_journal_core.data.encounter.Encounter;
import kth.lab2_journal_core.data.organization.Organization;
import kth.lab2_journal_core.data.role.Role;


import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "T_Practitioner")
public class Practitioner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private LocalDateTime dateOfBirth; //Format of date: "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = "encounter_practitioner",
            joinColumns = @JoinColumn(name = "practitioner_id"),
            inverseJoinColumns = @JoinColumn(name = "encounter_id")
    )
    private List<Encounter> encounters;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Column(nullable = true,unique = true)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String user_email) {
        this.email = user_email;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Encounter> getEncounters() {
        return encounters;
    }

    public void setEncounters(List<Encounter> encounters) {
        this.encounters = encounters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
