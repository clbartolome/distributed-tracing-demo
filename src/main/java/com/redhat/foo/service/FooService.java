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
public class FooService {

  @Autowired
  Tracer tracer;
  
  @Autowired
  FooServiceAux aux;

  private final FooRepository FooRepository;

  public FooService(FooRepository FooRepository) {
    this.FooRepository = FooRepository;
  }

  @Traceable
  public List<Foo> getAll(){
    Delay.execute();
    FooRepository.findAll();
    return this.callRepository();    
  }

  private List<Foo> callRepository() {
    Span newSpan = tracer.buildSpan("FooService/ callRepository").asChildOf(tracer.activeSpan()).start();

    Delay.execute();
    List<Foo> list = this.aux.getAllAux();
    
    newSpan.finish();
    return list;

  }
  
}
