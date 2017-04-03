package com.holinvan.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.holinvan.web.model.Camping;

@Repository
public interface CampingRepository extends JpaRepository<Camping, Integer> {

}
