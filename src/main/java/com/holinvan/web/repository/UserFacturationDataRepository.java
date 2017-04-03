package com.holinvan.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.holinvan.web.model.UserFacturationData;

@Repository
public interface UserFacturationDataRepository extends JpaRepository <UserFacturationData, Integer>{

}