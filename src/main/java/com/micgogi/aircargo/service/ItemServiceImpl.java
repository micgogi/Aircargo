package com.micgogi.aircargo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micgogi.aircargo.entity.Item;
import com.micgogi.aircargo.repository.ItemRepository;


/**
 * @author micgogi
	ItemServiceImpl.java
 *
 * 8:02:42 PMSep 10, 2019
 */
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
		// TODO Auto-generated method stub
		return itemRepo.getAllItemByConsignemnt(consignmentId);
	}
	

}
