package com.mondris.demo.Repository;

import com.mondris.demo.Model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    State getByName(String name);
}
