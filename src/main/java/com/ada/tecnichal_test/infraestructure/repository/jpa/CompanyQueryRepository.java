package com.ada.tecnichal_test.infraestructure.repository.jpa;


import com.ada.tecnichal_test.domain.model.Company;
import com.ada.tecnichal_test.domain.dto.CompanyVersionInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyQueryRepository extends Repository<Company, Long> {
    @Query(value = """
        SELECT new com.ada.tecnichal_test.domain.dto.CompanyVersionInfo(
            c.code, c.name, a.name, v.version
        )
        FROM Company c
        JOIN VersionCompany vc ON vc.company.id = c.id
        JOIN Version v ON vc.version.id = v.id
        JOIN Application a ON v.application.id = a.id
        WHERE c.code = :codeCompany
    """)
    List<CompanyVersionInfo> findCompanyVersionInfoByCode(@Param("codeCompany") String code);
}
