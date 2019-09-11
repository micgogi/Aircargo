package com.micgogi.aircargo.repository;

import java.util.Date;
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
	
	@Query("select consignment from Consignment consignment where consignment.source=?1 and "
			+ "(consignment.dateOfShipment>=?2 and consignment.dateOfShipment<=?3) ")
	List<Consignment> getAllReports(String source, Date start, Date end);
	@Query("select consignment from Consignment consignment where consignment.dateOfShipment>=?1 "
			+ "and consignment.dateOfShipment<=?2")
	List<Consignment> getAllReportsByDate(Date start , Date end);
	@Query( "select consignment.source,sum(consignment.totalCost) from Consignment consignment"
			+ " where consignment.dateOfShipment>=?1 and consignment.dateOfShipment<=?2 group by consignment.source" )
	public List<?> getReportsByRevenue(Date start,Date end);

	@Query("select distinct consignment.source from Consignment consignment")
	public List<String> getAirportNames();

	
}
