package io.github.demo.demoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableFeignClients
@EnableJpaAuditing
public class DemoServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoServiceApplication.class, args);
  }
}
