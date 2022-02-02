package tech.sergeyev.directoryofregions;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("tech.sergeyev.directoryofregions.persistence.dao")
public class DirectoryOfRegionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DirectoryOfRegionsApplication.class, args);
    }

}
