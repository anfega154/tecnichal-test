package com.ada.tecnichal_test.adapter.controller.company;

import com.ada.tecnichal_test.adapter.controller.BaseController;
import com.ada.tecnichal_test.aplication.service.company.CompanyQueryService;
import com.ada.tecnichal_test.aplication.service.company.CompanyService;
import com.ada.tecnichal_test.domain.model.Company;
import com.ada.tecnichal_test.domain.dto.CompanyVersionInfo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
     @RequestMapping("/api/company")
     @Validated
     public class CompanyController extends BaseController {
         private final CompanyService companyService;
         private final CompanyQueryService companyQueryService;

         /**
          * Constructor for CompanyController.
          *
          * @param companyService
          * @param companyQueryService
          */
         public CompanyController(CompanyService companyService, CompanyQueryService companyQueryService) {
             this.companyService = companyService;
             this.companyQueryService = companyQueryService;
         }

         /**
          * Creates a new company.
          *
          * @param company
          * @return ResponseEntity
          */
         @PostMapping
         public Object create(@Valid @RequestBody Company company) {
             try {
                 Company created = companyService.create(company);
                 return created(created);
             } catch (IllegalArgumentException ex) {
                 return error(ex.getMessage(), HttpStatus.BAD_REQUEST);
             } catch (Exception ex) {
                 return error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
             }
         }

         /**
          * Retrieves a company by its code.
          *
          * @param code
          * @return ResponseEntity
          */
         @GetMapping("/{code}")
         public Object getByCode(@PathVariable String code) {
             try {
                 return companyService.getByCode(code)
                         .map(this::ok)
                         .orElse(error("Company not found", HttpStatus.NOT_FOUND));
             } catch (Exception ex) {
                 return error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
             }
         }

         /**
          * Retrieves all companies.
          *
          * @return ResponseEntity
          */
         @GetMapping
         public Object list() {
             try {
                 return ok(companyService.list());
             } catch (Exception ex) {
                 return error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
             }
         }

         /**
          * Deletes a company by its ID.
          *
          * @param id
          * @return ResponseEntity
          */
         @DeleteMapping("/{id}")
         public Object delete(@PathVariable Long id) {
             try {
                 companyService.delete(id);
                 return ok("Company successfully deleted");
             } catch (EntityNotFoundException ex) {
                 return error("Company not found", HttpStatus.NOT_FOUND);
             } catch (Exception ex) {
                 return error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
             }
         }

         /**
          * Updates a company by its ID.
          *
          * @param id
          * @param company
          * @return ResponseEntity
          */
         @PutMapping("/{id}")
         public Object update(@PathVariable Long id, @Valid @RequestBody Company company) {
             try {
                 Company updated = companyService.update(id, company);
                 return ok(updated);
             } catch (EntityNotFoundException ex) {
                 return error("Company not found", HttpStatus.NOT_FOUND);
             } catch (IllegalArgumentException ex) {
                 return error(ex.getMessage(), HttpStatus.BAD_REQUEST);
             } catch (Exception ex) {
                 return error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
             }
         }

            /**
            * Retrieves version information for a company and version by its code.
            *
            * @param code
            * @return ResponseEntity
            */
         @GetMapping("/{code}/versions")
         public Object getCompanyVersionInfo(@PathVariable String code) {
             try {
                 List<CompanyVersionInfo> infoList = companyQueryService.getCompanyVersionsByCode(code);
                 if (infoList.isEmpty()) {
                     return error("No data found for the given company code", HttpStatus.NOT_FOUND);
                 }
                 return ok(infoList);
             } catch (Exception ex) {
                 return error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
             }
         }
     }