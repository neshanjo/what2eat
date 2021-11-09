package de.schneider21.what2eat.meal.business;

import de.schneider21.what2eat.meal.data.BasicMeal;

import java.util.List;

public interface IMenuService {

    List<BasicMeal> getAllAvailableMeals();

    BasicMeal getMealForDate(String dateString);
}
