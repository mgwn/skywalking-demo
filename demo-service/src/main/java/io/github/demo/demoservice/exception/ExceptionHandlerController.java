package io.github.demo.demoservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(Exception.class)
  @Trace
  Mono<ResponseEntity<String>> exceptionHandler(Exception ex) {
    ActiveSpan.error(ex);
    log.error("Get exception ", ex);
    return Mono.just(new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST));
  }
}
