package com.redhat.foo.service;

import java.util.List;

import com.redhat.foo.entity.Foo;
import com.redhat.foo.repository.FooRepository;
import com.redhat.foo.utils.Delay;
import com.redhat.foo.utils.tracing.tracing.Traceable;

import org.springframework.stereotype.Service;

@Service
public class FooService {

  private final FooRepository FooRepository;

  public FooService(FooRepository FooRepository) {
    this.FooRepository = FooRepository;
  }

  @Traceable
  public List<Foo> getAll(){
    Delay.execute();
    return FooRepository.findAll();    
  }
  
}
