package com.blackhat.job_portal.company.service;

import com.blackhat.job_portal.dto.CompanyDto;

import com.blackhat.job_portal.entity.Company;

import java.util.List;

public interface ICompanyService {

    List<CompanyDto> getAllCompanies();

    List<CompanyDto> getAllCompaniesForAdmin();

    void deleteCompanyById(Long id);

    boolean updateCompanyDetails(Long id, CompanyDto companyDto);

    boolean createCompany(CompanyDto companyDto);

}
