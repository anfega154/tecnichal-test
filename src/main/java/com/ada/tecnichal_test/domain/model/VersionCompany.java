package com.ada.tecnichal_test.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "version_company", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"company_id", "version_id"})
})
public class VersionCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "version_company_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "version_id")
    private Version version;

    @Column(name = "version_company_description")
    private String description;
}
