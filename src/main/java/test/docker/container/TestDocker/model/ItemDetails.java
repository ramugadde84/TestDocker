package test.docker.container.TestDocker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ItemDetails {

    private Integer id;
    private Integer itemId;
    private String details;
}
