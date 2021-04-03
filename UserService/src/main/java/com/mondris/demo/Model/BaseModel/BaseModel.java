package com.mondris.demo.Model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mondris.demo.Model.Employee;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="is_enabled")
    private boolean enabled = true;

    @Column(name="created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private  Timestamp updatedAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "created_by_user_email")
    private Employee createdByUser;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "update_by_user_email")
    private Employee updatedByUser;


    private String note;

}
