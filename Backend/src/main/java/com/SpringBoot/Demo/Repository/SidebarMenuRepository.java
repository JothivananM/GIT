package com.SpringBoot.Demo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.SpringBoot.Demo.Entity.SidebarMenu;

public interface SidebarMenuRepository extends JpaRepository<SidebarMenu, Integer> {

	//public List<Role_Mapping> findByRoleId(int roleId);
	// @Query(value = "select r.role_table_id, r.url, r.menu_name, r.icon from sidebar_menu r where r.role_id = :roleId", nativeQuery = true)
	// public List<SidebarMenu> findAllByRoleId(int roleId);
	@Modifying
	@Query(value = "select * from sidebar_menu where role_id = :roleId", nativeQuery = true)
	public List<SidebarMenu> findAllByRoleId(int roleId);

}
 