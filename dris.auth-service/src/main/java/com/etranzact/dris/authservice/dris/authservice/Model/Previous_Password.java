package com.etranzact.dris.authservice.dris.authservice.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Previous_Password {
    @Id
    private long id;
    private String user_email;
    private String password;
    private final String status ="changed";
    private Timestamp changed_at;


}
