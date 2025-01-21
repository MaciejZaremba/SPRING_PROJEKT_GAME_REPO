package com.pjait.Games_Client.controllers;

import com.pjait.Games_Client.services.ViewPlatformService;
import com.pjait.Games_Data.entities.Platform;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client/platforms")
public class ViewPlatformController {
    private final ViewPlatformService viewPlatformService;
    public ViewPlatformController(ViewPlatformService viewPlatformService) {
        this.viewPlatformService = viewPlatformService;
    }

    @GetMapping("/view")
    public String viewAllPlatforms(Model model) {
        model.addAttribute("entityType", "Platform");
        model.addAttribute("entities", viewPlatformService.getAllPlatforms());
        return "viewList";
    }

    @GetMapping("/view/id")
    public String viewPlatformById(Model model) {
        model.addAttribute("entityType", "Platform");
        model.addAttribute("platform", new Platform());
        return "findByIdForm";
    }

    @PostMapping("/view/id")
    public String viewPlatformById(@ModelAttribute Platform platform, Model model) {
        Long id = platform.getId();
        model.addAttribute("entityType", "Platform");
        model.addAttribute("entities", viewPlatformService.getPlatformById(id));
        return "viewList";
    }

    @GetMapping("/view/name")
    public String viewPlatformByName(Model model) {
        model.addAttribute("entityType", "Platform");
        model.addAttribute("platform", new Platform());
        return "findByNameForm";
    }

    @PostMapping("/view/name")
    public String viewPlatformByName(@ModelAttribute Platform platform, Model model) {
        String name = platform.getName();
        model.addAttribute("entityType", "Platform");
        model.addAttribute("entities", viewPlatformService.getPlatformByName(name));
        return "viewList";
    }

    @GetMapping("/view/apiId")
    public String viewPlatformByApiId(Model model) {
        model.addAttribute("entityType", "Platform");
        model.addAttribute("platform", new Platform());
        return "findByApiIdForm";
    }

    @PostMapping("/view/apiId")
    public String viewPlatformByApiId(@ModelAttribute Platform platform, Model model) {
        Long apiId = platform.getApiId();
        model.addAttribute("entityType", "Platform");
        model.addAttribute("entities", viewPlatformService.getPlatformByApiId(apiId));
        return "viewList";
    }

    @GetMapping("/delete")
    public String deletePlatform(Model model) {
        model.addAttribute("entityType", "Platform");
        model.addAttribute("platform", new Platform());
        return "deleteForm";
    }

    @PostMapping("/delete")
    public String deletePlatform(@ModelAttribute Platform platform) {
        Long id = platform.getId();
        viewPlatformService.deletePlatform(id);
        return "redirect:/client/platforms/view";
    }

    @GetMapping("/update")
    public String updatePlatform(Model model) {
        model.addAttribute("entityType", "Platform");
        model.addAttribute("platform", new Platform());
        return "updateForm";
    }

    @PostMapping("/update")
    public String updatePlatform(@ModelAttribute Platform platform) {
        Long id = platform.getId();
        viewPlatformService.updatePlatform(id, platform);
        return "redirect:/client/platforms/view";
    }

    @GetMapping("/add")
    public String addPlatform(Model model) {
        model.addAttribute("entityType", "Platform");
        model.addAttribute("platform", new Platform());
        return "addForm";
    }

    @PostMapping("/add")
    public String addPlatform(@ModelAttribute Platform platform) {
        viewPlatformService.addPlatform(platform);
        return "redirect:/client/platforms/view";
    }

}
