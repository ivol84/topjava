package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.web.meal.MealRestController;

@Controller
@RequestMapping("/meals")
public class MealsController {
    @Autowired
    private MealRestController restController;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("meals", restController.getAll());
        return "meals";
    }
}
