package com.pjait.Games_API.services;

import com.pjait.Games_Data.entities.Company;
import com.pjait.Games_Data.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService extends BaseService<Company> {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        super(companyRepository);
        this.companyRepository = companyRepository;
    }

    public List<Company> findAllCompanies() {
        return findAll();
    }

    public Company findCompanyById(Long id) {
        return findById(id);
    }

    public void deleteCompany(Long id) {
        deleteById(id);
    }

    public void addCompany(Company company) {
        add(company);
    }

    public void updateCompany(Company company, Long id) {
        update(company, id);
    }

    public Optional<Company> findCompanyByName(String name) {
        return companyRepository.findByName(name);
    }

    public Optional<Company> findCompanyByApiId(Long apiId)
    {
        return companyRepository.findByApiId(apiId);
    }

}
