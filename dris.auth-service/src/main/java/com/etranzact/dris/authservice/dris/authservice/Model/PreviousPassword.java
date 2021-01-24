package com.etranzact.dris.authservice.dris.authservice.Model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="previous_passwords")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreviousPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String newPassword;
    private final String status ="changed";
    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;
    @CreationTimestamp
    private Timestamp changed_at;

    public PreviousPassword(String newPassword, User user) {
        this.newPassword = newPassword;
        this.user = user;
    }
}
