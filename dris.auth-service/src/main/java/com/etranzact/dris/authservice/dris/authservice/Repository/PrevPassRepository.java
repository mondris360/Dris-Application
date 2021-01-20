package com.etranzact.dris.authservice.dris.authservice.Repository;

import com.etranzact.dris.authservice.dris.authservice.Model.PreviousPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrevPassRepository extends JpaRepository<PreviousPassword, Long> {
    List<PreviousPassword> getAllByUser_email(String email);
}
