package test.docker.container.TestDocker.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import test.docker.container.TestDocker.entity.EmployeeEntity;
import test.docker.container.TestDocker.model.Employee;
import test.docker.container.TestDocker.service.EmployeeService;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public class Consumer {

    @Autowired
    private EmployeeService employeeService;

    private final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);


    /*@KafkaListener(topics = "${spring.kafka.topic-name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    @Async("taskExecutor")*/
    public void consume(ConsumerRecord<?, ?> record, Acknowledgment acknowledgment) throws IOException, InterruptedException {
        String topic = record.topic();
        int partition = record.partition();
        long offset = record.offset();
        Object key = record.key();
        Object value = record.value();

        logger.info("recieved topic message check");
        // Process the message or perform any desired operations
        logger.info("Received message from topic [" + topic + "] at partition " + partition +
                ", offset " + offset + ": key=" + key + ", value=" + value);

        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = objectMapper.readValue((String)value, Employee.class);
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee,employeeEntity);
        employeeService.saveEmployee(employeeEntity);
        acknowledgment.acknowledge();
    }
}
