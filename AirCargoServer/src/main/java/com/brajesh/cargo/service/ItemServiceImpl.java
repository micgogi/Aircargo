package com.brajesh.cargo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brajesh.cargo.entity.Item;
import com.brajesh.cargo.repository.ItemRepository;


@Service
public class ItemServiceImpl implements ItemService{
	@Autowired ItemRepository itemRepo;

	@Override
	public boolean saveItem(Item item) {
		itemRepo.save(item);
		return true;
	}

	@Override
	public List<Item> getAllItemByConsignment(int consignmentId) {
		return itemRepo.getAllItemByConsignemnt(consignmentId);
	}
	

}
