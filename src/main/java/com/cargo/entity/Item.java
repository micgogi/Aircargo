package com.cargo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {
	
	@Id
	@Column(name="itemNo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemNo;
	@Column(name="itemCategory")
	private String itemCategory;
	@Column(name="itemName")
	private String itemName;
	@Column(name="weight")
	private double weight;
	
	

	@ManyToOne(fetch = FetchType.LAZY)
	private Consignment consignment;
	
	public Item() {
		super();
	
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
	public Consignment getConsignment() {
		return consignment;
	}
	public void setConsignment(Consignment consignment) {
		this.consignment = consignment;
	}
	@Override
	public String toString() {
		return "Item [itemNo=" + itemNo + ", itemCategory=" + itemCategory + ", itemName=" + itemName + ", weight="
				+ weight + ", consignment=" + consignment + "]";
	}
	
	

}
