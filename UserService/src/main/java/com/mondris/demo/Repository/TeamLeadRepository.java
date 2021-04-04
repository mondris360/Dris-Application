package com.mondris.demo.Repository;

import com.mondris.demo.Model.Employee;
import com.mondris.demo.Model.SubDepartment;
import com.mondris.demo.Model.TeamLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamLeadRepository extends JpaRepository<TeamLead, Long> {

    TeamLead getById(long id);
    TeamLead getByEmployee(Employee user);
    TeamLead getBySubDepartment(SubDepartment subDepartment);

}
