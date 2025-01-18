package com.pjait.Games_Data.repositories;

import com.pjait.Games_Data.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByName(String name);
    Optional<Company> findById(Long id);
    List<Company> findAllByCountry(String country);
}
