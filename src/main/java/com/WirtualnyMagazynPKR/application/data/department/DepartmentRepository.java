package com.WirtualnyMagazynPKR.application.data.department;

import com.WirtualnyMagazynPKR.application.data.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    Optional<Department> findByName(String department);
}
