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
public class UnitTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    // Set Time and Date Test
    @Test
    public void UnitTest_1() {
        System.out.println("Set Time and Date Test");

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

    }

    // Test Title
    @Test
    public void UnitTest_2() {

        System.out.println("Set Title Test");

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.alarmName), isDisplayed()));
        appCompatEditText.perform(replaceText("Testing Name"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.createAlarm), withText("+"), isDisplayed()));
        appCompatButton.perform(click());

    }

}
