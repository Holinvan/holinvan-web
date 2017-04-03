package com.holinvan.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.holinvan.web.model.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	
	Authority findByName(String name);
}
