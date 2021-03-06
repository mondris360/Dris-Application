package com.mondris.demo.Repository;

import com.mondris.demo.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Employee, Long> {
    Employee getByEmail(String email);
}
