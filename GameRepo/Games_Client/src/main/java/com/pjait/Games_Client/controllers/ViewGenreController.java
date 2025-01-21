package com.pjait.Games_Client.controllers;

import com.pjait.Games_Client.services.ViewGenreService;
import com.pjait.Games_Data.entities.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client/genres")
public class ViewGenreController {
    private final ViewGenreService viewGenreService;
    public ViewGenreController(ViewGenreService viewGenreService) {
        this.viewGenreService = viewGenreService;
    }

    @GetMapping("/view")
    public String viewAllGenres(Model model) {
        List<Genre> genres = viewGenreService.getAllGenres();
        model.addAttribute("entityType", "Genre");
        model.addAttribute("entities", genres);
        return "viewList";
    }

    @GetMapping("/view/id")
    public String viewGenreById(Model model) {
        model.addAttribute("entityType", "Genre");
        model.addAttribute("genre", new Genre());
        return "findByIdForm";
    }

    @PostMapping("/view/id")
    public String viewGenreById(@ModelAttribute Genre genre, Model model)
    {
        Long id = genre.getId();
        model.addAttribute("entityType", "Genre");
        model.addAttribute("entities", viewGenreService.getGenreById(id));
        return "viewList";
    }

    @GetMapping("/view/name")
    public String viewGenreByName(Model model) {
        model.addAttribute("entityType", "Genre");
        model.addAttribute("genre", new Genre());
        return "findByNameForm";
    }

    @PostMapping("/view/name")
    public String viewGenreByName(@ModelAttribute Genre genre, Model model) {
        String name = genre.getName();
        model.addAttribute("entityType", "Genre");
        model.addAttribute("entities", viewGenreService.getGenreByName(name));
        return "viewList";
    }

    @GetMapping("/view/apiId")
    public String viewGenreByApiId(Model model) {
        model.addAttribute("entityType", "Genre");
        model.addAttribute("genre", new Genre());
        return "findByApiIdForm";
    }

    @PostMapping("/view/apiId")
    public String viewGenreByApiId(@ModelAttribute Genre genre, Model model) {
        Long apiId = genre.getApiId();
        model.addAttribute("entityType", "Genre");
        model.addAttribute("entities", viewGenreService.getGenreByApiId(apiId));
        return "viewList";
    }

    @GetMapping("/delete")
    public String deleteGenre(Model model) {
        model.addAttribute("entityType", "Genre");
        model.addAttribute("genre", new Genre());
        return "deleteForm";
    }

    @PostMapping("/delete")
    public String deleteGenre(@ModelAttribute Genre genre) {
        Long id = genre.getId();
        this.viewGenreService.deleteGenre(id);
        return "redirect:/client/genres/view";
    }

    @GetMapping("/update")
    public String updateGenre(Model model) {
        model.addAttribute("entityType", "Genre");
        model.addAttribute("genre", new Genre());
        return "updateForm";
    }

    @PostMapping("/update")
    public String updateGenre(@ModelAttribute Genre genre) {
        Long id = genre.getId();
        this.viewGenreService.updateGenre(id, genre);
        return "redirect:/client/genres/view";
    }

    @GetMapping("/add")
    public String addGenre(Model model) {
        model.addAttribute("entityType", "Genre");
        model.addAttribute("genre", new Genre());
        return "addForm";
    }

    @PostMapping("/add")
    public String addGenre(@ModelAttribute Genre genre) {
        this.viewGenreService.addGenre(genre);
        return "redirect:/client/genres/view";
    }
}
