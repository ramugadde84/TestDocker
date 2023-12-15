package test.docker.container.TestDocker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.docker.container.TestDocker.kafka.KafkaProducer;
import test.docker.container.TestDocker.model.PersonalDetails;
import test.docker.container.TestDocker.model.PersonalDetailsResponse;
import test.docker.container.TestDocker.service.DetailsService;

import java.util.List;

@RestController
@RequestMapping("api/v1/details")

public class DetailsController {
    private final Logger logger = LoggerFactory.getLogger(DetailsController.class);

    @Autowired
    private DetailsService detailsService;

    @GetMapping("/list")
    public List<PersonalDetails> fetchDetailsList(@RequestParam("name") String name) throws InterruptedException {
        Thread.sleep(5000);
        return detailsService.fetchDetails(name);
    }

    @PostMapping("/save")
    public ResponseEntity<PersonalDetailsResponse> saveDetails(@RequestBody PersonalDetails personalDetails) throws InterruptedException, JsonProcessingException {
        logger.info("Personal Details saved:"+ new ObjectMapper().writeValueAsString(personalDetails));
        PersonalDetailsResponse personalDetailsResponse = new PersonalDetailsResponse();
        personalDetailsResponse.setMessage("success");
        return new ResponseEntity<>(personalDetailsResponse, HttpStatus.OK);
    }


}
