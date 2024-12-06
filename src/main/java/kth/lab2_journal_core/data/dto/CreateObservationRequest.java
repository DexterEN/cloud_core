package kth.lab2_journal_core.data.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CreateObservationRequest {

    // Observation fields
    @NotNull(message = "Description is required")
    private String description;

    // Encounter fields
    private LocalDateTime dateOf;
    private String notes;
    private String patientEmail; // ID of the associated patient
    private String practitionerEmail; // List of practitioner IDs
    private Long organizationId; // ID of the associated organization

    // Condition fields
    @NotNull(message = "Condition name is required")
    private String conditionName;

    @NotNull(message = "Severity is required")
    @Min(value = 1, message = "Severity must be at least 1")
    private Integer severity;

    // Getters and Setters
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDateOf() { return dateOf; }
    public void setDateOf(LocalDateTime dateOf) { this.dateOf = dateOf; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPractitionerEmail() {
        return practitionerEmail;
    }

    public void setPractitionerEmail(String practitionerEmail) {
        this.practitionerEmail = practitionerEmail;
    }

    public Long getOrganizationId() { return organizationId; }
    public void setOrganizationId(Long organizationId) { this.organizationId = organizationId; }

    public String getConditionName() { return conditionName; }
    public void setConditionName(String conditionName) { this.conditionName = conditionName; }

    public Integer getSeverity() { return severity; }
    public void setSeverity(Integer severity) { this.severity = severity; }
}
