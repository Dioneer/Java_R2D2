package Pegas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationRunnerService {
    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunnerService.class, args);
    }
}
