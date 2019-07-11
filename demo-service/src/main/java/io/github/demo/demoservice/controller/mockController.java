package io.github.demo.demoservice.controller;

import io.github.demo.demoservice.integration.WiremockIntegration;
import io.github.demo.demoservice.vo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/mock")
public class mockController {
  private WiremockIntegration wiremockIntegration;

  public mockController(WiremockIntegration wiremockIntegration) {
    this.wiremockIntegration = wiremockIntegration;
  }

  @GetMapping("/{id}")
  public User mockUser(@PathVariable("id") String id) {
    return wiremockIntegration.getUser(id);
  }
}
