package com.redhat.foo.repository;

import com.redhat.foo.entity.Foo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FooRepository extends JpaRepository<Foo, Long>{

  
}
