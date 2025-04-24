package com.ada.tecnichal_test.aplication.service.company;


import com.ada.tecnichal_test.domain.dto.CompanyVersionInfo;
import com.ada.tecnichal_test.infraestructure.repository.jpa.CompanyQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyQueryService {

    private final CompanyQueryRepository queryRepository;

    /**
     * Constructor for CompanyQueryService.
     *
     * @param queryRepository
     */
    public CompanyQueryService(CompanyQueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    /**
     * Retrieves a list of CompanyVersionInfo by company code.
     *
     * @param code
     * @return List<CompanyVersionInfo>
     */
    public List<CompanyVersionInfo> getCompanyVersionsByCode(String code) {
        return queryRepository.findCompanyVersionInfoByCode(code);
    }
}
