package de.schneider21.what2eat.meal.api;

import de.schneider21.what2eat.ServiceFactory;
import de.schneider21.what2eat.framework.RestController;
import de.schneider21.what2eat.meal.business.IMealService;
import de.schneider21.what2eat.meal.data.BasicMeal;
import de.schneider21.what2eat.meal.data.ExtendedMeal;

import java.util.List;

public class MealController extends RestController {

    public MealController() {
        super();
        addHttpGetMapping("/meal", this::getMeals);
        addHttpGetMapping("/meal/.*", this::getMeal);
    }

    public List<BasicMeal> getMeals(IRequestParameters parameters) {
        final IMealService mealService = ServiceFactory.getInstance().getMealService();
        final List<BasicMeal> meals = mealService.getAllAvailableMeals();

        return meals;
    }

    public ExtendedMeal getMeal(IRequestParameters parameters) {
        final String dateFromPath = parameters.getPath().substring("/meal/".length());

        final IMealService mealService = ServiceFactory.getInstance().getMealService();
        final ExtendedMeal meal = mealService.getExtendedMealForDate(dateFromPath);

        return meal;
    }

}
