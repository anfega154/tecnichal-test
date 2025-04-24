package com.ada.tecnichal_test.aplication.service.company;

import com.ada.tecnichal_test.aplication.service.BaseService;
import com.ada.tecnichal_test.domain.model.Company;
import com.ada.tecnichal_test.infraestructure.repository.JpaCompanyRepositoryAdapter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class CompanyService extends BaseService<Company, Long> {
    private final JpaCompanyRepositoryAdapter repository;

    /**
     * Returns the repository for this service.
     *
     */
    @Override
    protected JpaRepository<Company, Long> getRepository() {
        return repository.getJpaRepository();
    }

    /**
     * Constructor for CompanyService.
     *
     * @param repository
     */
    public CompanyService(JpaCompanyRepositoryAdapter repository) {
        this.repository = repository;
    }

    /**
     * Creates a new company.
     *
     * @param company
     * @return Company
     */
    public Company create(Company company) {
        try {
            if (repository.findByCode(company.getCode()).isPresent()) {
                logError("Company with code " + company.getCode() + " already exists");
                throw new IllegalArgumentException("A company with the code " + company.getCode() + " already exists");
            }

            logInfo("Creating company: " + company.getName());
            return repository.save(company);
        } catch (DataAccessException e) {
            logError("Error while creating company: " + e.getMessage());
            throw new RuntimeException("Failed to create company", e);
        }
    }

    /**
     * Finds a company by its code.
     *
     * @param code
     * @return Company
     */
    public Optional<Company> getByCode(String code) {
        try {
            return repository.findByCode(code);
        } catch (DataAccessException e) {
            logError("Error while retrieving company by code: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve company by code", e);
        }
    }

    /**
     * Finds all companies in the repository.
     *
     * @return List<Company>
     */
    public List<Company> list() {
        try {
            List<Company> companies = repository.findAll();
            return filterDuplicatesByCode(companies);
        } catch (DataAccessException e) {
            logError("Error while listing companies: " + e.getMessage());
            throw new RuntimeException("Failed to list companies", e);
        }
    }


    /**
     * Updates a company by its ID.
     *
     * @param id
     * @param company
     * @return Company
     */
    public Company update(Long id, Company company) {
        try {
            Company existing = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Company with ID " + id + " not found"));

            logInfo("Updating company with ID: " + id);

            existing.setName(company.getName());
            existing.setCode(company.getCode());
            existing.setDescription(company.getDescription());

            return repository.save(existing);
        } catch (EntityNotFoundException e) {
            logError(e.getMessage());
            throw e;
        } catch (DataAccessException e) {
            logError("Error while updating company: " + e.getMessage());
            throw new RuntimeException("Failed to update company", e);
        }
    }

    /**
     * Filters a list of companies to remove duplicates based on their code.
     *
     * @param companies The original list that may contain duplicates.
     * @return A list without duplicates, keeping the first occurrence for each code.
     */
    public List<Company> filterDuplicatesByCode(List<Company> companies) {
        return companies.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(
                                Company::getCode,
                                Function.identity(),
                                (existing, duplicate) -> existing
                        ),
                        map -> new ArrayList<>(map.values())
                ));
    }
}