package com.micgogi.aircargo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.micgogi.aircargo.entity.Consignment;
@Repository
public interface CosnsignmentRespoitory extends JpaRepository<Consignment,Integer> {

	@Query("select consignment from Consignment consignment where consignment.customer.accountNo=?1")
	List<Consignment> getAllConsingmnetByCustomer(int id);
	Consignment findByConsignmentNo(int consignmentNo);
}
