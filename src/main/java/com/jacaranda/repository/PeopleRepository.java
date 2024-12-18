package com.jacaranda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.model.People;

public interface PeopleRepository extends JpaRepository<People, Integer> {

}
