package com.guet.qiusuo.fruittravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FruitTravelApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(FruitTravelApplication.class, args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
