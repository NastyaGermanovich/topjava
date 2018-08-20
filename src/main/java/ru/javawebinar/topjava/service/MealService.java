package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {

    Meal create(int userId, Meal meal);

    void update(int userId, Meal meal);

    void delete(int userId, int id) throws NotFoundException;

    Meal get(int userId, int id) throws NotFoundException;

    List<MealWithExceed> getAll(int userId, int caloriesPerDay);

    List<MealWithExceed> getBetween(int userId, int caloriesPerDay, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime);


}