package com.example.demo.persistence;



import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AuthToken;
@Repository
public interface AuthTokenRespository extends JpaRepository<AuthToken, String> {

	
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM auth_token WHERE tipo='TO_DATE' AND hasta<?", nativeQuery = true)
	public int purgeToDate(Date hasta);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM auth_token WHERE tipo='DEFAULT' AND DATE_ADD(last_used, INTERVAL validity_seconds SECOND)<?", nativeQuery = true)
	public int purgeDefault(Date hasta);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM auth_token WHERE tipo='FROM_TO_DATE' AND hasta<<?", nativeQuery = true)
	public int purgeFromToDate(Date hasta);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM auth_token WHERE tipo='REQUEST_LIMIT' AND request_count>=request_limit", nativeQuery = true)
	public int purgeRequestLimit();
	
	
	
}
