package com.holinvan.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holinvan.web.model.Item;
import com.holinvan.web.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired	
	ItemRepository itemRepository;
	
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

}
