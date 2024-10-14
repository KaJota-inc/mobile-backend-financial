package com.example.springwithPostgreSql.entity;

import jakarta.persistence.*;

@lombok.Data
@Entity
@Table(name="user", schema = "public" )
public class UserEntity {
    @Id
    @GeneratedValue(generator = "user_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String city;
}
