package com.cargo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.cargo.entity.Item;
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	@Query("select c from Item c where c.consignment.consignmentNo=?1")
	List<Item> findItemList(int consignmentId);

}
