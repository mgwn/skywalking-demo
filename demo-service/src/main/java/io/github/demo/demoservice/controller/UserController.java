package io.github.demo.demoservice.controller;

import io.github.demo.demoservice.service.UserService;
import io.github.demo.demoservice.vo.User;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/user")
@Slf4j
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public Flux<User> getAll() {
    return Flux.fromIterable(userService.getAll());
  }

  @PostMapping
  public Mono<User> createUser(@RequestBody User user) {
    return Mono.justOrEmpty(userService.createUser(user));
  }

  @PutMapping("/{id}")
  public Mono<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
    return Mono.justOrEmpty(userService.updateUser(id, user));
  }

  @DeleteMapping("/{id}")
  public Mono<Void> deleteUser(@PathVariable("id") Integer id) {
    return Mono.fromRunnable(() -> userService.deleteUser(id));
  }

  @PostMapping("/batch")
  public Mono<Void> batchPost(@RequestBody List<User> users) {
    return Mono.fromRunnable(() -> userService.batchAddUser(users));
  }

  @PutMapping("/batch")
  public Mono<Void> batchPut(@RequestBody List<User> users) {
    return Mono.fromRunnable(() -> userService.batchUpdateUser(users));
  }
}
