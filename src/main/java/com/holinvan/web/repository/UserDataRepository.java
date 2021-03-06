package com.holinvan.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.holinvan.web.model.UserData;


@Repository
public interface UserDataRepository extends JpaRepository <UserData, Integer>{

	UserData findByEmail(String email);

	UserData findById(Integer id);



}