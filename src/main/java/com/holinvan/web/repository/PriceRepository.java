package com.holinvan.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.holinvan.web.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {

}
