package test.docker.container.TestDocker.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Employee {
    private Long id;
    private String name;
    private String city;
    private Integer salary;

}
