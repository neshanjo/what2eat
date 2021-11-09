package de.schneider21.what2eat.meal.business;

import de.schneider21.what2eat.meal.data.BasicMeal;
import de.schneider21.what2eat.meal.data.DataWithTime;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service that caches method calls to the given IMenuService instance.
 * This class implements a simple cache that grows over time and is never reset.
 * It is safe to use with threads, however, in the case that two calls happen exactly at
 * the same time, the cache is updated twice.
 */
public class CachingMenuService implements IMenuService {

    private IMenuService service;
    private long cachingTimeMs;

    public CachingMenuService(IMenuService service, long cachingTimeMs) {
        this.service = service;
        this.cachingTimeMs = cachingTimeMs;
    }

    private DataWithTime<List<BasicMeal>> mealListWithTime;

    @Override
    public List<BasicMeal> getAllAvailableMeals() {
        long currentTime = System.currentTimeMillis();
        if (mealListWithTime == null || mealListWithTime.getData() == null ||
                currentTime - mealListWithTime.getTime() > cachingTimeMs) {
            System.out.println("CachingMenuService: Updating cache");
            mealListWithTime = new DataWithTime<>(service.getAllAvailableMeals(), currentTime);
        } else {
            System.out.println("CachingMenuService: Using cached result");
        }
        return mealListWithTime.getData();
    }

    private Map<String, DataWithTime<BasicMeal>> mealAndTimeByDate = new ConcurrentHashMap<>();

    @Override
    public BasicMeal getMealForDate(String dateString) {
        long currentTime = System.currentTimeMillis();
        DataWithTime<BasicMeal> basicMealDataWithTime = mealAndTimeByDate.get(dateString);
        if (basicMealDataWithTime == null || basicMealDataWithTime.getData() == null
                || currentTime - basicMealDataWithTime.getTime() > cachingTimeMs) {
            System.out.println("CachingMenuService: Updating cache");
            BasicMeal meal = service.getMealForDate(dateString);
            basicMealDataWithTime = new DataWithTime<>(meal, currentTime);
            mealAndTimeByDate.put(dateString, basicMealDataWithTime);
        } else {
            System.out.println("CachingMenuService: Using cached result");
        }
        return basicMealDataWithTime.getData();
    }
}
