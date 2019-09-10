package com.micgogi.aircargo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micgogi.aircargo.entity.Consignment;
import com.micgogi.aircargo.repository.CosnsignmentRespoitory;

@Service
public class ConsignmentServiceImpl implements CosignmentService {
	@Autowired CosnsignmentRespoitory consognmentRepo;

	@Override
	public boolean addConsignemnt(Consignment consignment) {
		consognmentRepo.save(consignment);
		return true;
	}

	@Override
	public List<Consignment> getAllConsignmentByCustomer(int id) {
		return consognmentRepo.getAllConsingmnetByCustomer(id);
		
	}

	@Override
	public Consignment findById(int consignmentId) {
		// TODO Auto-generated method stub
	Consignment consignment = consognmentRepo.findByConsignmentNo(consignmentId);
	
		return consignment;
	}
	

}
