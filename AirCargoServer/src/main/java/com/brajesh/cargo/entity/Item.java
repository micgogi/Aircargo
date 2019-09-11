package com.brajesh.cargo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {
	
	@Id
	@Column(name = "item_no")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemNo;
	
	@Column(name = "item_category")
	private String itemCategory;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "weight")
	private double weight;
	
	@ManyToOne()
	@JoinColumn(name = "consignment_no", nullable = false)
	private Consignment consignemnt;

	public Consignment getConsignemnt() {
		return consignemnt;
	}

	public void setConsignemnt(Consignment consignemnt) {
		this.consignemnt = consignemnt;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
