package com.mondris.demo.Repository;

import com.mondris.demo.Model.OfficePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OfficePositionRepository extends JpaRepository<OfficePosition, Long> {

    OfficePosition getByName(String name);
}
