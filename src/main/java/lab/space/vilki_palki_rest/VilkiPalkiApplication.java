package lab.space.vilki_palki_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class VilkiPalkiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VilkiPalkiApplication.class, args);
    }

}
