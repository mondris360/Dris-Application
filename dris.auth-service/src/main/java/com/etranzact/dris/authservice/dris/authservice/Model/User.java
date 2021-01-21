package com.etranzact.dris.authservice.dris.authservice.Model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    private  String email;
    private  String password;
    private final String status = "inactive";
    private String role;
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<PreviousPassword> previousPasswords;
}
