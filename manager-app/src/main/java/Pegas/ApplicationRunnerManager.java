package Pegas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationRunnerManager {
    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunnerManager.class, args);
    }
}
