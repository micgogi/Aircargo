package com.micgogi.aircargo.entity;

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
	@Column(name = "consignmentNo")
	private int consignmentNo;

	@Column(name = "shipmentAddress")
	private String shipmentAddress;

	@Column(name = "serviceType")
	private String serviceType;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfShipment")
	private Date dateOfShipment;

	@Column(name = "totalCost")
	private double totalCost;

	@Column(name = "source")
	private String source;

	@Column(name = "destination")
	private String destination;
    @JsonIgnore
	@ManyToOne
	@JoinColumn(name = "accountNo")
	Customer customer;
	

	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	

	
    
}
