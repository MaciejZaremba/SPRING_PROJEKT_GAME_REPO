package com.pjait.Games_Client.controllers;

import com.pjait.Games_Client.services.ViewThemeService;
import com.pjait.Games_Data.entities.Theme;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client/themes")
public class ViewThemeController {
    private final ViewThemeService viewThemeService;
    public ViewThemeController(ViewThemeService viewThemeService) {
        this.viewThemeService = viewThemeService;
    }

    @GetMapping("/view")
    public String viewAllThemes(Model model) {
        model.addAttribute("entityType", "Theme");
        model.addAttribute("entities", viewThemeService.getAllThemes());
        return "viewList";
    }

    @GetMapping("/view/id")
    public String viewThemeById(Model model) {
        model.addAttribute("entityType", "Theme");
        model.addAttribute("theme", new Theme());
        return "findByIdForm";
    }

    @PostMapping("/view/id")
    public String viewThemeById(@ModelAttribute Theme theme, Model model) {
        Long id = theme.getId();
        model.addAttribute("entityType", "Theme");
        model.addAttribute("entities", viewThemeService.getThemeById(id));
        return "viewList";
    }

    @GetMapping("/view/name")
    public String viewThemeByName(Model model) {
        model.addAttribute("entityType", "Theme");
        model.addAttribute("theme", new Theme());
        return "findByNameForm";
    }

    @PostMapping("/view/name")
    public String viewThemeByName(@ModelAttribute Theme theme, Model model) {
        String name = theme.getName();
        model.addAttribute("entityType", "Theme");
        model.addAttribute("entities", viewThemeService.getThemeByName(name));
        return "viewList";
    }

    @GetMapping("/view/apiId")
    public String viewThemeByApiId(Model model) {
        model.addAttribute("entityType", "Theme");
        model.addAttribute("theme", new Theme());
        return "findByApiIdForm";
    }

    @PostMapping("/view/apiId")
    public String viewThemeByApiId(@ModelAttribute Theme theme, Model model) {
        Long apiId = theme.getApiId();
        model.addAttribute("entityType", "Theme");
        model.addAttribute("entities", viewThemeService.getThemeByApiId(apiId));
        return "viewList";
    }

    @GetMapping("/delete")
    public String deleteTheme(Model model) {
        model.addAttribute("entityType", "Theme");
        model.addAttribute("theme", new Theme());
        return "deleteForm";
    }

    @PostMapping("/delete")
    public String deleteTheme(@ModelAttribute Theme theme) {
        Long id = theme.getId();
        viewThemeService.deleteTheme(id);
        return "redirect:/client/themes/view";
    }

    @GetMapping("/update")
    public String updateTheme(Model model) {
        model.addAttribute("entityType", "Theme");
        model.addAttribute("theme", new Theme());
        return "updateForm";
    }

    @PostMapping("/update")
    public String updateTheme(@ModelAttribute Theme theme) {
        Long id = theme.getId();
        viewThemeService.updateTheme(id, theme);
        return "redirect:/client/themes/view";
    }

    @GetMapping("/add")
    public String addTheme(Model model) {
        model.addAttribute("entityType", "Theme");
        model.addAttribute("theme", new Theme());
        return "addForm";
    }

    @PostMapping("/add")
    public String addTheme(@ModelAttribute Theme theme) {
        viewThemeService.addTheme(theme);
        return "redirect:/client/themes/view";
    }

}
