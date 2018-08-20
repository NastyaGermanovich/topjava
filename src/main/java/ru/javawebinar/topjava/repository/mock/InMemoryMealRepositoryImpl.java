package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private static  AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(SecurityUtil.authUserId(), meal));
    }
// метод save работает неправильно
    @Override
    public Meal save(int userId, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            Map<Integer, Meal> meals = new HashMap<>();
            meals.put(meal.getId(), meal);
            repository.put(userId, meals);
            return meal;
        }
        // treat case: update, but absent in storage
        Map<Integer, Meal> meals = repository.get(userId);
        return meals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int mealId) {
        Map<Integer, Meal> meals = repository.get(userId);
        if (meals != null && !meals.isEmpty()) {
            return meals.remove(mealId) != null;
        }
        return false;
    }

    @Override
    public Meal get(int userId, int mealId) {
        Map<Integer, Meal> meals = repository.get(userId);
        if (meals != null && !meals.isEmpty()) {
            return meals.get(mealId);
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        List<Meal> mealList = new ArrayList<>(meals.values());
        mealList.sort(Comparator.comparing(Meal::getDate).reversed());
        return mealList;
    }
}

