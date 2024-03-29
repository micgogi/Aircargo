package com.micgogi.aircargo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.micgogi.aircargo.entity.Item;
@Repository
public interface ItemRepository extends JpaRepository<Item,Integer>{
	@Query("select item from Item item where item.consignemnt.consignmentNo=?1")
	List<Item> getAllItemByConsignemnt(int consignmentId);
	

}
