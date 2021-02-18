package com.mondris.demo.Repository;

import com.mondris.demo.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserServiceRepository extends JpaRepository<Employee, Long> {
    Employee getByEmail(String email);
}
