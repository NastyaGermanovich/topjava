package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;

    public static final Meal MEAL_01 = new Meal(MEAL_ID , LocalDateTime.of(2015, Month.SEPTEMBER, 1, 9, 0), "breakfast", 650);
    public static final Meal MEAL_02 = new Meal(MEAL_ID + 1, LocalDateTime.of(2015, Month.SEPTEMBER, 1, 12, 40), "dinner", 800);
    public static final Meal MEAL_03 = new Meal(MEAL_ID + 2, LocalDateTime.of(2015, Month.SEPTEMBER, 1, 8, 30), "breakfast", 550);
    public static final Meal MEAL_04 = new Meal(MEAL_ID + 3, LocalDateTime.of(2015, Month.SEPTEMBER, 3, 14, 0), "dinner", 700);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

}
