package com.juliosueiras.tip_calculator;

import android.support.test.rule.ActivityTestRule;

import android.support.test.runner.AndroidJUnit4;

import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.squareup.spoon.Spoon;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.containsString;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    private final static int TEST_CASH_AMOUNT = 150;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    private MainActivity mActivity;

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
    }

    @Test
    public void test_default_all_one_person_calculate() {
        Spoon.screenshot(mActivity, "initial_state");
        _calculateTip(0.10);
        Spoon.screenshot(mActivity, "after_test");
    }

    @Test
    public void test_tip_15_perc_one_person_calculate() {
        Spoon.screenshot(mActivity, "initial_state");
        _selectTipAmount("15%");
        _calculateTip(0.15);
        Spoon.screenshot(mActivity, "after_test");
    }

    @Test
    public void test_tip_20_perc_one_person_calculate() {
        Spoon.screenshot(mActivity, "initial_state");
        _selectTipAmount("20%");
        _calculateTip(0.20);
        Spoon.screenshot(mActivity, "after_test");
    }

    private void _selectTipAmount(String tip_amount) {
        if(!(tip_amount.equals("10%"))) {
            onView(withId(R.id.spinner_tip)).perform(click());
            onData(allOf(is(instanceOf(String.class)), is(tip_amount))).perform(click());
        }

    }

    private void _calculateTip(double tip_amount) {
        onView(withId(R.id.edit_text_cash_amount)).perform(typeText(String.valueOf(TEST_CASH_AMOUNT)));
        onView(withId(R.id.button_calculate)).perform(click()); onView(withId(R.id.text_result_tip)).check(matches(withText("Tip is: $" + (TEST_CASH_AMOUNT * tip_amount))));
        onView(withId(R.id.text_result_total)).check(matches(withText("Total is: $" + (TEST_CASH_AMOUNT * (1+ tip_amount)))));
    }
}
