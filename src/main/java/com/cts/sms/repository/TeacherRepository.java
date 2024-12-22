
package com.cts.sms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.sms.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	@Query("select t from Teacher t where t.firstName=?1")
	List<Teacher> findTeacherByFirstName(String firstName);

	@Query("select t from Teacher t where t.lastName=?1")
	List<Teacher> findTeacherByLastName(String lastName);

	@Query("select t from Teacher t where t.doj=?1")
	List<Teacher> findTeacherByDOJ(LocalDate doj);
	
	@Query("select t from Teacher t where t.dob=?1")
	List<Teacher> findTeacherByDOB(LocalDate dob);

	@Query("update Teacher t set t.firstName=?2 where t.id=?1")
	int updateTeacherFirstName(Long id, String firstName);

}
