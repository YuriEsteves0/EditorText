package com.yuri.ldt;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.yuri.ldt.View.LoginActivity;
import com.yuri.ldt.View.SplashScreen;

import org.junit.Rule;
import org.junit.Test;

public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenarioRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testeLogin(){
        onView(withId(R.id.emailInput)).perform(typeText("yuri@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.senhaInput)).perform(typeText("123123"), closeSoftKeyboard());
        onView(withId(R.id.senhaInput2)).perform(typeText("123223"), closeSoftKeyboard());

        onView(withId(R.id.btnEntrar)).perform(click());

        onView(withId(R.id.texto)).check(matches(isDisplayed()));
    }
}
