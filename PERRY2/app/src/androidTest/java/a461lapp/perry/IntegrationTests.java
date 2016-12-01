package a461lapp.perry;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class IntegrationTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    // Create Alarm with both Time and Date
    @Test
    public void IntegrationTest_1() {

        System.out.println("Create Alarm with both Time and Date");

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.alarmName), isDisplayed()));
        appCompatEditText.perform(replaceText("Test"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.createAlarm), withText("+"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.setTime), withText("Set Time"), isDisplayed()));
        button.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.time_activity_confirm_button), withText("Change Time"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.setDate), withText("Set Date"), isDisplayed()));
        button2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.confirm_date), withText("Set Date"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction checkBox = onView(
                allOf(withId(R.id.checkBox), withText("Set Alarm"), isDisplayed()));
        checkBox.perform(click());

    }

    // Test Alarm with Time and without Date
    @Test
    public void createAlarmWithoutDate() {

        System.out.println("Test Alarm with Time and without Date");

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.alarmName), isDisplayed()));
        appCompatEditText.perform(replaceText("Test"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.createAlarm), withText("+"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.setTime), withText("Set Time"), isDisplayed()));
        button.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.time_activity_confirm_button), withText("Change Time"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction checkBox = onView(
                allOf(withId(R.id.checkBox), withText("Set Alarm"), isDisplayed()));
        checkBox.perform(click());

    }

    // Create Alarm with Date and without Time
    @Test
    public void createAlarmWithoutTime() {

        System.out.println("Create Alarm with Date and without Time");

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.alarmName), isDisplayed()));
        appCompatEditText.perform(replaceText("Test"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.createAlarm), withText("+"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.setDate), withText("Set Date"), isDisplayed()));
        button.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.confirm_date), withText("Set Date"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction checkBox = onView(
                allOf(withId(R.id.checkBox), withText("Set Alarm"), isDisplayed()));
        checkBox.perform(click());


    }

}
