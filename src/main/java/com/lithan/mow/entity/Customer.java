package com.lithan.mow.entity;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="full_name")
    String fullName;

    String email;

    String address;

    String password;

    byte[] profilePicture;

    byte[] profileBackground;

    byte[] qualification;

}
