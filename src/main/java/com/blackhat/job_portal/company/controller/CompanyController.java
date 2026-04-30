package com.blackhat.job_portal.company.controller;

import com.blackhat.job_portal.dto.CompanyDto;
import com.blackhat.job_portal.company.service.ICompanyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class CompanyController {

    private final ICompanyService companyService;

//    @Autowired // optional
//    public CompanyController(ICompanService CompanService) {
//        this.CompanService = CompanService;
//    }

    @GetMapping(path = "/public", version = "1.0")
    public ResponseEntity<List<CompanyDto>> getAllCompanies(){
        List<CompanyDto> companyList = companyService.getAllCompanies();
        return ResponseEntity.ok().body(companyList);
    }

    @PostMapping(path = "/admin", version = "1.0")
    public ResponseEntity<String> createCompany(@RequestBody @Valid CompanyDto companyDto) {
        boolean isCreated = companyService.createCompany(companyDto);
        if (isCreated) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Request processed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Request processing failed");
        }
    }

    @GetMapping(path = "/admin", version = "1.0")
    public ResponseEntity<List<CompanyDto>> getAllCompaniesForAdmin() {
        List<CompanyDto> companyList = companyService.getAllCompaniesForAdmin();
        return ResponseEntity.ok().body(companyList);
    }

    @PutMapping(path = "/{id}/admin", version = "1.0")
    public ResponseEntity<String> updateCompanyDetails(@PathVariable @NotBlank String id,
                                                       @RequestBody @Valid CompanyDto companyDto) {
        boolean isUpdated = companyService.updateCompanyDetails(Long.valueOf(id),companyDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("Company details updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update Company details");
        }
    }

    @DeleteMapping(path = "/{id}/admin", version = "1.0")
    public ResponseEntity<String> deleteCompanyById(@PathVariable @NotBlank String id) {
        companyService.deleteCompanyById(Long.valueOf(id));
        return ResponseEntity.status(HttpStatus.OK).body("Company record deleted successfully.");
    }
}
