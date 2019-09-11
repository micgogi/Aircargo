package com.brajesh.cargo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brajesh.cargo.entity.Consignment;
@Repository
public interface ConsignmentRepository extends JpaRepository<Consignment,Integer> {

	@Query("select consignment from Consignment consignment where consignment.customer.accountNo=?1")
	List<Consignment> getAllConsingmnetByCustomer(int id);
	Consignment findByConsignmentNo(int consignmentNo);
}
