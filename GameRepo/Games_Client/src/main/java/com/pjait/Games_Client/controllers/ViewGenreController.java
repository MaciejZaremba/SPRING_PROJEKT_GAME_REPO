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
        model.addAttribute("entities", new Genre());
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
}
