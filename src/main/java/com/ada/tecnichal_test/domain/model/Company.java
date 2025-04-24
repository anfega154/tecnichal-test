package com.ada.tecnichal_test.domain.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    private Long id;

    @Column(name = "codigo_company", unique = true, nullable = false)
    private String code;

    @Column(name = "name_company", nullable = false)
    private String name;

    @Column(name = "description_company")
    private String description;
}