package io.github.demo.demoservice.integration;

import io.github.demo.demoservice.vo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "wiremock", url = "http://localhost:9082")
public interface WiremockIntegration {

  @PostMapping("/mock/user/{id}")
  public User getUser(@PathVariable("id") String userId);
}
