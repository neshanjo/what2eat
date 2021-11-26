package de.schneider21.what2eat.meal.api;

import de.schneider21.what2eat.meal.business.IMealService;
import de.schneider21.what2eat.meal.data.BasicMeal;
import de.schneider21.what2eat.meal.data.ExtendedMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MealController {

    private IMealService mealService;

    @Autowired
    public MealController(IMealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/meal")
    public List<BasicMeal> getMeals() {
        final List<BasicMeal> meals = mealService.getAllAvailableMeals();
        return meals;
    }

    @GetMapping("/meal/{date}")
    public ExtendedMeal getMeal(@PathVariable String date) {
        final ExtendedMeal meal = mealService.getExtendedMealForDate(date);
        return meal;
    }

}
