package com.etranzact.dris.authservice.dris.authservice.Model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="otp_code")
public class OtpCode {
    @Id
    private long id;
    private String user_email;
    private int validityPeriodInMinutes;
    private final String status = "active";

}
