package org.george.pm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.george.pm.mapper")
public class PMApplication {

    public static void main(String[] args) {
        SpringApplication.run(PMApplication.class, args);
    }

}
