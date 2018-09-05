package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;


    @Test
    public void get() throws Exception {
        int id = MEAL_02.getId();
        assertMatch(mealService.get(id, USER_ID), MEAL_02);
    }

    @Test
    public void delete() throws Exception {
        int id = MEAL_01.getId();
        mealService.delete(id, USER_ID);
        assertMatch(mealService.getAll(USER_ID), MEAL_02);
    }

    @Test
    public void getBetweenDates() throws Exception {
      LocalDate start = LocalDate.of(2015,Month.SEPTEMBER,1);
      LocalDate end = LocalDate.of(2015,Month.SEPTEMBER,2);
      assertMatch(mealService.getBetweenDates(start,end,ADMIN_ID),MEAL_03);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        LocalDateTime start = LocalDateTime.of(2015,Month.SEPTEMBER,1,9,0);
        LocalDateTime end = LocalDateTime.of(2015,Month.SEPTEMBER,1,12,0);
        assertMatch(mealService.getBetweenDateTimes(start,end,USER_ID),MEAL_01);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(mealService.getAll(ADMIN_ID), MEAL_04, MEAL_03);
    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal();
        updated.setId(MEAL_01.getId());
        updated.setCalories(300);
        updated.setDescription("supper");
        updated.setDateTime( LocalDateTime.of(2015,Month.SEPTEMBER,2,15,0));
        mealService.update(updated, USER_ID);
        assertMatch(mealService.get(updated.getId(), USER_ID), updated);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.of(2015, Month.SEPTEMBER, 3, 18, 0), "supper", 700);
        Meal created = mealService.create(newMeal, ADMIN_ID);
        newMeal.setId(created.getId());
        assertMatch(mealService.getAll(ADMIN_ID), newMeal, MEAL_04, MEAL_03);
    }


    @Test(expected = NotFoundException.class)
    public void NotFoundDelete() {
        mealService.delete(MEAL_01.getId(), ADMIN_ID);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void NotFoundGet() {
        mealService.get(MEAL_01.getId(),ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void NotFoundUpdate() {
        Meal updated = new Meal();
        updated.setId(MEAL_01.getId());
        updated.setCalories(300);
        updated.setDescription("supper");
        mealService.update(updated, ADMIN_ID);
    }
}