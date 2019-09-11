package com.brajesh.cargo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brajesh.cargo.entity.Consignment;
import com.brajesh.cargo.repository.ConsignmentRepository;

@Service
public class ConsignmentServiceImpl implements ConsignmentService {
	@Autowired
	private ConsignmentRepository consignmentRepo;

	@Override
	public boolean addConsignemnt(Consignment consignment) {
		consignmentRepo.save(consignment);
		return true;
	}

	@Override
	public List<Consignment> getAllConsignmentByCustomer(int id) {
		return consignmentRepo.getAllConsingmnetByCustomer(id);

	}

	@Override
	public Consignment findById(int consignmentId) {
		Consignment consignment = consignmentRepo.findByConsignmentNo(consignmentId);
		return consignment;
	}

}
