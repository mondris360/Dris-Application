package com.etranzact.dris.authservice.dris.authservice.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="previous_passwords")
public class PreviousPassword {
    @Id
    private long id;
    private String password;
    private final String status ="changed";
    private Timestamp changed_at;
    @ManyToOne
    private User user;

}
