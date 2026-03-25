package com.blackhat.job_portal.company.service.impl;

import com.blackhat.job_portal.dto.CompanyDto;
import com.blackhat.job_portal.entity.Company;
import com.blackhat.job_portal.repository.CompanyRepository;
import com.blackhat.job_portal.company.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;

//    public CompanyServiceImpl(CompanyRepository companyRepository) {
//        this.companyRepository = companyRepository;
//    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companyList = companyRepository.findAll();
        return companyList.stream().map(this::transformCompanyToDto).collect(Collectors.toList());
    }
    private CompanyDto transformCompanyToDto(Company company){
//
//        List<JobDto> jobDtos = company.getJobs().stream()
//                .map(this::transformJobToDto)
//                .collect(Collectors.toList());

        return  new CompanyDto(company.getId(),company.getName(),company.getLogo(),
                company.getIndustry(),company.getSize(),company.getRating(),
                company.getLocations(),company.getFounded(),company.getDescription(),
                company.getEmployees(),company.getWebsite(),company.getCreatedAt());
    }
}
