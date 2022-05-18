package com.redhat.foo.controller;

import java.util.List;

import com.redhat.foo.entity.Foo;
import com.redhat.foo.service.FooService;
import com.redhat.foo.utils.Delay;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/foo")
public class FooController {

  private final FooService service;

  public FooController(FooService service) {
    Delay.execute();
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Foo>> getFoos() {
    List<Foo> Foos = service.getAll();
    HttpStatus status = HttpStatus.OK;
    if (0 == Foos.size()) status = HttpStatus.NO_CONTENT;
    return ResponseEntity.status(status).body(Foos);
  }

}
