package com.micgogi.aircargo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
private String roleName;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}

}

