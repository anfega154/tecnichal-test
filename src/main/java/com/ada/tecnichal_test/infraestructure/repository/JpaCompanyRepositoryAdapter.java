package com.ada.tecnichal_test.infraestructure.repository;

import com.ada.tecnichal_test.domain.model.Company;
import com.ada.tecnichal_test.infraestructure.repository.jpa.SpringDataCompanyRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaCompanyRepositoryAdapter{
    private final SpringDataCompanyRepository jpaRepository;

    public JpaCompanyRepositoryAdapter(SpringDataCompanyRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

        /**
         * Saves a company in the repository.
         *
         * @param company
         * @return Company
         */
    public Company save(Company company) {
        jpaRepository.save(company);
        return company;
    }

    /**
     * Finds a company by its ID.
     *
     * @param id
     * @return Optional<Company>
     */
    public Optional<Company> findById(Long id) {
        return jpaRepository.findById(id);
    }

    /**
     * Finds a company by its code.
     *
     * @param code
     * @return Optional<Company>
     */
    public Optional<Company> findByCode(String code) {
        return jpaRepository.findByCode(code);
    }

    /**
     * Finds all companies in the repository.
     *
     * @return List<Company>
     */
    public List<Company> findAll() {
        return jpaRepository.findAll();
    }

    /**
     * Deletes a company by its ID.
     *
     * @param id
     */
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    public SpringDataCompanyRepository getJpaRepository() {
        return jpaRepository;
    }
}
