package kth.lab2_journal_core.data.organization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kth.lab2_journal_core.data.encounter.Encounter;
import kth.lab2_journal_core.data.location.Location;
import kth.lab2_journal_core.data.practitioner.Practitioner;

import java.util.List;

@Entity
@Table(name = "T_Organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @JsonIgnore
    @OneToMany(mappedBy = "organization")
    private List<Encounter> Encounters;

    @JsonIgnore
    @OneToMany(mappedBy = "organization")
    private List<Practitioner> practitioners;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Practitioner> getPractitioners() {
        return practitioners;
    }

    public void setPractitioners(List<Practitioner> practitioners) {
        this.practitioners = practitioners;
    }

    public List<Encounter> getEncounters() {
        return Encounters;
    }

    public void setEncounters(List<Encounter> encounters) {
        Encounters = encounters;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
