package com.pjait.Games_Client.controllers;

import com.pjait.Games_Client.services.ViewCompanyService;
import com.pjait.Games_Data.entities.Company;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client/companies")
public class ViewCompanyController {
    private final ViewCompanyService viewCompanyService;
    public ViewCompanyController(ViewCompanyService viewCompanyService) {
        this.viewCompanyService = viewCompanyService;
    }

    @GetMapping("/view")
    public String viewAllCompanies(Model model) {
        model.addAttribute("entityType", "Company");
        model.addAttribute("entities", viewCompanyService.getAllCompanies());
        return "viewList";
    }

    @GetMapping("/view/id")
    public String viewCompanyById(Model model) {
        model.addAttribute("entityType", "Company");
        model.addAttribute("company", new Company());
        return "findByIdForm";
    }

    @PostMapping("/view/id")
    public String viewCompanyById(@ModelAttribute Company company, Model model) {
        Long id = company.getId();
        model.addAttribute("entityType", "Company");
        model.addAttribute("entities", viewCompanyService.getCompanyById(id));
        return "viewList";
    }

    @GetMapping("/view/name")
    public String viewCompanyByName(Model model) {
        model.addAttribute("entityType", "Company");
        model.addAttribute("company", new Company());
        return "findByNameForm";
    }

    @PostMapping("/view/name")
    public String viewCompanyByName(@ModelAttribute Company company, Model model) {
        String name = company.getName();
        model.addAttribute("entityType", "Company");
        model.addAttribute("entities", viewCompanyService.getCompanyByName(name));
        return "viewList";
    }

    @GetMapping("/view/apiId")
    public String viewCompanyByApiId(Model model) {
        model.addAttribute("entityType", "Company");
        model.addAttribute("company", new Company());
        return "findByApiIdForm";
    }

    @PostMapping("/view/apiId")
    public String viewCompanyByApiId(@ModelAttribute Company company, Model model) {
        Long apiId = company.getApiId();
        model.addAttribute("entityType", "Company");
        model.addAttribute("entities", viewCompanyService.getCompanyByApiId(apiId));
        return "viewList";
    }

    @GetMapping("/delete")
    public String deleteCompany(Model model) {
        model.addAttribute("entityType", "Company");
        model.addAttribute("company", new Company());
        return "deleteForm";
    }

    @PostMapping("/delete")
    public String deleteCompany(@ModelAttribute Company company) {
        Long id = company.getId();
        viewCompanyService.deleteCompany(id);
        return "redirect:/client/companies/view";
    }

    @GetMapping("/update")
    public String updateCompany(Model model) {
        model.addAttribute("entityType", "Company");
        model.addAttribute("company", new Company());
        return "updateForm";
    }

    @PostMapping("/update")
    public String updateCompany(@ModelAttribute Company company) {
        Long id = company.getId();
        viewCompanyService.updateCompany(id, company);
        return "redirect:/client/companies/view";
    }

    @GetMapping("/add")
    public String addCompany(Model model) {
        model.addAttribute("entityType", "Company");
        model.addAttribute("company", new Company());
        return "addForm";
    }

    @PostMapping("/add")
    public String addCompany(@ModelAttribute Company company) {
        viewCompanyService.addCompany(company);
        return "redirect:/client/companies/view";
    }
}
