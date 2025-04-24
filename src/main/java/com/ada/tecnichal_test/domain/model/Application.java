package com.ada.tecnichal_test.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id")
    private Long id;

    @Column(name = "app_code", unique = true)
    private String code;

    @Column(name = "app_name")
    private String name;

    @Column(name = "app_description")
    private String description;

}
