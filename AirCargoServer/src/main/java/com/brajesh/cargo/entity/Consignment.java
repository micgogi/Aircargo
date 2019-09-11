package com.brajesh.cargo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "consignment")
public class Consignment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "consignment_no")
	private int consignmentNo;

	@Column(name = "shipment_address")
	private String shipmentAddress;

	@Column(name = "service_type")
	private String serviceType;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfShipment")
	private Date dateOfShipment;

	@Column(name = "total_cost")
	private double totalCost;

	@Column(name = "source")
	private String source;

	@Column(name = "destination")
	private String destination;
	
	@Column(name="customer_id")
	private String customerId;

	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getConsignmentNo() {
		return consignmentNo;
	}

	public void setConsignmentNo(int consignmentNo) {
		this.consignmentNo = consignmentNo;
	}

	public String getShipmentAddress() {
		return shipmentAddress;
	}

	public void setShipmentAddress(String shipmentAddress) {
		this.shipmentAddress = shipmentAddress;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public Date getDateOfShipment() {
		return dateOfShipment;
	}

	public void setDateOfShipment(Date dateOfShipment) {
		this.dateOfShipment = dateOfShipment;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Consignment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
