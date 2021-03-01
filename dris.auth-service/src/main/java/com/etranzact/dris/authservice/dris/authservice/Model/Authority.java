package com.etranzact.dris.authservice.dris.authservice.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue
    private int id;
    private String authority;
    @ManyToOne
    @JoinColumn(name="user_fk")
    private User user;

    public Authority(String authority) {
        this.authority = authority;
    }
}
