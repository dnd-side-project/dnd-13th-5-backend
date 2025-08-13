package com.dnd.sub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DndApplication {

    public static void main(String[] args) {
        SpringApplication.run(DndApplication.class, args);
    }

}
