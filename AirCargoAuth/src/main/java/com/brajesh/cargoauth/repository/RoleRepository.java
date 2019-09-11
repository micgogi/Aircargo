package com.brajesh.cargoauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brajesh.cargoauth.entity.User;

public interface RoleRepository extends JpaRepository<User, Integer> {

}
