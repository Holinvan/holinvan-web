package com.holinvan.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.holinvan.web.model.Availability;



@Repository
public interface AvailabilityRepository extends JpaRepository <Availability, Integer> {
}
