package com.mondris.demo.Repository;

import com.mondris.demo.Model.Employee;
import com.mondris.demo.Model.SubDepartment;
import com.mondris.demo.Model.TeamLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TeamLeadRepository extends JpaRepository<TeamLead, Long> {

    Optional<TeamLead> getById(long id);
    TeamLead getByEmployee(Employee user);
    TeamLead getBySubDepartment(SubDepartment subDepartment);

}
