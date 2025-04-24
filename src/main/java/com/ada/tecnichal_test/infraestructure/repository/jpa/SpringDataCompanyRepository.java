package com.ada.tecnichal_test.infraestructure.repository.jpa;

import com.ada.tecnichal_test.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataCompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCode(String code);
}
