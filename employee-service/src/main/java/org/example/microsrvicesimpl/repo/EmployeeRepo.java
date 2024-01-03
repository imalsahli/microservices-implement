package org.example.microsrvicesimpl.repo;

import org.example.microsrvicesimpl.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
}
