package com.etranzact.dris.authservice.dris.authservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="previous_passwords")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreviousPassword {
    @Id
    private long id;
    private String newPassword;
    private final String status ="changed";
    @CreationTimestamp
    private Timestamp changed_at;
//    @ManyToOne
//    private User user;


    public PreviousPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
