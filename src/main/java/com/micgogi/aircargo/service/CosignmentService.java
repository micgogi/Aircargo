package com.micgogi.aircargo.service;

import java.util.List;

import com.micgogi.aircargo.entity.Consignment;

public interface CosignmentService {
	boolean addConsignemnt(Consignment consignment);
	List<Consignment> getAllConsignmentByCustomer(int id);
	Consignment findById(int consignmentId);
}
