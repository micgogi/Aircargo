package com.cargo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cargo.entity.Consignment;
@Repository
public interface ConsignmentRepository extends JpaRepository<Consignment, Integer> {
	
	@Query("select c from Consignment c where c.customer.accountNo=?1")
	List<Consignment> findListConsignment(int accountNo);

}
