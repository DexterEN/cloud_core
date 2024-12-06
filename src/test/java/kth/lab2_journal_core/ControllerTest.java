package kth.lab2_journal_core;

import kth.lab2_journal_core.data.dto.CreateObservationRequest;
import kth.lab2_journal_core.data.dto.CreatePatientRequest;
import kth.lab2_journal_core.data.dto.CreatePractitionerRequest;
import kth.lab2_journal_core.data.encounter.EncounterService;
import kth.lab2_journal_core.data.observation.ObservationService;
import kth.lab2_journal_core.data.organization.OrganizationService;
import kth.lab2_journal_core.data.patient.Patient;
import kth.lab2_journal_core.data.patient.PatientService;
import kth.lab2_journal_core.data.practitioner.PractitionerService;
import kth.lab2_journal_core.data.user.User;
import kth.lab2_journal_core.data.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class)
@Import(SecurityConfig.class)  // Import the security config
@ActiveProfiles("test")
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PatientService patientService;

    @MockitoBean
    private PractitionerService practitionerService;

    @MockitoBean
    private ObservationService observationService;

    @MockitoBean
    private EncounterService encounterService;

    @MockitoBean
    private OrganizationService organizationService;

    @MockitoBean
    private UserService userService;


    @Test
    void shouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/healthz")).andExpect(status().isOk()).andExpect(content().string("OK"));
    }

    @Test
    void shouldReturnPatientByEmail() throws Exception {
        // Mock the service behavior
        when(patientService.findPatientByEmail("test@example.com")).thenReturn(new Patient());

        // Perform GET request and verify response
        mockMvc.perform(get("/api/patient/get/byEmail").param("email", "test@example.com"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnAllPatients() throws Exception {
        // Mock the service behavior
        when(patientService.getAllPatients()).thenReturn(Collections.emptyList());

        // Perform GET request and verify response
        mockMvc.perform(get("/api/patient/get/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]")); // Expect empty list as response
    }
/**
    @Test
    void shouldCreateObservation() throws Exception {
        // Perform POST request and verify response
        mockMvc.perform(post("/api/observation/create/description")
                        .content("Sample observation description")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isCreated())
                .andExpect(content().string("Observation saved successfully"));

        // Verify the service method was called
        verify(observationService, times(1)).saveObservation(any());
    }


    @Test
    void shouldCreatePatient() throws Exception {
        // Correct request JSON for creating a patient
        String requestJson = """
                {
                    "dateOfBirth": "1990-01-01T00:00:00",
                    "name": "John Doe",
                    "gender": "Male",
                    "userEmail": "johndoe@example.com"
                }
                """;

        // Mock the userService to return a user when looking up by email
        User mockUser = new User();  // You can set fields for the mock user if needed
        when(userService.findUserByEmail("johndoe@example.com")).thenReturn(mockUser);

        // Mock the behavior of the patientService.createPatient() method
        doNothing().when(patientService).createPatient(any(CreatePatientRequest.class));

        // Perform POST request and verify response
        mockMvc.perform(post("/api/patient/create")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("Patient saved successfully"));

        // Verify that the createPatient method was called once with the correct argument
        verify(patientService, times(1)).createPatient(any(CreatePatientRequest.class));
    }
 **/
    @Test
    void shouldGetAllPractitioners() throws Exception {
        // Mock the service behavior
        when(practitionerService.getAllPractitioners()).thenReturn(Collections.emptyList());

        // Perform GET request and verify response
        mockMvc.perform(get("/api/practitioner/get/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
