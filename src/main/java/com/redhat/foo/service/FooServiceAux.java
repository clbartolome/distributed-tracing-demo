package com.redhat.foo.service;

import java.util.List;

import com.redhat.foo.entity.Foo;
import com.redhat.foo.repository.FooRepository;
import com.redhat.foo.utils.Delay;
import com.redhat.foo.utils.tracing.tracing.Traceable;

import io.opentracing.Span;
import io.opentracing.Tracer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FooServiceAux {


  @Autowired
  Tracer tracer;

  private final FooRepository FooRepository;

  public FooServiceAux(FooRepository FooRepository) {
    this.FooRepository = FooRepository;
  }

  @Traceable
  public List<Foo> getAllAux(){
    Delay.execute();
    FooRepository.findAll();
    return this.callRepositoryAux();    
  }

  private List<Foo> callRepositoryAux() {
    Span newSpan = tracer.buildSpan("FooServiceAux/ callRepositoryAux").asChildOf(tracer.activeSpan()).start();

    Delay.execute();
    List<Foo> list = this.FooRepository.findAll();
    
    newSpan.finish();
    return list;
  }
  
}
