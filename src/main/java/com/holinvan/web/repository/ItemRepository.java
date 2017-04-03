package com.holinvan.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.holinvan.web.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
