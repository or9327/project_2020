package com.example.chorong.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// the main class of the project
// has to be located at the top of the project
// because the project starts to read the configuration of the project
// from this class where there is @SpringBootApplication annotation

//@EnableJpaAuditing //JPA Auditing 활성화. -> 제거
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
