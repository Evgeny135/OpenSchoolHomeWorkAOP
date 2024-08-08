package org.application.openschoolhomeworkaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class OpenSchoolHomeWorkAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenSchoolHomeWorkAopApplication.class, args);
    }

}
