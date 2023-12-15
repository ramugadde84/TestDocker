package test.docker.container.TestDocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TestDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestDockerApplication.class, args);
	}

}
