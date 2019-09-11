package com.brajesh.cargo.service;

import java.util.List;

import com.brajesh.cargo.entity.Consignment;

public interface ConsignmentService {
	boolean addConsignemnt(Consignment consignment);
	List<Consignment> getAllConsignmentByCustomer(int id);
	Consignment findById(int consignmentId);
}
