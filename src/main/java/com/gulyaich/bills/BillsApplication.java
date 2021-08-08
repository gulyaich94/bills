package com.gulyaich.bills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackages = {"com.gulyaich.bills.model.entity"})
public class BillsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillsApplication.class, args);
    }

}
