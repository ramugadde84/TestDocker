package test.docker.container.TestDocker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Details {
    public String name;
    public List<PersonalDetails> personalDetailsList;
}
