package de.schneider21.what2eat.meal.business;

import de.schneider21.what2eat.meal.data.BasicMeal;
import de.schneider21.what2eat.meal.data.ExtendedMeal;

import java.util.List;

public interface IMealService {

    List<BasicMeal> getAllAvailableMeals();

    ExtendedMeal getExtendedMealForDate(String dateString);

    int calculateColdBowlProbabilityInPercent(Double temperature);
}
