package com.cts.sms.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.sms.entity.Parents;

public interface ParentRepository extends JpaRepository<Parents, Long>{

	@Query("select p from Parents p where p.student.id =?1")
	ArrayList<Parents> findParentByStudentId(Long id);

}
