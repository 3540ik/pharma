package com.Pharma.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 60)
    private String name;
}