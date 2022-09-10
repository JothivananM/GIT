package com.SpringBoot.Demo.Repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SpringBoot.Demo.Entity.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

	@Query(value = "select * from user_details where company_email=:company_email and password=:password and is_active = 'true'", nativeQuery = true)
	UserDetails findOneBycompanyEmailIdIgnoreCaseAndUserPassword(String company_email, String password);

	@Transactional
	@Modifying
	@Query(value = "UPDATE user_details\r\n"
			+ "SET is_active=:status WHERE employee_table_id=:id", nativeQuery = true)
	public int updateStatus(int id, Boolean status);

	@Transactional
	@Query(value = "select password from user_details where company_email =:companyEmailid and is_active = 'true'", nativeQuery = true)
	public String getUserPassword(String companyEmailid);

	@Transactional
	@Modifying
	@Query(value = "select role_id from user_details\r\n"
			+ "where company_email =:mailId ", nativeQuery = true)
	public int showRoleId(String mailId);

	@Transactional
	@Modifying
	@Query(value = "select * from user_details where is_active = true order by employee_code", nativeQuery = true)
	public List<UserDetails> findAll();
}
