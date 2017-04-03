package com.holinvan.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.holinvan.web.model.Caravana;

@Repository
public interface CaravanaRepository extends JpaRepository <Caravana, String> {

	Caravana findByPlate(String plate);
}
