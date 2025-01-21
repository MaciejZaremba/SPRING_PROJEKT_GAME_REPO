package com.pjait.Games_API.controllers;

import com.pjait.Games_API.services.CompanyService;
import com.pjait.Games_Data.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.findAllCompanies();
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.findCompanyById(id);
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Company> getCompanyByName(@PathVariable String name) {
        Company company = companyService.findCompanyByName(name);
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/apiId/{apiId}")
    public ResponseEntity<Company> getCompanyByApiId(@PathVariable Long apiId) {
        Company company = companyService.findCompanyByApiId(apiId);
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createCompany(@RequestBody Company company) {
        if (companyService.findCompanyByName(company.getName()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        companyService.addCompany(company);
        if (companyService.findCompanyByName(company.getName()) != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteCompanyById(@PathVariable Long id) {
        Company company = companyService.findCompanyById(id);
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<Void> updateCompanyById(@PathVariable Long id, @RequestBody Company company) {
        Company companyTest = companyService.findCompanyById(id);
        if (companyTest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        companyService.updateCompany(company, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
