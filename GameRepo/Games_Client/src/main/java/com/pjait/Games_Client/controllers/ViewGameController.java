package com.pjait.Games_Client.controllers;

import com.pjait.Games_Client.services.ViewGameService;
import com.pjait.Games_Data.entities.Game;
import com.pjait.Games_Data.entities.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/client/games")
public class ViewGameController {
    private final ViewGameService viewGameService;

    public ViewGameController(ViewGameService viewGameService) {
        this.viewGameService = viewGameService;
    }

    @GetMapping("/view")
    public String viewAllGames(Model model) {
        List<Game> games = viewGameService.getAllGames();

        model.addAttribute("entityType", "Game");
        model.addAttribute("entities", games);
        return "viewList";
    }

    @GetMapping("/view/genres")
    public String viewGenresByGameId(Model model) {
        model.addAttribute("entityType", "Genre");
        model.addAttribute("entities", new Genre());
        return "findByIdForm";
    }

    @PostMapping("/view/genres")
    public String viewGenresByGameId(@ModelAttribute("entityType") Genre genre, Model model) {
        Long inputId = genre.getId();
        model.addAttribute("entityType", "Genre");
        model.addAttribute("entities", viewGameService.getAllGenresByGameId(inputId));
        return "viewList";
    }
}
