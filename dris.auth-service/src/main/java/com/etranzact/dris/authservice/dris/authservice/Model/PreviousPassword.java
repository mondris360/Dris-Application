package com.etranzact.dris.authservice.dris.authservice.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="previous_password")
public class PreviousPassword {
    @Id
    private long id;
    private String user_email;
    private String password;
    private final String status ="changed";
    private Timestamp changed_at;


}
