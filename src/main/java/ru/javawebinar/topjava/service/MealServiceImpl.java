package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal create(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    @Override
    public void update(int userId, Meal meal) {
        ValidationUtil.checkNotFound(repository.save(userId, meal), "update meal with Id: " + meal.getId());
    }

    @Override
    public void delete(int userId, int id) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(repository.delete(userId, id), id);
    }

    @Override
    public Meal get(int userId, int id) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(repository.get(userId, id), id);
    }

    @Override
    public List<MealWithExceed> getAll(int userId, int caloriesPerDay) {
        return MealsUtil.getWithExceeded(repository.getAll(userId), caloriesPerDay);
    }

    @Override
    public List<MealWithExceed> getBetween(int userId, int caloriesPerDae, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return MealsUtil.getFilteredWithExceeded(repository.getAll(userId), caloriesPerDae, DateTimeUtil.toLocalDateTime(startDate, startTime), DateTimeUtil.toLocalDateTime(endDate, endTime));
    }


}