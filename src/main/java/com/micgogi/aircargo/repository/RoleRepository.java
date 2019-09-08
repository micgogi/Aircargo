package com.micgogi.aircargo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micgogi.aircargo.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
	Role findById(int roleId);
}
