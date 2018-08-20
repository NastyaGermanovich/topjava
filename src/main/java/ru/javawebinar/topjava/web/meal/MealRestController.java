package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealWithExceed> getAll() {
        int userId = SecurityUtil.authUserId();
        int caloriesPerDay = SecurityUtil.authUserCaloriesPerDay();
        log.info("user {} getAll", userId);
        return service.getAll(userId, caloriesPerDay);
    }

    public Meal get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("user {} got meal {}", userId, id);
        return service.get(userId, id);
    }

    public List<MealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = SecurityUtil.authUserId();
        int caloriesPerDay = SecurityUtil.authUserCaloriesPerDay();
        log.info("user {} got meal between: {} {} and {} {}", userId, startDate, startTime, endDate, endTime);
        return service.getBetween(userId, caloriesPerDay, startDate, startTime, endDate, endTime);
    }

    public Meal create(Meal meal) {
        int userId = SecurityUtil.authUserId();
        log.info("user {} create meal{}", userId, meal);
        return service.create(userId, meal);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("user {} deleted meal {}", userId, id);
        service.delete(userId, id);
    }

    public void update(Meal meal, int id) {
        int userId = SecurityUtil.authUserId();
        log.info("user {} update meal{} with id={}", userId, meal, id);
        service.update(userId, meal);
    }

}