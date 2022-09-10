package com.SpringBoot.Demo.Repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.SpringBoot.Demo.Entity.AccessLevel;

public interface AccessLevelRepository extends JpaRepository<AccessLevel, Integer> {

	@Transactional
	@Query(value = "select count(*) from access_level where url=:url and role_id=:roleId", nativeQuery = true)
	public int accessApi(String url, int roleId);

}
