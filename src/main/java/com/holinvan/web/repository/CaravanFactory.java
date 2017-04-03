package com.holinvan.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.holinvan.web.model.Caravan;

@Repository
public interface CaravanFactory extends JpaRepository <Caravan, Integer> {

	Caravan findById(Integer id);
}
