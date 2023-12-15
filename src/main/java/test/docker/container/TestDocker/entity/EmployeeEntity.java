package test.docker.container.TestDocker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="EMPLOYEE")
@Setter
@Getter
public class EmployeeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence_generator")
    @SequenceGenerator(name = "employee_sequence_generator", sequenceName = "EMPLOYEE_SEQ", allocationSize = 1)
    private Long id;
    @Column(name="NAME")
    private String name;
    @Column(name="CITY")
    private String city;
    @Column(name="SALARY")
    private Integer salary;
}
