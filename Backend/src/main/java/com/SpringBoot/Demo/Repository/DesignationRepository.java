package com.SpringBoot.Demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBoot.Demo.Entity.Designation;

public interface DesignationRepository extends JpaRepository<Designation, Integer> {

}
