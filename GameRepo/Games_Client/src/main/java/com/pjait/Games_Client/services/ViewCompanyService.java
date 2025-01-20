package com.pjait.Games_Client.services;

import com.pjait.Games_Data.entities.Company;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ViewCompanyService extends ViewBaseService{
    public ViewCompanyService(RestClient restClient) {
        super(restClient);
    }

    public List<Company> getAllCompanies() {
        return viewAllEntities("/companies");
    }

    public Company getCompanyById(Long id) {
        return (Company) viewEntityByField("/companies/apiId/" + id, new ParameterizedTypeReference<>() {});
    }

    public Company getCompanyByName(String name) {
        return (Company) viewEntityByField("/companies/name/" + name, new ParameterizedTypeReference<>() {});
    }

    public Company getCompanyByApiId(Long id) {
        return (Company) viewEntityByField("/companies/apiId/" + id, new ParameterizedTypeReference<>() {});
    }

    public void addCompany(Company company) {
        createEntity("/companies", company);
    }

    public void updateCompany(Long id, Company company) {
        updateEntity("/companies/id/" + id, company);
    }

    public void deleteCompany(Long id) {
        deleteEntity("companies/id" + id, id);
    }

}
