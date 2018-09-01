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

    public static final Meal MEAL_01 = new Meal(LocalDateTime.of(2015, Month.SEPTEMBER, 1, 9, 0),"breakfast",650);
    public static final Meal MEAL_02 = new Meal(LocalDateTime.of(2015, Month.SEPTEMBER, 1, 12, 40),"dinner",800);
    public static final Meal MEAL_03 = new Meal(LocalDateTime.of(2015, Month.SEPTEMBER, 1, 8, 30),"breakfast",550);
    public static final Meal MEAL_04 = new Meal(LocalDateTime.of(2015, Month.SEPTEMBER, 1, 14, 0),"dinner",700);

//    public static void assertMatch(User actual, User expected) {
//        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
//    }
//
//    public static void assertMatch(Iterable<User> actual, User... expected) {
//        assertMatch(actual, Arrays.asList(expected));
//    }
//
//    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
//        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
//    }
}
