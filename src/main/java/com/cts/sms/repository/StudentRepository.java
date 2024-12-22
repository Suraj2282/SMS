package com.cts.sms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.sms.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	@Query("select s from Student s where s.firstName= ?1")
	public List<Student> findStudentByFirstName(String firstName);

	@Query("select s from Student s where s.lastName=?1")
	public List<Student> findStudentByLastName(String lastName);

	@Query("select s.id, s.firstName, s.lastName from Student s where s.fees< ?1")
	public List<Object[]> getStudentsWithPendingFees(Long minFees);
}
