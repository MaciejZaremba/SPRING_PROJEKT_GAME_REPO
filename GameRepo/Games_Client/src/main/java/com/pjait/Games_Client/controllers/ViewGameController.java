package com.pjait.Games_Client.controllers;

import com.pjait.Games_Client.services.ViewGameService;
import com.pjait.Games_Data.entities.*;
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

    @GetMapping("/view/id")
    public String viewGameById(Model model) {
        model.addAttribute("entityType", "Game");
        model.addAttribute("game", new Game());
        return "findByIdForm";
    }

    @PostMapping("/view/id")
    public String viewGameById(@ModelAttribute Game game, Model model)
    {
        Long id = game.getId();
        model.addAttribute("entityType", "Game");
        model.addAttribute("entities", viewGameService.getGameById(id));
        return "viewList";
    }

    @GetMapping("/view/name")
    public String viewGameByName(Model model) {
        model.addAttribute("entityType", "Game");
        model.addAttribute("game", new Game());
        return "findByNameForm";
    }

    @PostMapping("/view/name")
    public String viewGameByName(@ModelAttribute Game game, Model model)
    {
        String name = game.getName();
        model.addAttribute("entityType", "Game");
        model.addAttribute("entities", viewGameService.getGameByName(name));
        return "viewList";
    }

    @GetMapping("/view/apiId")
    public String viewGameByApiId(Model model) {
        model.addAttribute("entityType", "Game");
        model.addAttribute("game", new Game());
        return "findByApiIdForm";
    }

    @PostMapping("/view/apiId")
    public String viewGameByApiId(@ModelAttribute Game game, Model model)
    {
        Long apiId = game.getApiId();
        model.addAttribute("entityType", "Game");
        model.addAttribute("entities", viewGameService.getGameByApiId(apiId));
        return "viewList";
    }

    @GetMapping("/view/rating")
    public String viewGamesByRating(Model model) {
        model.addAttribute("entityType", "Game");
        model.addAttribute("game", new Game());
        return "findByRatingForm";
    }

    @PostMapping("/view/rating")
    public String viewGamesByRating(@ModelAttribute Game game, Model model)
    {
        Double rating = game.getRating();
        model.addAttribute("entityType", "Game");
        model.addAttribute("entities", viewGameService.getGamesByRating(rating));
        return "viewList";
    }

    @GetMapping("/view/year")
    public String viewGamesByReleaseYear(Model model) {
        model.addAttribute("entityType", "Game");
        model.addAttribute("game", new Game());
        return "findByReleaseYearForm";
    }

    @PostMapping("/view/year")
    public String viewGamesByReleaseYear(@ModelAttribute Game game, Model model)
    {
        Integer year = game.getReleaseYear();
        model.addAttribute("entityType", "Game");
        model.addAttribute("entities", viewGameService.getGamesByReleaseYear(year));
        return "viewList";
    }

    @GetMapping("/delete")
    public String deleteGame(Model model) {
        model.addAttribute("entityType", "Game");
        model.addAttribute("game", new Game());
        return "deleteForm";
    }

    @PostMapping("/delete")
    public String deleteGame(@ModelAttribute Game game)
    {
        Long id = game.getId();
        viewGameService.deleteGame(id);
        return "redirect:/client/games/view";
    }

    @GetMapping("/update")
    public String updateGame(Model model) {
        model.addAttribute("entityType", "Game");
        model.addAttribute("game", new Game());
        return "updateForm";
    }

    @PostMapping("/update")
    public String updateGame(@ModelAttribute Game game)
    {
        Long id = game.getId();
        viewGameService.updateGame(id, game);
        return "redirect:/client/games/view";
    }

    @GetMapping("/add")
    public String addGame(Model model) {
        model.addAttribute("entityType", "Game");
        model.addAttribute("game", new Game());
        return "addForm";
    }

    @PostMapping("/add")
    public String addGame(@ModelAttribute Game game)
    {
        viewGameService.addGame(game);
        return "redirect:/client/games/view";
    }

    @GetMapping("/view/genres")
    public String viewAllGenresByGameId(Model model) {
        model.addAttribute("entityType", "Genre");
        model.addAttribute("genre", new Genre());
        return "findByIdForm";
    }

    @PostMapping("/view/genres")
    public String viewAllGenresByGameId(@ModelAttribute Genre genre, Model model)
    {
        Long id = genre.getId();
        model.addAttribute("entityType", "Genre");
        model.addAttribute("entities", viewGameService.getAllGenresByGameId(id));
        return "viewList";
    }

    @GetMapping("/view/platforms")
    public String viewAllPlatformsByGameId(Model model) {
        model.addAttribute("entityType", "Platform");
        model.addAttribute("platform", new Platform());
        return "findByIdForm";
    }

    @PostMapping("/view/platforms")
    public String viewAllPlatformsByGameId(@ModelAttribute Platform platform, Model model)
    {
        Long id = platform.getId();
        model.addAttribute("entityType", "Platform");
        model.addAttribute("entities", viewGameService.getAllPlatformsByGameId(id));
        return "viewList";
    }

    @GetMapping("/view/themes")
    public String viewAllThemesByGameId(Model model) {
        model.addAttribute("entityType", "Theme");
        model.addAttribute("theme", new Theme());
        return "findByIdForm";
    }

    @PostMapping("/view/themes")
    public String viewAllThemesByGameId(@ModelAttribute Theme theme, Model model)
    {
        Long id = theme.getId();
        model.addAttribute("entityType", "Theme");
        model.addAttribute("entities", viewGameService.getAllThemesByGameId(id));
        return "viewList";
    }

    @GetMapping("/view/companies")
    public String viewAllCompaniesByGameId(Model model) {
        model.addAttribute("entityType", "Company");
        model.addAttribute("company", new Company());
        return "findByIdForm";
    }

    @PostMapping("/view/companies")
    public String viewAllCompaniesByGameId(@ModelAttribute Company company, Model model)
    {
        Long id = company.getId();
        model.addAttribute("entityType", "Theme");
        model.addAttribute("entities", viewGameService.getAllCompaniesByGameId(id));
        return "viewList";
    }
}
