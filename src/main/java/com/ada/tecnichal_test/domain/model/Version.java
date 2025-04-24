package com.ada.tecnichal_test.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "version")
public class Version {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "version_id")
    private Long id;

    @Column(name = "version")
    private String version;

    @Column(name = "version_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "app_id")
    private Application application;

}
