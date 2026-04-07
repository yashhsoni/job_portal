package com.blackhat.job_portal.company.controller;

import com.blackhat.job_portal.dto.CompanyDto;
import com.blackhat.job_portal.company.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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





}
