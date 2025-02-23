package com.SReMake;

import org.babyfish.jimmer.client.EnableImplicitApi;
import org.babyfish.jimmer.sql.EnableDtoGeneration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDtoGeneration
@EnableImplicitApi
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
