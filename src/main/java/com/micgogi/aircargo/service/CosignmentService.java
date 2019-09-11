package com.micgogi.aircargo.service;

import java.util.Date;
import java.util.List;

import com.micgogi.aircargo.entity.Consignment;

public interface CosignmentService {
	boolean addConsignemnt(Consignment consignment);
	List<Consignment> getAllConsignmentByCustomer(int id);
	Consignment findById(int consignmentId);
	List<Consignment> getAllReportsBYDate(String source, Date start, Date end);
	List<Consignment> getAllReports(Date start, Date end);
	List<?> getAllReportsByRevenue(Date strat, Date end);
	List<String> getAllAirports();
}
