package io.github.demo.demoservice.service;

import io.github.demo.demoservice.dao.UserRepository;
import io.github.demo.demoservice.vo.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private UserRepository userRepository;
  private ThreadPoolTaskExecutor threadPoolTaskExecutor;

  public UserService(UserRepository userRepository, ThreadPoolTaskExecutor threadPoolTaskExecutor) {
    this.userRepository = userRepository;
    this.threadPoolTaskExecutor = threadPoolTaskExecutor;
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public User updateUser(Integer id, User user) {
    Optional<User> userOpt = userRepository.findById(id);
    if (!userOpt.isPresent()) {
      throw new IllegalArgumentException("Illegal id: " + id);
    }
    user.setId(userOpt.get().getId());
    return userRepository.save(user);
  }

  public void deleteUser(Integer id) {
    userRepository.deleteById(id);
  }

  @Async
  public void batchAddUser(List<User> users) {
    userRepository.saveAll(users);
  }

  public void batchUpdateUser(List<User> users) {
    threadPoolTaskExecutor.execute(new CustomerCallable(userRepository, users));
  }

  public static class CustomerCallable implements Runnable {
    private UserRepository userRepository;
    private List<User> users;

    CustomerCallable(UserRepository userRepository, List<User> user) {
      this.userRepository = userRepository;
      this.users = user;
    }

    @Override
    public void run() {
      List<User> allById =
          userRepository.findAllById(users.stream().map(User::getId).collect(Collectors.toSet()));
      if (allById.size() < users.size()) {
        throw new IllegalArgumentException("Illegal input for update batch");
      }
      userRepository.saveAll(users);
    }
  }
}
