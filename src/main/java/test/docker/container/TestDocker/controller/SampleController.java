package test.docker.container.TestDocker.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callmy")
public class SampleController {

    Logger logger = LogManager.getLogger(SampleController.class);

    @GetMapping("/name")
    public String getName(@RequestParam("name") String name) {
        try {
            logger.info("Sample Controller getName method exectuted:{}", name);
            getName("test");
            return "hai" + " " + name;
        }catch(NullPointerException nullPointerException){
            logger.error("Exception occurred {}",nullPointerException);
        }

        return "failed";
    }
}
