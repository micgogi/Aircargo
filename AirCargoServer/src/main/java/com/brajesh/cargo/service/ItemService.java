package com.brajesh.cargo.service;

import java.util.List;

import com.brajesh.cargo.entity.Item;

public interface ItemService {
	
	boolean saveItem(Item item);
	List<Item> getAllItemByConsignment(int consignmentId);

}
