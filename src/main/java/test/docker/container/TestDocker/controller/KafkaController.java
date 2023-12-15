package test.docker.container.TestDocker.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.docker.container.TestDocker.kafka.KafkaProducer;
import test.docker.container.TestDocker.model.Employee;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Autowired
    KafkaProducer producer;


    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestBody Employee employee) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.producer.sendMessage(objectMapper.writeValueAsString(employee));
    }
}
