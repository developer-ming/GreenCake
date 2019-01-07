package com.greencake.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greencake.domain.Authorizer;

public interface AuthorizerRepository extends JpaRepository<Authorizer, Integer> {

}
