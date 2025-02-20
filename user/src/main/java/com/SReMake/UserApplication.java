package com.SReMake;

import org.babyfish.jimmer.client.EnableImplicitApi;
import org.babyfish.jimmer.sql.EnableDtoGeneration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDtoGeneration
@EnableImplicitApi
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
