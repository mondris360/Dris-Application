package com.etranzact.dris.authservice.dris.authservice.Model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="users")
@ToString
public class User {
    @Id
    private  String email;

    private  String password;

    private  Boolean enabled = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PreviousPassword> previousPasswords;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private  Set<Authority> authorities;

    @CreationTimestamp
    private Timestamp created_at;

    @UpdateTimestamp
    private Timestamp updated_at;

}
