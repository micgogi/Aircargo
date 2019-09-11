package com.cargo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cargo.entity.Consignment;
@Repository
public interface ConsignmentRepository extends JpaRepository<Consignment, Integer> {
	
	@Query("select c from Consignment c where c.customer.accountNo=?1")
	List<Consignment> findListConsignment(int accountNo);

	@Query("select c from Consignment c where c.source=?3 and (c.dateOfShipment>=?1 and c.dateOfShipment<=?2)")
	List<Consignment> listAirport(Date from, Date to,String airport);
	
	@Query("select c from Consignment c where c.dateOfShipment>=?1 and c.dateOfShipment<=?2")
	List<Consignment> listDuration(Date from, Date to);
	
	@Query("select c.source,sum(c.totalCost) from Consignment c where c.dateOfShipment>=?1 and c.dateOfShipment<=?2 group by c.source")
	List<?> listRevenue(Date from, Date to);
	
	Consignment findById(int id);
}
