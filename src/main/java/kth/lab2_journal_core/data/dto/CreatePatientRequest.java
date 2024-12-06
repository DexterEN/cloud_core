package kth.lab2_journal_core.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kth.lab2_journal_core.data.role.Role;

import java.time.LocalDateTime;

public class CreatePatientRequest {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateOfBirth;
    private String name;
    private String gender;
    private String userEmail;

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
