package de.schneider21.what2eat.meal.business;

import de.schneider21.what2eat.meal.data.BasicMeal;

import java.util.Collections;
import java.util.List;

public class MockMenuService implements IMenuService {

    private List<BasicMeal> allMeals;

    public MockMenuService(List<BasicMeal> allMeals) {
        this.allMeals = allMeals;
    }

    public List<BasicMeal> findAllSortByDateAsc() {
        return Collections.unmodifiableList(allMeals);
    }

    @Override
    public List<BasicMeal> getAllAvailableMeals() {
        return allMeals;
    }

    @Override
    public BasicMeal getMealForDate(String dateString) {
        return allMeals
                .stream()
                .filter(m -> m.getDate().equals(dateString))
                .findAny()
                .orElse(null);
    }
}
