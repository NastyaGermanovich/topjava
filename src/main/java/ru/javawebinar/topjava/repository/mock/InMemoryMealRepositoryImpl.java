package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private static AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int mealId) {
        if(repository.get(mealId).getUserId() == userId) {
           return repository.remove(mealId)!= null;
        }
        return false;
    }

    @Override
    public Meal get(int userId, int mealId) {
       if(repository.get(mealId).getUserId() == userId) {
           return repository.get(mealId);
       }
       return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> meals = repository.values()
                .stream()
                .filter(v -> v.getUserId()== userId)
                .collect(Collectors.toList());
        meals.sort(Comparator.comparing(Meal::getDate).reversed());
        return meals;
    }
}

