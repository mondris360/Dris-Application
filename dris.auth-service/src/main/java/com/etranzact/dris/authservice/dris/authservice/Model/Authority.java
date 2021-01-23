package com.etranzact.dris.authservice.dris.authservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue
    private int id;
    private String authority;
//    @ManyToOne()
//    private User user;

    public Authority(String authority) {
        this.authority = authority;
    }
}
