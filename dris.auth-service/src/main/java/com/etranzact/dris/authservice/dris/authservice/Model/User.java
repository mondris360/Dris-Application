package com.etranzact.dris.authservice.dris.authservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="users")
public class User {
    @Id
    private  String email;
    private  String password;
    private  boolean enabled = false;
    @JoinColumn(name="user_email")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PreviousPassword> previousPasswords;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email")
    private  Set<Authority> authorities;
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;

}
