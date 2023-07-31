package demo.mannam_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MannaMProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MannaMProjectApplication.class, args);
    }

    @Bean
    public String uploadPath(){
        return "C:/image/";
    }
}
