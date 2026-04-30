package com.blackhat.job_portal.company.service.impl;

import com.blackhat.job_portal.company.service.ICompanyService;
import com.blackhat.job_portal.constants.ApplicationConstants;
import com.blackhat.job_portal.dto.CompanyDto;
import com.blackhat.job_portal.dto.JobDto;
import com.blackhat.job_portal.entity.Company;
import com.blackhat.job_portal.entity.Job;
import com.blackhat.job_portal.repository.CompanyRepository;
import com.blackhat.job_portal.util.ApplicationUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;

//    public CompanyServiceImpl(CompanyRepository companyRepository) {
//        this.companyRepository = companyRepository;
//    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companyList =companyRepository.fetchCompaniesWithJobsByStatus(ApplicationConstants.ACTIVE_STATUS);
        return companyList.stream().map(this::transformCompanyToDto).collect(Collectors.toList());
    }

    @Cacheable("companies")
    @Override
    public List<CompanyDto> getAllCompaniesForAdmin() {
        List<Company> companyList =companyRepository.findAll();
        return companyList.stream().map(this::transformCompanyToDtoForAdmin).collect(Collectors.toList());
    }

    @org.springframework.transaction.annotation.Transactional
    @Override
    public void deleteCompanyById(Long id) {
        companyRepository.deleteById(id);
    }

    @Transactional
    @Override
    public boolean updateCompanyDetails(Long id, CompanyDto companyDto) {
        int updatedRecords = companyRepository.updateCompanyDetails(
                id,companyDto.name(),companyDto.logo(),
                companyDto.industry(),companyDto.size(),companyDto.rating(),
                companyDto.locations(),companyDto.founded(),companyDto.description(),
                companyDto.employees(),companyDto.website()
        );
        return updatedRecords > 0;
    }

    @org.springframework.transaction.annotation.Transactional
    @Override
    public boolean createCompany(CompanyDto companyDto) {
        Company company = transformCompanyDtoToEntity(companyDto);
        Company savedCompany = companyRepository.save(company);
        return savedCompany.getId() != null && savedCompany.getId() > 0;
    }

    private CompanyDto transformCompanyToDto(Company company){

        List<JobDto> jobDtos = company.getJobs().stream()
                .map(job -> ApplicationUtility.transformJobToDto(job))
                .collect(Collectors.toList());

        return  new CompanyDto(company.getId(),company.getName(),company.getLogo(),
                company.getIndustry(),company.getSize(),company.getRating(),
                company.getLocations(),company.getFounded(),company.getDescription(),
                company.getEmployees(),company.getWebsite(),company.getCreatedAt(), jobDtos);
    }

    private JobDto transformJobToDto(Job job) {
        return new JobDto(
                job.getId(),
                job.getTitle(),
                job.getCompany().getId(),
                job.getCompany().getName(),
                job.getCompany().getLogo(),
                job.getLocation(),
                job.getWorkType(),
                job.getJobType(),
                job.getCategory(),
                job.getExperienceLevel(),
                job.getSalaryMin(),
                job.getSalaryMax(),
                job.getSalaryCurrency(),
                job.getSalaryPeriod(),
                job.getPostedDate(),
                job.getApplicationDeadline(),
                job.getApplicationsCount(),
                job.getFeatured(),
                job.getUrgent(),
                job.getRemote(),
                job.getStatus()
        );
    }
    private Company transformCompanyDtoToEntity(CompanyDto companyDto) {
        Company company = new Company();
        BeanUtils.copyProperties(companyDto, company);
        return company;
    }
    private CompanyDto transformCompanyToDtoForAdmin(Company company) {
        return new CompanyDto(company.getId(), company.getName(), company.getLogo(),
                company.getIndustry(), company.getSize(), company.getRating(),
                company.getLocations(), company.getFounded(), company.getDescription(),
                company.getEmployees(), company.getWebsite(), company.getCreatedAt(),null);
    }

}
