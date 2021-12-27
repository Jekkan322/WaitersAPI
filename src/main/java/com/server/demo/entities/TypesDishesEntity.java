package com.server.demo.entities;

import javax.persistence.*;

@Entity
@Table(name="TypesDishes")
public class TypesDishesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


}
