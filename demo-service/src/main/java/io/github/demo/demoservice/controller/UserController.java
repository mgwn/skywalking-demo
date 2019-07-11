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

@RestController
@RequestMapping(path = "/api/user")
@Slf4j
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getAll() {
    return userService.getAll();
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @PutMapping("/{id}")
  public User updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
    return userService.updateUser(id, user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable("id") Integer id) {
    userService.deleteUser(id);
  }

  @PostMapping("/batch")
  public void batchPost(@RequestBody List<User> users) {
    userService.batchAddUser(users);
  }

  @PutMapping("/batch")
  public void batchPut(@RequestBody List<User> users) {
    userService.batchUpdateUser(users);
  }
}
