package com.project.Myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Myproject.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
