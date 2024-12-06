package kth.lab2_journal_core.data.condition;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import kth.lab2_journal_core.data.observation.Observation;

@Entity
@Table(name = "T_Condition")
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "condition")
    private Observation observation;


    @Column(nullable = false, columnDefinition = "int default 1 CHECK (severity BETWEEN 1 AND 10)")
    @Min(1)
    @Max(10)
    private Integer severity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Min(1) @Max(10) Integer getSeverity() {
        return severity;
    }

    public void setSeverity(@Min(1) @Max(10) Integer severity) {
        this.severity = severity;
    }

    public Observation getObservation() {
        return observation;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
