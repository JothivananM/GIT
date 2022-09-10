package com.SpringBoot.Demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot.Demo.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
