package com.micgogi.aircargo.service;

import java.util.List;

import com.micgogi.aircargo.entity.Item;

public interface ItemService {
	
	boolean saveItem(Item item);
	List<Item> getAllItemByConsignment(int consignmentId);

}
